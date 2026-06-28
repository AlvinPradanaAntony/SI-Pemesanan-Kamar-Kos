# Changelog

Semua perubahan penting pada proyek **KostAiraApp** akan didokumentasikan di
file ini.

**KostAiraApp** adalah aplikasi desktop berbasis Java Swing untuk pengelolaan
dan pemesanan kamar kos secara online maupun langsung oleh pengelola.

---

## [1.0.0] - 2026-06-27

### Added

- Paket instalasi native untuk Windows x64, Linux x64, dan macOS x64 yang telah
  menyertakan Java Runtime.
- Workflow GitHub Actions untuk membangun artifact multiplatform, membuat
  checksum SHA-256, dan menerbitkan GitHub Release dari tag versi.
- Konfigurasi koneksi MySQL/MariaDB melalui GitHub Secrets, termasuk dukungan
  SSL dan sertifikat CA untuk database terkelola.
- Dokumentasi instalasi, penggunaan paket native, dan persiapan database.

### Changed

- Proses build aplikasi diotomatisasi melalui skrip PowerShell dan `jpackage`.
- Konfigurasi database tidak lagi terbatas pada koneksi lokal sehingga aplikasi
  dapat menggunakan database online maupun lokal.

### Features

- Autentikasi pengguna dan administrator.
- Pengelolaan data kamar, jenis kamar, lokasi, fasilitas, dan status
  ketersediaan.
- Pemesanan kamar kos serta pencatatan pembayaran.
- Dashboard dan laporan operasional untuk pengelola kos.
- Pencarian data dan pengaturan aplikasi.
