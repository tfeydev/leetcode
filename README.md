# 🚀 LeetCode – Java & PostgreSQL Solutions

This repository documents my LeetCode solutions in **Java** and **PostgreSQL**.

## 📌 Why Java?

I solve algorithm problems (arrays, hash maps, trees, dynamic programming, graphs, etc.) in **Java**,
since my main tech stack focuses on:

* **Java**
* **Spring Boot**
* **Angular**
* **Docker**
* **PostgreSQL**

Java solutions are organized in classic folders:

```
java/
  ├── easy/
  ├── medium/
  └── hard/
```

---

## 🐘 Why PostgreSQL for SQL problems?

LeetCode uses a simplified, MySQL-like SQL engine.
I solve SQL problems locally on **Fedora** using:

* PostgreSQL (v16+)
* `psql` in terminal
* IntelliJ CE / Zed for `.sql` files

To avoid table name conflicts between problems
(e.g., `Person`, `Employee`, `Customer`), I create a **dedicated schema per problem**:

* `lc_175`
* `lc_176`
* `lc_177`
* … up to `lc_626`

Each problem is isolated and can be tested independently.

---

## 🛠 PostgreSQL Setup (one-time)

### 1. Create the LeetCode database

```bash
createdb leetcode_db -O $USER
```

### 2. Open PostgreSQL

```bash
psql -U $USER leetcode_db
```

### 3. Create a schema for each LeetCode SQL problem

```sql
DO $$
BEGIN
    FOR i IN 1..1000 LOOP  -- adjust range as needed
        EXECUTE format('CREATE SCHEMA IF NOT EXISTS lc_%s AUTHORIZATION current_user', i);
    END LOOP;
END $$;
```

Now your local environment is ready for **any SQL problem**.

---

## 📂 Repository Structure

```
leetcode/
│
├── java/
│   ├── easy/
│   ├── medium/
│   └── hard/
│
└── postgresql/
    ├── 175_combine_two_tables/
    │   └── solution.sql
    ├── 176_second_highest_salary/
    │   └── solution.sql
    └── ...
```

Each SQL problem has its own folder containing **only** the solution (`solution.sql`).
Table creation and data insertion are done manually in `psql` for each session.

---

## 📌 Example: Running a SQL solution locally

### 1. Switch to the problem schema

```sql
SET search_path TO lc_175;
```

### 2. Create tables and insert data manually

(Copied from LeetCode description — **not included in repo**)

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

### 3. Run the solution

```sql
\i postgresql/175_combine_two_tables/solution.sql
```

---

## 🎯 Goal of this repository

* Practice Java algorithms
* Master SQL problem solving
* Gain deep PostgreSQL experience on Fedora
* Prepare for professional backend development
  (Java + Spring Boot + PostgreSQL)

