[CmdletBinding()]
param(
    [string]$ProjectDirectory = "Form + Database/KostAiraApp",
    [string]$OutputDirectory = "artifacts",
    [string]$Version = "1.0.2"
)

$ErrorActionPreference = "Stop"
Set-StrictMode -Version Latest

$repositoryRoot = Split-Path -Parent $PSScriptRoot
$projectRoot = Join-Path $repositoryRoot $ProjectDirectory
$sourceRoot = Join-Path $projectRoot "src"
$libraryRoot = Join-Path $projectRoot "JasperReports Library"
$buildRoot = Join-Path $repositoryRoot ".portable-build"
$classesRoot = Join-Path $buildRoot "classes"
$dependencyRoot = Join-Path $buildRoot "dependencies"
$inputRoot = Join-Path $buildRoot "input"
$artifactRoot = Join-Path $repositoryRoot $OutputDirectory
$absoluteLayoutJar = Join-Path $dependencyRoot "AbsoluteLayout-RELEASE300.jar"
$applicationJar = Join-Path $inputRoot "KostAiraApp.jar"
$applicationIconSource = Join-Path $sourceRoot "SI_AiraKost_Asset/LOGOKOST.png"

if (-not (Test-Path $sourceRoot)) {
    throw "Folder source tidak ditemukan: $sourceRoot"
}

if (-not (Test-Path $applicationIconSource)) {
    throw "Icon aplikasi tidak ditemukan: $applicationIconSource"
}

foreach ($command in @("javac", "jar", "jpackage")) {
    if (-not (Get-Command $command -ErrorAction SilentlyContinue)) {
        throw "Perintah '$command' tidak tersedia. Gunakan JDK 17 atau yang lebih baru."
    }
}

$normalizedVersion = $Version.TrimStart("v")
if ($normalizedVersion -notmatch "^\d+(\.\d+){0,2}$") {
    throw "Versi '$Version' tidak valid untuk jpackage. Gunakan format seperti 1.0.0."
}

# ============================================================
# Section: Windows build
# Output:
# - Installer Windows .exe
# - Membutuhkan jpackage dari JDK 17+
# - Di GitHub Actions, workflow menginstall WiX Toolset lebih dulu
# ============================================================
if ($IsWindows) {
    $platformName = "Windows"
    $architecture = "x64"
    $classpathSeparator = ";"
    $packageType = "exe"
    $packageExtension = ".exe"

# ============================================================
# Section: macOS build
# Output:
# - Installer macOS .dmg
# - Dibuild di runner macOS karena jpackage membuat paket native
# - Arsitektur mengikuti runner: x64 atau arm64
# ============================================================
} elseif ($IsMacOS) {
    $platformName = "macOS"
    $architecture = if ([System.Runtime.InteropServices.RuntimeInformation]::OSArchitecture -eq "Arm64") { "arm64" } else { "x64" }
    $classpathSeparator = ":"
    $packageType = "dmg"
    $packageExtension = ".dmg"

# ============================================================
# Section: Linux build
# Output:
# - Paket Linux .deb
# - Membutuhkan fakeroot di GitHub Actions untuk packaging .deb
# - Target saat ini Linux x64
# ============================================================
} elseif ($IsLinux) {
    $platformName = "Linux"
    $architecture = "x64"
    $classpathSeparator = ":"
    $packageType = "deb"
    $packageExtension = ".deb"
} else {
    throw "Sistem operasi ini tidak didukung."
}

if (Test-Path $buildRoot) {
    $resolvedBuildRoot = [System.IO.Path]::GetFullPath($buildRoot)
    $resolvedRepositoryRoot = [System.IO.Path]::GetFullPath($repositoryRoot)
    if (-not $resolvedBuildRoot.StartsWith($resolvedRepositoryRoot, [System.StringComparison]::OrdinalIgnoreCase)) {
        throw "Build directory berada di luar repository: $resolvedBuildRoot"
    }
    Remove-Item -LiteralPath $resolvedBuildRoot -Recurse -Force
}

New-Item -ItemType Directory -Force -Path $classesRoot, $dependencyRoot, $inputRoot, $artifactRoot | Out-Null

if ($IsWindows) {
    Add-Type -AssemblyName System.Drawing
    $packageIcon = Join-Path $buildRoot "KostAiraApp.ico"
    $sourceImage = [System.Drawing.Image]::FromFile($applicationIconSource)
    $iconBitmap = New-Object System.Drawing.Bitmap 256, 256
    $graphics = [System.Drawing.Graphics]::FromImage($iconBitmap)

    try {
        $graphics.Clear([System.Drawing.Color]::Transparent)
        $graphics.InterpolationMode = [System.Drawing.Drawing2D.InterpolationMode]::HighQualityBicubic
        $graphics.DrawImage($sourceImage, 0, 0, 256, 256)
        $iconHandle = $iconBitmap.GetHicon()
        $icon = [System.Drawing.Icon]::FromHandle($iconHandle)
        $iconStream = [System.IO.File]::Create($packageIcon)
        try {
            $icon.Save($iconStream)
        } finally {
            $iconStream.Dispose()
            $icon.Dispose()
        }
    } finally {
        $graphics.Dispose()
        $iconBitmap.Dispose()
        $sourceImage.Dispose()
    }
} elseif ($IsMacOS) {
    $iconSet = Join-Path $buildRoot "KostAiraApp.iconset"
    $packageIcon = Join-Path $buildRoot "KostAiraApp.icns"
    New-Item -ItemType Directory -Force -Path $iconSet | Out-Null

    $iconSizes = @{
        "icon_16x16.png" = 16
        "icon_16x16@2x.png" = 32
        "icon_32x32.png" = 32
        "icon_32x32@2x.png" = 64
        "icon_128x128.png" = 128
        "icon_128x128@2x.png" = 256
        "icon_256x256.png" = 256
        "icon_256x256@2x.png" = 512
        "icon_512x512.png" = 512
        "icon_512x512@2x.png" = 1024
    }

    foreach ($iconFile in $iconSizes.GetEnumerator()) {
        & sips -z $iconFile.Value $iconFile.Value $applicationIconSource `
            --out (Join-Path $iconSet $iconFile.Key) | Out-Null
        if ($LASTEXITCODE -ne 0) {
            throw "Gagal membuat icon macOS: $($iconFile.Key)"
        }
    }

    & iconutil -c icns $iconSet -o $packageIcon
    if ($LASTEXITCODE -ne 0) {
        throw "Gagal membuat file icon macOS."
    }
} else {
    $packageIcon = $applicationIconSource
}

Invoke-WebRequest `
    -Uri "https://repo1.maven.org/maven2/org/netbeans/external/AbsoluteLayout/RELEASE300/AbsoluteLayout-RELEASE300.jar" `
    -OutFile $absoluteLayoutJar

$sourceFiles = Get-ChildItem -Path $sourceRoot -Recurse -Filter "*.java" |
    ForEach-Object { '"' + $_.FullName.Replace([char]92, [char]47) + '"' }
$sourceList = Join-Path $buildRoot "sources.txt"
Set-Content -Path $sourceList -Value $sourceFiles -Encoding ASCII

$compileClasspath = "$libraryRoot/*$classpathSeparator$absoluteLayoutJar"
& javac `
    --release 8 `
    -encoding UTF-8 `
    -classpath $compileClasspath `
    -d $classesRoot `
    "@$sourceList"

if ($LASTEXITCODE -ne 0) {
    throw "Kompilasi Java gagal dengan exit code $LASTEXITCODE."
}

Get-ChildItem -Path $sourceRoot -Recurse -File |
    Where-Object { $_.Extension -ne ".java" } |
    ForEach-Object {
        $relativePath = $_.FullName.Substring($sourceRoot.Length).TrimStart("\", "/")
        $destination = Join-Path $classesRoot $relativePath
        New-Item -ItemType Directory -Force -Path (Split-Path -Parent $destination) | Out-Null
        Copy-Item -LiteralPath $_.FullName -Destination $destination -Force
    }

& jar `
    --create `
    --file $applicationJar `
    --main-class FormAPP.LoginMini `
    -C $classesRoot .

if ($LASTEXITCODE -ne 0) {
    throw "Pembuatan JAR gagal dengan exit code $LASTEXITCODE."
}

Copy-Item -Path (Join-Path $libraryRoot "*.jar") -Destination $inputRoot -Force
Copy-Item -LiteralPath $absoluteLayoutJar -Destination $inputRoot -Force

$databaseProperties = Join-Path $projectRoot "db.properties"
if (Test-Path $databaseProperties) {
    Copy-Item -LiteralPath $databaseProperties -Destination $inputRoot -Force
}

$caCertificate = Join-Path $repositoryRoot "Form + Database/Backup Database/ca.pem"
if (Test-Path $caCertificate) {
    Copy-Item -LiteralPath $caCertificate -Destination $inputRoot -Force
}

$databaseRoot = Join-Path $inputRoot "database"
New-Item -ItemType Directory -Force -Path $databaseRoot | Out-Null
Copy-Item `
    -LiteralPath (Join-Path $repositoryRoot "Form + Database/Backup Database/kostaira.sql") `
    -Destination $databaseRoot
Copy-Item `
    -LiteralPath (Join-Path $repositoryRoot "PORTABLE.md") `
    -Destination (Join-Path $inputRoot "README.md")

$jpackageArguments = @(
    "--type", $packageType,
    "--name", "KostAiraApp",
    "--input", $inputRoot,
    "--main-jar", "KostAiraApp.jar",
    "--main-class", "FormAPP.LoginMini",
    "--dest", $artifactRoot,
    "--vendor", "KostAira",
    "--app-version", $normalizedVersion,
    "--icon", $packageIcon,
    "--description", "Sistem Informasi Pemesanan Kamar Kos",
    "--java-options", "-Dfile.encoding=UTF-8",
    "--java-options", "--add-opens=java.base/java.lang=ALL-UNNAMED",
    "--java-options", "--add-opens=java.base/java.util=ALL-UNNAMED"
)

# ============================================================
# Section: Windows jpackage options
# - Menambahkan pilihan folder instalasi
# - Menambahkan shortcut Start Menu dan Desktop
# ============================================================
if ($IsWindows) {
    $jpackageArguments += @("--win-dir-chooser", "--win-menu", "--win-shortcut")

# ============================================================
# Section: Linux jpackage options
# - Menentukan nama package Debian
# - Menambahkan menu group dan shortcut launcher
# ============================================================
} elseif ($IsLinux) {
    $jpackageArguments += @(
        "--linux-package-name", "kostairaapp",
        "--linux-menu-group", "Office",
        "--linux-shortcut"
    )

# ============================================================
# Section: macOS jpackage options
# - Menentukan nama package/app macOS
# ============================================================
} elseif ($IsMacOS) {
    $jpackageArguments += @("--mac-package-name", "KostAiraApp")
}

& jpackage @jpackageArguments

if ($LASTEXITCODE -ne 0) {
    throw "Pembuatan paket $packageType gagal dengan exit code $LASTEXITCODE."
}

$generatedPackage = Get-ChildItem -Path $artifactRoot -File |
    Where-Object { $_.Extension -eq $packageExtension } |
    Sort-Object LastWriteTimeUtc -Descending |
    Select-Object -First 1

if (-not $generatedPackage) {
    throw "Paket hasil jpackage dengan ekstensi '$packageExtension' tidak ditemukan."
}

$artifactPath = Join-Path $artifactRoot "KostAiraApp-v$normalizedVersion-$platformName-$architecture$packageExtension"
if ($generatedPackage.FullName -ne $artifactPath) {
    Move-Item -LiteralPath $generatedPackage.FullName -Destination $artifactPath -Force
}

Write-Host "Paket aplikasi berhasil dibuat: $artifactPath"
