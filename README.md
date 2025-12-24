# 🚀 LeetCode – Java & PostgreSQL Solutions

This repository documents my journey solving **LeetCode problems** using **Java** and **PostgreSQL**.

## 📌 Why Java?

I solve algorithm problems (arrays, hash maps, trees, dynamic programming, graphs, etc.) in **Java** because it aligns with my tech stack:

* **Java** & **Spring Boot**
* **Angular**
* **Docker** & **Podman**
* **PostgreSQL**

Java solutions are organized by difficulty:

```
java/
  ├── easy/
  ├── medium/
  └── hard/
```

---

## 🐘 Why PostgreSQL for SQL Problems?

LeetCode uses a simplified MySQL-like SQL engine, but I prefer solving SQL problems locally on **Fedora** using:

* **PostgreSQL 16+**
* `psql` in terminal
* **IntelliJ IDEA** / **Zed** for `.sql` files

To avoid table name conflicts between problems (e.g., `Person`, `Employee`, `Customer`), I create a **dedicated schema per problem**:

* `lc_175` → Problem 175
* `lc_176` → Problem 176
* `lc_177` → Problem 177
* … up to `lc_1000+`

Each problem is isolated and can be tested independently.

---

## 🛠 PostgreSQL Setup (One-Time)

### 1. Create the LeetCode Database

```bash
createdb leetcode_db -O $USER
```

### 2. Connect to PostgreSQL

```bash
psql -U $USER leetcode_db
```

### 3. Create Schemas for All LeetCode SQL Problems

```sql
DO $$
BEGIN
    FOR i IN 1..1000 LOOP
        EXECUTE format('CREATE SCHEMA IF NOT EXISTS lc_%s AUTHORIZATION current_user', i);
    END LOOP;
END $$;
```

Your local environment is now ready for **any SQL problem**! 🎉

---

## 📂 Repository Structure

```
leetcode/
│
├── backend/                    # Spring Boot API
│   ├── src/
│   ├── Dockerfile
│   └── build.gradle
│
├── java/                       # Java algorithm solutions
│   ├── easy/
│   ├── medium/
│   └── hard/
│
└── postgresql/                 # PostgreSQL SQL solutions
    ├── easy/
    │   └── 175_combine_two_tables/
    │       └── solution.sql
    ├── medium/
    │   └── 176_second_highest_salary/
    │       └── solution.sql
    └── hard/
```

Each SQL problem has its own folder containing **only** the solution (`solution.sql`).  
Table creation and data insertion are done manually in `psql` for each session.

---

## 🐳 Deployment Setup (OCI VM + Podman)

The backend runs as a **rootless Podman container** on Oracle Cloud Infrastructure (OCI).

### Critical: Enable User Linger

When running Podman containers as a non-root user, you **must** enable `loginctl linger` to prevent containers from stopping when SSH sessions end:

```bash
ssh oci-vm
loginctl enable-linger $USER
```

**Without this**, containers will be killed when you disconnect from SSH. This is because rootless Podman containers run under user sessions, which terminate when the SSH connection closes.

### Deploy Backend

```bash
# Pull latest image
podman pull ghcr.io/techthordev/springboot-backend:latest

# Stop and remove old container
podman rm -f container-springboot-backend

# Run with environment variables
podman run -d \
  --name container-springboot-backend \
  --restart=always \
  --env-file /home/opc/backend/.env \
  -p 8080:8080 \
  ghcr.io/techthordev/springboot-backend:latest
```

---

## 📌 Example: Running a SQL Solution Locally

### 1. Switch to the Problem Schema

```sql
SET search_path TO lc_175;
```

### 2. Create Tables and Insert Test Data

*(Copied from LeetCode problem description — not included in repo)*

```sql
CREATE TABLE Person (
    PersonId INTEGER,
    FirstName VARCHAR,
    LastName VARCHAR
);

CREATE TABLE Address (
    AddressId INTEGER,
    PersonId INTEGER,
    City VARCHAR,
    State VARCHAR
);

INSERT INTO Person VALUES (1, 'Allen', 'Wang'), (2, 'Bob', 'Johnson');
INSERT INTO Address VALUES (1, 2, 'New York', 'NY');
```

### 3. Run the Solution

```sql
\i postgresql/easy/175_combine_two_tables/solution.sql
```

---

## 🎯 Goals

* ✅ Practice **Java algorithms** & **data structures**
* ✅ Master **SQL problem solving** with PostgreSQL
* ✅ Gain deep **PostgreSQL** experience on Fedora
* ✅ Build production-ready **Spring Boot** backend
* ✅ Deploy with **Docker/Podman** on cloud infrastructure
* ✅ Prepare for professional **backend development** (Java + Spring Boot + PostgreSQL)

---

## 🛠 Tech Stack

| Category | Technology |
|----------|------------|
| **Language** | Java 25 |
| **Framework** | Spring Boot 4.0 |
| **Database** | PostgreSQL 17 (Supabase) |
| **Container** | Podman (rootless) |
| **CI/CD** | GitHub Actions |
| **Deployment** | Oracle Cloud Infrastructure (OCI) |
| **Frontend** | Angular + Netlify |
| **SSL** | Let's Encrypt (Certbot) |

---

## 📝 License

**MIT License**

This repository is for educational purposes. LeetCode problems are property of LeetCode.

---
