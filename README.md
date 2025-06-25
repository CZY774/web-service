# 🏫 Portal Mahasiswa - Web Service Architecture with Spring Boot

Portal Mahasiswa adalah proyek microservices berbasis **Spring Boot 3.4.5** dan **Java 17** yang dikembangkan dalam konteks mata kuliah **Web Service**. Aplikasi ini merupakan simulasi sistem informasi akademik untuk mahasiswa dan dosen, dengan fitur autentikasi, otorisasi berbasis peran, manajemen data matakuliah, kartu studi, dan transkrip nilai.

## 🔧 Teknologi & Tools

- Java 17
- Spring Boot 3.4.5
- Spring Web, Spring Data JPA, MongoDB Driver, JWT
- MySQL, PostgreSQL, MongoDB
- Eureka Discovery Server & Client
- Spring Cloud Gateway (Reactive)
- WebClient (Bonus)
- DTO Mapping (Bonus)

---

## 🧱 Arsitektur Microservice

| Service            | Database     | Tanggung Jawab Utama        |
|--------------------|--------------|------------------------------|
| `user-service`     | MySQL        | Login, register, user info  |
| `matkul-service`   | PostgreSQL   | Manajemen data matakuliah   |
| `dosen-service`    | MongoDB      | Kartu studi & nilai         |
| `api-gateway`      | -            | Reverse proxy & route       |
| `eureka-server`    | -            | Service registry            |

> Setiap service memiliki JWT authentication dan konfigurasi `Spring Security` masing-masing.

---

## 📌 Fitur Utama

### 🔐 Autentikasi & Otorisasi

- Login / Register (NIM + Password)
- JWT Token-based auth
- Role-based access (`MAHASISWA`, `DOSEN`)
- Mahasiswa tidak bisa mengakses endpoint dosen, dan sebaliknya
- Endpoint publik hanya: `/api/auth/login`, `/api/auth/register`

---

### 👨‍🎓 Role: Mahasiswa

| Endpoint                           | Method | Deskripsi                         |
|------------------------------------|--------|-----------------------------------|
| `/api/mahasiswa/kartu-studi`       | GET    | Lihat Kartu Studi                 |
| `/api/mahasiswa/profile`           | GET    | Lihat profil                      |
| `/api/mahasiswa/transkrip`         | GET    | Lihat transkrip nilai             |
| `/api/matakuliah`                  | GET    | Lihat semua matakuliah            |
| `/api/matakuliah`                  | POST   | Tambah matakuliah                 |
| `/api/matakuliah/{id}`             | DELETE | Hapus matakuliah                  |
| `/api/matakuliah/{id}`             | PUT    | Edit matakuliah                   |
| `/api/auth/change-password`        | POST   | Ganti password                    |
| `/api/auth/logout`                 | POST   | Logout                            |

---

### 👨‍🏫 Role: Dosen

| Endpoint                                      | Method | Deskripsi                        |
|-----------------------------------------------|--------|----------------------------------|
| `/api/dosen/kartu-studi`                      | POST   | Tambah kartu studi               |
| `/api/dosen/kartu-studi/{id}`                 | DELETE | Hapus kartu studi                |
| `/api/dosen/kartu-studi/{id}`                 | PUT    | Update kartu studi               |
| `/api/dosen/kartu-studi/mahasiswa/{nim}`      | GET    | Lihat kartu studi mahasiswa      |
| `/api/dosen/nilai`                            | POST   | Tambah nilai                     |
| `/api/dosen/nilai/{id}`                       | DELETE | Hapus nilai                      |
| `/api/dosen/nilai/{id}`                       | PUT    | Update nilai                     |
| `/api/dosen/nilai/mahasiswa/{nim}`            | GET    | Lihat nilai mahasiswa            |
| `/api/auth/change-password`                   | POST   | Ganti password                   |
| `/api/auth/logout`                            | POST   | Logout                           |

---

## 🔗 Struktur Folder

```plaintext
portal-mahasiswa/
│
├── user-service/          # Auth, register, mahasiswa profile (MySQL)
├── matkul-service/        # Manajemen matakuliah (PostgreSQL)
├── dosen-service/         # Nilai & kartu studi (MongoDB)
├── api-gateway/           # Spring Cloud Gateway
├── eureka-server/         # Eureka registry
└── shared-library/        # (Opsional) DTOs & WebClient config
