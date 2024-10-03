# Pizzeria Application

This guide provides instructions on how to build and run a pizzeria simulation applitcation
written in Kotlin/Spring Boot application that allows customers to submit their email 
addresses along with a list of toppings they are interested in. 
It also provides an endpoint for retrieving the list of toppings and the number of 
unique customers who have requested each topping.

## Requirements

- Java 21
- Kotlin 1.5 or later
- Spring Boot 2.5 or later
- Gradle

## Building and Running the Application

- Build the Project
  - ./gradlew clean build
- Run the Application
  - ./gradlew bootRun

## Endpoint Examples

- Get Toppings
  - http://localhost:8080/api/customers/toppings
- Submit Toppings
  - http://localhost:8080/api/customers/submit?email=someEmail@place.com&toppings=pepperoni,onion,anchovies

## H2 Database Console

- Access the console at http://localhost:8080/h2-console with the following settings
  - JDBC URL: jdbc:h2:mem:testdb
  - Username: sa
  - Password: password