# Mini Store 

**Mini Store** is a Java-based project that simulates the basic functionality of an online store to manage products, customers, and purchases in a simple way, applying OO principles.

## Main Features

- **Lightweight encapsulation** to create, list, update, and delete products.
- **Customer management** to register and manage customer information.
- **Automated persistence** for saving sales and enable sales recordability.
- **Purchase simulation** with the ability to select products, enter quantity, and issue invoices.
- **Product rating functionality**: users/clients, and aggregate feedback.
- **Architecture** for database properties using DataSource for DB layer.

## Technologies Used

| Technology | Description |
|------------|-------------|
| **Java 17+** | Main programming language |
| **Maven** | Build and dependency management |
| **JPA/Hibernate** | Database ORM for data persistence |
| **PostgreSQL** | Relational database for data management |
| **H2 Database** | In-memory DB for fast tests |
| **Git & GitHub** | Version control and repository on the Web |

## Installation and Execution

### 1. Clone the repository

```bash
git clone https://github.com/Arb3laez/Mini-tienda.git
```

### 2. Navigate into the project folder

```bash
cd Mini-tienda
```

### 3. Build the project

```bash
mvn clean install
```

### 4. Run the application

```bash
mvn exec:java      

```

**Or** using a script class:

```bash
java -cp target/Mini-tienda-1.0-SNAPSHOT.jar com.yourcompany.Main
```

##  Structure

```
Mini-tienda/
├── src/
│   ├── main/
│   │   ├── java/              # Source code (models, controllers, DAO/s)
│   │   └── db.properties      # Configuration and property files
│   └── test/                  # Unit tests
├── pom.xml                    # Maven configuration
└── README.md                  # Package documentation
```

## Example of Use

1. **Register a new product**
2. **Check available inventory**
3. **Register a customer and process a purchase**


