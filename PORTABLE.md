# KostAiraApp Multiplatform

Setiap file distribusi sudah menyertakan runtime Java. Java tidak perlu dipasang
secara terpisah dan pengguna tidak perlu mengekstrak ZIP.

## Menjalankan aplikasi

### Windows

Unduh dan jalankan:

```text
KostAiraApp-vX.Y.Z-Windows-x64.exe
```

### Linux

Unduh lalu instal paket:

```bash
sudo apt install ./KostAiraApp-vX.Y.Z-Linux-x64.deb
```

### macOS

Unduh dan buka `KostAiraApp-vX.Y.Z-macOS-x64.dmg`, kemudian salin aplikasi ke
folder Applications. Karena paket belum ditandatangani dengan Apple Developer
ID, macOS mungkin meminta pengguna membuka aplikasi melalui klik kanan **Open**
atau menu **System Settings > Privacy & Security**.

## Persiapan database

Aplikasi saat ini menggunakan MySQL/MariaDB dengan konfigurasi:

```text
Host: localhost
Database: kostaira
Username: root
Password: kosong
```

Sebelum login:

1. Jalankan MySQL atau MariaDB.
2. Buat database bernama `kostaira`.
3. Import file `database/kostaira.sql` dari paket aplikasi.
4. Jalankan aplikasi.

Akun admin bawaan:

```text
Username: admin
Password: admin
```

Jika database dipindahkan ke layanan online, sesuaikan koneksi pada
`src/FormAPP/ConnectDB.java`, lalu build ulang.

## Artifact dan GitHub Release

Workflow **Build Multiplatform Release** menghasilkan:

```text
KostAiraApp-vX.Y.Z-Windows-x64.exe
KostAiraApp-vX.Y.Z-Linux-x64.deb
KostAiraApp-vX.Y.Z-macOS-x64.dmg
SHA256SUMS.txt
```

Build biasa dapat diunduh melalui tab **Actions**. Push tag seperti `v1.0.2`
akan membuat satu GitHub Release dan melampirkan ketiga paket tersebut.

Catatan: format satu file ini adalah paket native yang memasang aplikasi beserta
runtime Java. `jpackage` tidak menghasilkan executable portable tunggal yang
benar-benar berjalan tanpa ekstraksi atau instalasi.
