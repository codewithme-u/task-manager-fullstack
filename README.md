# 🧠 Team Task Manager (Full Stack)

A full-stack task management web application built using **Spring Boot, MySQL, and HTML/CSS/JavaScript** with role-based access control.

This system enables teams to collaborate, manage projects, assign tasks, and track progress efficiently — similar to tools like Trello and Asana.

---

## 🌍 Live Demo

👉 https://task-manager-fullstack-production-b100.up.railway.app/auth.html

---

## 🚀 Features

### 🔐 Authentication

- User Signup & Login  
- Secure password hashing using **BCrypt**

---

### 👥 Role-Based Access

#### 👑 Admin

- Create projects  
- Add/remove members  
- Assign tasks  
- View all tasks and analytics  

#### 👤 Member

- View assigned tasks  
- Update task status  

---

### 📁 Project Management

- Create and manage projects  
- Add members to projects  
- View project-wise tasks  

---

### ✅ Task Management

- Create tasks (title, description, due date, priority)  
- Assign tasks to users  
- Update task status:

  - To Do  
  - In Progress  
  - Done  

---

### 📊 Dashboard

- Total tasks overview  
- Tasks by status  
- Tasks per user  
- Overdue task tracking  

---

## 🛠 Tech Stack

- **Backend:** Spring Boot (REST APIs)  
- **Database:** MySQL  
- **Frontend:** HTML, Bootstrap, JavaScript  
- **Security:** BCrypt Password Encoding  
- **Build Tool:** Maven  
- **Deployment:** Railway  

---

## ⚙️ Setup Instructions

### 1. Clone Repository

```bash
git clone https://github.com/codewithme-u/task-manager-fullstack.git
cd task-manager-fullstack
````

---

### 2. Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/taskmanager
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 3. Run Backend

```bash
mvn spring-boot:run
```


### 4. Access Application

Open in browser:

```bash
http://localhost:8080/auth.html
```

---

## 🔗 API Overview

| Method | Endpoint              | Description        |
| ------ | --------------------- | ------------------ |
| POST   | `/auth/signup`        | Register user      |
| POST   | `/auth/login`         | Login user         |
| GET    | `/projects`           | Get projects       |
| POST   | `/tasks`              | Create task        |
| GET    | `/tasks/project/{id}` | Get project tasks  |
| PUT    | `/tasks/{id}`         | Update task status |

---

## 📸 Screenshots

![Login](https://github.com/user-attachments/assets/3b2de9a3-3e50-4122-b3a7-a9999d7cf1da)

![Admin](https://github.com/user-attachments/assets/cafc9fa2-a630-447d-a480-eb022fbe9ffe)

![Member](https://github.com/user-attachments/assets/8de04d03-ef85-4792-bedf-73d021b79af7)

---

## 📌 Future Improvements

* 🎨 UI/UX enhancements (modern dashboard)
* ⚡ Real-time updates using WebSockets
* 🔔 Notification system
* 📎 File attachments in tasks

---

## 👨‍💻 Author

**Saksham Pokhrel**
🔗 [https://github.com/codewithme-u/task-manager-fullstack](https://github.com/codewithme-u/task-manager-fullstack)

