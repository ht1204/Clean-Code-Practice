# Clean Code Practice

## Overview

This repository contains two implementations: in JavaScript and Java, of a task that involves fetching, parsing, and processing JSON data from an external API. The task demonstrates key software architecture principles like Single Responsibility Principle (SRP), Open/Closed Principle (OCP), and clean error handling.

## Solved Problems
### JavaScript
Inconsistent Error Handling: Previous version presented callback-based error handling. This was refactored to use async resources for better control and readability. \
Single Responsibility Principle (SRP): Each function was refactored to handle only one responsibility, such as fetchData, parseData, and processData. \
Complex Conditionals: The parseData function was simplified by introducing a strategy map, removing the need for multiple conditionals.

### Java
Callback-based Error Handling: Callbacks were replaced by CompletableFuture to handle asynchronous operations, making error handling much more readable and consistent. \
Tight Coupling: Previously, the fetching, parsing, and processing logic was all tightly coupled. These were separated into different classes to adhere to SRP and to improve modularity and make possible separation of concerns, single responsability and open-closed principles.

## Project Structure
The repository is divided into two main directories:

JavaScript Implementation: Located in the `js/` folder.
Java Implementation: Located in the `java/` folder.

Each implementation to fix is located in `v_0` folder.
Proposal implementation of task solution is located in `v_1` folder.

## Tech Stack
#### JavaScript
Language: Node.js (JavaScript)
Tech:
Node.js: Runtime for executing JavaScript code outside a browser.
XHR: Used for HTTP requests in the fetchData function.
DOMParser: Used to parse XML in case the response is XML format.

#### Java:
Language: Pure Java (JDK)
Tech:
HttpURLConnection: Used for making HTTP requests.
CompletableFuture: Used for asynchronous operations.
Manual JSON formatting: For pretty-printing JSON in a readable way, using only native Java features.

## How to Run
JavaScript Project (Node.js): `npm i` \
Node `v18` at least, `nvm` recommended to use.

Java Project: \
Navigate to the java/ folder: `cd java/` 

Compile the Java source code using the following command: 

`javac -d bin src/com/library/**/*.java` 

Run the Java program from the bin directory: `java -cp bin com.library.main.Main`


Software Architecture Workshop, 2024



