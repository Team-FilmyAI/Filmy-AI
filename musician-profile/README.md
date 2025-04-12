# 🎵 Musician Profile Management API

A Spring Boot RESTful backend that manages musician profiles, including performance details, experiences, recommendations, and availability booking. Designed for integration with a React frontend and supports file uploads for profile pictures and experience posters.

---

## ⚙️ Tech Stack
- **Backend**: Java 21, Spring Boot 3
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA (Hibernate)
- **Validation**: Jakarta Bean Validation
- **Build Tool**: Maven
- **File Uploads**: Multipart + Filesystem (can be migrated to AWS S3 or other cloud later)

---

## 📊 Database Tables Overview

| Entity                   | Description                                                  |
|-------------------------|--------------------------------------------------------------|
| `MusicianProfile`       | Core profile of the musician                                 |
| `MusicianPerformanceInfo` | Performance details: vocal range, instruments, etc.        |
| `Experience`            | Musical experiences: songs, genres, posters, etc.           |
| `RecommendationDetail` | Recommendations by organizations or individuals             |
| `SlotBooking`           | Bookable slots for collaboration or performance             |

---

## 📁 DTOs Overview

| DTO Class                   | Purpose                                                       |
|----------------------------|---------------------------------------------------------------|
| `MusicianBasicInfoDTO`     | For creating/updating name, email, location, etc.            |
| `MusicianAboutDTO`         | Used to update the bio section                               |
| `MusicianPerformanceDTO`   | Captures musical traits (instrument, vocal range, etc.)      |
| `ExperienceDTO`            | Holds experience data: song title, singers, genre            |
| `SlotBookingDTO`           | Booking info: date, status, custom message                   |
| `RecommendationDTO`        | Recommendation source, content, and visibility               |
| `MusicianDashboardDTO`     | Aggregates all sections of the profile for display           |

---

## 📄 Controllers & API Endpoints

### 1. `MusicianDashboardController`
- `GET /musician/dashboard/{id}` → Returns full dashboard view (DTO)

### 2. `MusicianProfileEditController`
- `POST /musician/profile` → Create a new musician
- `PUT /musician/profile/{id}/basic` → Update basic info
- `PUT /musician/profile/{id}/about` → Update bio
- `PUT /musician/profile/{id}/performance` → Update performance info (creates if not exists)

### 3. `ExperienceController`
- `POST /musician/experience/create/{profileId}` → Add experience
- `PUT /musician/experience/update/{id}` → Update experience
- `DELETE /musician/experience/{id}` → Delete experience

### 4. `SlotBookingController`
- `POST /musician/slot/create/{profileId}` → Add slot
- `PUT /musician/slot/update/{id}` → Update slot
- `DELETE /musician/slot/{id}` → Delete slot

### 5. `RecommendationController`
- `POST /musician/recommendation/create/{profileId}` → Add recommendation
- `DELETE /musician/recommendation/{id}` → Delete recommendation

### 6. `ImageUploadController`
- `POST /musician/upload/profile-picture/{profileId}` → Upload profile image
- `POST /musician/upload/experience-poster/{experienceId}` → Upload poster
- `DELETE /musician/profile-picture/{profileId}` → Delete profile image
- `DELETE /musician/experience-poster/{experienceId}` → Delete poster
- `GET /musician/file/{filename}` → Serve uploaded file

---

## 🤝 Service Layer Responsibilities

| Service Class              | Role                                                       |
|---------------------------|-------------------------------------------------------------|
| `MusicianProfileService`  | Creates musician profiles                                  |
| `ExperienceService`       | Adds/updates/removes experience entries                     |
| `SlotBookingService`      | Manages slot creation, updates, and deletions              |
| `RecommendationService`   | Adds or deletes recommendations                             |
| `ImageStorageService`     | Handles upload, delete, and retrieval of images             |

---

## ⚠️ Exception Handling

- **`ResourceNotFoundException`**: Thrown when an entity is missing in DB
- **`GlobalExceptionHandler`**: Maps 404/500 to proper API messages

```json
{
  "timestamp": "...",
  "status": 404,
  "error": "Not Found",
  "message": "Profile not found",
  "path": "/musician/profile/99"
}
```

---

## 🚀 Running the App

1. Create PostgreSQL DB (e.g., `filmy_ai`)
2. Update `application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/filmy_ai
    username: postgres
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```
3. Run the app:
```bash
./mvnw spring-boot:run
```
4. Use Postman/TalendAPI to hit the endpoints

---

## 🌟 Bonus
- Code is modular and ready for extension
- Can be easily integrated with a React frontend
- Upload logic can be upgraded to AWS S3 or Firebase

---

## ✅ Author
**Tarun Ramapuram**  
| Backend Dev |


