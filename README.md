

# Task Manager REST API
This project provides a REST API for a task manager. The API offers two endpoints:

- Add a task
- List all tasks

## Prerequisites
- Java 13 or higher
- Maven 3.2.0 or higher

## Endpoints
### Add a Task
Use the following endpoint to add a new task:

```bash
POST http://localhost:8080/taskmanager/task/create

{
 "title":"Time Management",
 "description": "Adding time tracking",
 "isCompleted": "False"
}
```

### List all Tasks
Use the following endpoint to list all available tasks:

```bash
GET http://localhost:8080/taskmanager/tasks/
```

## Getting Started

### Terminal
1. Download the source code and navigate to the task manager directory.
2. Ensure that Maven and Java environment variables are set.
3. Execute the following command to clean and install the project:

``` bash
mvn clean install
```
4. Start the microservice using the following command:
``` bash
mvn spring-boot:run
```

### IDE
1. Set up the project as an existing Maven project in your IDE (STS, Eclipse, IntelliJ Idea).
2. Right-click on the project, and click on Maven -> Reload Project.
3. Right-click on the project, and click on Maven -> Download Sources.
4. Right-click on the project, and click on Run As -> Maven Clean and Maven Install.
5. Right-click on the project, and click on Run As -> Spring Boot App.
