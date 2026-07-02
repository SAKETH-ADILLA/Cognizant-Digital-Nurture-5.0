# Cognizant-Digital-Nurture-5.0

A repository containing all hands-on exercises and assignments completed as part of the **Cognizant Digital Nurture 5.0** program.

---

## 📁 Repository Structure

```
Cognizant-Digital-Nurture-5.0/
├── Algorithms_Data_Structures/
├── Design-Principles-and-Design-Patterns/
├── PLSQL_Exercises/
├── Spring-Core-and-Maven/
└── Spring-JPA-and-Hibernate/
    ├── 1. handson-by-exercise/
    ├── 2. handson-by-exercise/
    ├── 3. handson-by-exercise/
    └── SpringDataJPA_Hibernate_exercises/
```

---

## 📚 Modules

### 1. Algorithms & Data Structures
Covers fundamental algorithms and data structure implementations.

### 2. Design Principles & Design Patterns
Covers SOLID principles and common design patterns like Creational, Structural and Behavioral patterns.

### 3. PL/SQL Exercises
Covers Oracle PL/SQL programming including stored procedures, functions, cursors, triggers and exception handling.

### 4. Spring Core & Maven
Covers Spring Core concepts including Dependency Injection, Bean configuration, Spring MVC and Maven project setup.

### 5. Spring JPA & Hibernate
Covers Object-Relational Mapping using Hibernate and Spring Data JPA.

#### Hands-on 1 — Spring Data JPA Basics
- Setting up Spring Boot with Spring Data JPA
- Configuring datasource and Hibernate properties
- Creating `Country` entity and `CountryRepository`
- Implementing `CountryService` with `getAllCountries()`

#### Hands-on 2 — Query Methods & ORM Relationships
- Spring Data JPA Query Methods (containing, starting with, sorting)
- Stock data queries (between dates, greater than, top N)
- `@ManyToOne`, `@OneToMany`, `@ManyToMany` mappings
- `FetchType.EAGER` vs `FetchType.LAZY`
- `@JoinColumn`, `@JoinTable`, `mappedBy`

#### Hands-on 3 — HQL, Native Queries & Criteria API
- HQL vs JPQL introduction
- `@Query` annotation with HQL and `fetch` keyword
- Aggregate functions (`AVG`, `SUM`, `COUNT`)
- Native Queries with `nativeQuery = true`
- Criteria Query for dynamic filtering

#### SpringDataJPA Hibernate Exercises — Employee Management System
- **Exercise 1** — Project setup with H2 Database, Spring Data JPA, Lombok
- **Exercise 2** — JPA Entities (`Employee`, `Department`) with `@OneToMany` / `@ManyToOne`
- **Exercise 3** — Repositories extending `JpaRepository` with derived query methods
- **Exercise 4** — REST CRUD endpoints using `@RestController`
- **Exercise 5** — `@Query`, `@NamedQuery`, `@Param` annotations
- **Exercise 6** — Pagination (`Page`, `Pageable`) and Sorting (`Sort`)
- **Exercise 7** — Entity Auditing (`@CreatedBy`, `@LastModifiedBy`, `@CreatedDate`, `@LastModifiedDate`)
- **Exercise 8** — Projections (interface-based, class-based DTO, `@Value` SpEL)
- **Exercise 9** — Multiple DataSource configuration
- **Exercise 10** — Hibernate-specific features (`@DynamicUpdate`, `@Cache`, batch processing)

---

## 🛠️ Technologies Used

- Java 11
- Spring Boot 2.7.x
- Spring Data JPA
- Hibernate ORM
- MySQL
- H2 In-Memory Database
- Maven
- Lombok

---

## 👤 Author

**Saketh Adilla**  
Cognizant Digital Nurture 5.0 Program
