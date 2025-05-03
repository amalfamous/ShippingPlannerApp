
````markdown
# Shipping Planner - Backend

## 📦 Project Description

This is the backend system for the **Shipping Planner** application. It provides RESTful APIs to calculate optimal transportation routes between different geographical nodes (cities, ports, rail stations, etc.) based on criteria such as **cost**, **time**, or **CO₂ emissions**. The system also handles dynamic disruptions (perturbations) that affect travel times and costs.

## ⚙️ Technologies Used

- Java 20
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL / H2 (configurable)
- Lombok
- OpenStreetMap (for route referencing)
- Postman (for API testing)

## 🧩 Features

- CRUD operations for:
  - `Node` (cities, ports, etc.)
  - `Edge` (connections between nodes)
  - `Perturbation` (disruptions that impact routes)
- Dynamic route calculation based on:
  - Transport modes (`ROAD`, `RAIL`, `AIR`, `SEA`)
  - Priority (`COST`, `TIME`, `EMISSIONS`)
- Clean separation between DTOs and entities using converters
- Robust error handling and validation

## 🚀 Getting Started

### Prerequisites

- Java 20
- Maven
- postgresql database

### Setup

```bash
git clone https://github.com/yourusername/shipping-planner-backend.git
cd shipping-planner-backend
````

### Configuration

Edit `src/main/resources/application.properties`:


### Run the App

```bash
mvn spring-boot:run
```

## 📫 API Usage

Test endpoints with Postman or curl.

Example route request:

```
POST /api/routes/calculate
{
  "originCode": "CAS",
  "destinationCode": "ALG",
  "transportModes": ["SEA", "ROAD"],
  "priority": "TIME"
}
```

Returns the best path with total cost, time, and rationale.

## 🛠️ Developer Notes

* Use DTO converters (`RouteRequestConverter`, etc.) to keep the domain model clean.
* Perturbations affect route cost/duration dynamically based on current date/time.
* Use `/h2-console` (if enabled) to inspect the database.

## 🧑‍💻 Author

* \AMAL FAMOUS– Backend developer
* GitHub: [[github.com/amalfamous](https://github.com/amalfamous)
](https://github.com/amalfamous)
## 📄 License

This project is open-source and available under the MIT License.

