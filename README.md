# 🐘 Master–Slave Spring Boot Project (Java 21)

This project demonstrates a **Master–Slave PostgreSQL setup** integrated with a **Spring Boot 3 (Java 21)** application.  
It includes automatic **read/write routing** using AOP and `AbstractRoutingDataSource`.

---

## 🏗 Project Overview

- 🔵 **PostgreSQL Master (Primary) Database**
- 🟢 **PostgreSQL Slave (Read Replica) Database**
- 🚀 **Spring Boot 3 Application**
- 🔀 **Automatic Read/Write Routing using AOP**
- 📡 **Streaming Replication**

The application automatically routes:

- `INSERT / UPDATE / DELETE` → Master
- `SELECT` → Slave

---

## 🛠 Technologies Used

- Java 21
- Spring Boot 3
- Spring Data JPA
- PostgreSQL (Master + Slave)
- HikariCP connection pool
- AOP for automatic routing

---

## 📂 Project Structure
com.mycompany.masterslave
│
├── MasterSlaveSetupApplication.java
├── controller/
│ └── MasterSlaveController.java
├── service/
│ ├── MasterSlaveService.java
│ └── impl/
│ └── MasterSlaveServiceImpl.java
├── repository/
│ └── MasterSlaveRepository.java
├── entity/
│ └── MasterSlave.java
├── dto/
│ ├── MasterSlaveRequest.java
│ └── MasterSlaveResponse.java
└── config/
├── DataSourceType.java
├── DataSourceContextHolder.java
├── RoutingDataSource.java
├── DataSourceConfig.java
└── DataSourceRoutingAspect.java

---


---

## ⚙️ Setup & Configuration

### PostgreSQL Master & Slave

1. Install **Ubuntu Server** on two VMs.
2. Install PostgreSQL on both.
3. Configure **Master**:
    - Enable WAL settings (`wal_level = replica`, `max_wal_senders`, etc.)
    - Allow replication connections (`pg_hba.conf`)
    - Create replication user
4. Configure **Slave**:
    - Take base backup from Master
    - Add `standby.signal` for streaming replication
5. Start Slave and verify replication

> **References:**  
> [PostgreSQL Streaming Replication](https://www.postgresql.org/docs/current/warm-standby.html)

---

### Spring Boot Application

**application.yml**

```yaml
spring:
  datasource:
    master:
      url: jdbc:postgresql://<master-ip>:5432/testdb
      username: postgres
      password: postgres

    slave:
      url: jdbc:postgresql://<slave-ip>:5432/testdb
      username: postgres
      password: postgres

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

server:
  port: 7887

```

🔀 How Read/Write Routing Works

API request hits Controller.

Controller calls Service method.

@Transactional annotation is detected by AOP.

Routing logic:

readOnly = true → SLAVE

Otherwise → MASTER

RoutingDataSource directs query to the correct database.

```table
| Method | Endpoint                 | Description               |
| ------ | ------------------------ | ------------------------- |
| POST   | `/api/master-slave`      | Create record (MASTER)    |
| GET    | `/api/master-slave/`     | Fetch all records (SLAVE) |
| PUT    | `/api/master-slave/{id}` | Update record (MASTER)    |
| DELETE | `/api/master-slave/{id}` | Delete record (MASTER)    |
```

- 🔗 **LinkedIn Profile:** [Your LinkedIn](https://github.com/viku01999/postgresql-master-slave-setup)
- 🔗 **Java Repository:** [Master–Slave Spring Boot Project](https://github.com/viku01999/master-slave-setup)