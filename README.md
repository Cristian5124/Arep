# Java Web Server

This project implements a simple web server in Java that can handle **GET** and **POST** requests, as well as serve static files (HTML, CSS, JS, and images). **Maven** is used for project management.

## Getting Started

These instructions will help you get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

You need to have installed:

- [Java 21+](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/)

Verify the installations with:

```bash
java -version
mvn -version
```

### Installing

1. Clone the repository:
   ```bash
   git clone https://github.com/Cristian5124/Arep.git
   cd servidor-web-java
   ```

2. Compile and package the project with Maven:
   ```bash
   mvn clean compile
   mvn clean package

   ```

3. Run the server:
   ```bash
   java -jar target/servidor-web-1.0.0.jar
   ```

4. Open in your browser:
   ```
   http://localhost:8080
   ```

## Running the tests

To execute the included unit tests:

```bash
mvn test
```

Reports are generated in `target/surefire-reports`.

## Deployment

The server can be deployed on any machine with **Java 21+**.  
It is recommended to use a process manager like **systemd** or **Docker** for production environments.

## Built With

* [Java](https://www.oracle.com/java/) - Programming language
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Cristian Polo** - [GitHub](https://github.com/Cristian5124)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

* Simple implementation of a web server in Java.

## Postman Tests

Get Method

<img width="1482" height="445" alt="image" src="https://github.com/user-attachments/assets/49fa7601-20b3-446a-a2e5-b675d4ab0409" />

Post Method

<img width="1490" height="445" alt="image" src="https://github.com/user-attachments/assets/855b77d1-49dc-410e-8cb6-3df682667d9d" />

Get Api Method

<img width="1491" height="449" alt="image" src="https://github.com/user-attachments/assets/c7be0cf1-16fb-4674-838d-aeb981d67864" />

## Tests

<img width="1466" height="394" alt="image" src="https://github.com/user-attachments/assets/6c635f84-2af3-4fc8-9d7a-c37d17654490" />


