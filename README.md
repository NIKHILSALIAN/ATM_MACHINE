Project Name - ATM Machine Application


Overview
This project is an ATM Machine Application developed using Java and Spring Boot, with a focus on backend API development, user authentication, and core banking features such as account creation, balance inquiry, deposits, and withdrawals. The application implements RESTful APIs and follows microservices principles for modularity and scalability.

Features
Account Creation: Users can securely create new bank accounts with unique identifiers.
User Authentication: Secure login and logout functionality for account holders.
Balance Inquiry: Enables users to check their current balance.
Deposit and Withdrawal: Supports money deposit and withdrawal operations.
Notification System: Provides instant feedback to users after transactions.


Technologies Used
Java: Core programming language.
Spring Boot: For building RESTful APIs and managing backend services.
HTML, CSS, JavaScript: For frontend user interface.
MySQL: Database for storing user data and transaction details.
Getting Started
Prerequisites
Java (version 11 or later)
Spring Boot (v2.5 or later)
MySQL (or any preferred SQL database)
Maven (for dependency management)


1)Installation & Setup:
Clone the repository:git clone https://github.com/your-username/atm-machine-application.git
cd atm-machine-application

2)Configure Database:

Open application.properties or application.yml in the src/main/resources directory.
Update the database configuration to match your local MySQL setup.

spring.datasource.url=jdbc:mysql://localhost:3306/atm_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password


3)Run the Application:
Start the Spring Boot application:
bash : mvn spring-boot:run
Access the API endpoints via http://localhost:8080.


Usage:

Method	Endpoint	Description
POST	/api/atm/createAccount	Create a new account
POST	/api/atm/login	Login to an account
GET	/api/atm/checkBalance	Check account balance
POST	/api/atm/deposit	Deposit money into an account
POST	/api/atm/withdraw	Withdraw money from an account


