# 🏋️ TrainingWork Microservice

This microservice is part of a larger Gym CRM system and is responsible for managing trainer work assignments.  
It provides endpoints to add, accept, delete, and retrieve training work associated with trainers.  
The service is built with **Spring Boot**, uses **REST API** architecture, and is integrated with **Swagger/OpenAPI** for documentation and **SLF4J** for logging.

---

## 📦 Features

- ✅ Add new training work entries for trainers
- 🧾 Accept or reject submitted training work
- ❌ Delete training work records
- 🔍 Retrieve trainer work info by username
- 🔐 Basic validation and error handling
- 📄 Swagger/OpenAPI documentation support
- 🧪 Transaction ID-based logging for traceability

---

## ⚙️ Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Jakarta Validation**
- **Lombok**
- **SLF4J / Logback**
- **Swagger / OpenAPI 3**
- **Maven**

---

## 🚀 API Endpoints

### `POST /training-work/add`
Add new training work record for a trainer.

Request Body:
{
  "username": "trainer.username",
  "trainingName": "Upper Body Workout",
  "trainingDate": "2025-04-15",
  "trainingDuration": 90
}



### 'POST /training-work/accept'

Headers:
Transaction-ID: abc-123-xyz

Request Body:
{
  "username": "trainer.username",
  "trainingName": "Upper Body Workout",
  "trainingDate": "2025-04-15",
  "trainingDuration": 90
}



###DELETE /training-work/delete
Request Body:
{
  "username": "trainer.username",
  "trainingName": "Upper Body Workout",
  "trainingDate": "2025-04-15"
}


###GET /training-work/TrainingWork/{username}
GET /training-work/TrainingWork/john.doe



###Swagger API Docs
http://localhost:8080/swagger-ui.html



Author
Stanislav Donets
📧 stanislavdonetc@gmail.com
🔗 LinkedIn
