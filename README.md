## JavaFX with Spring Boot using MongoDB.
JavaFX is a Java library that is used to develop Desktop applications as well as Rich Internet Applications (RIA). The applications built in JavaFX, can run on multiple platforms including Web, Mobile and Desktops.


## Application Name : TodoList

## Dependencies

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>

<!-- https://mvnrepository.com/artifact/org.openjfx -->
<dependency>
	<groupId>org.openjfx</groupId>
	<artifactId>javafx-controls</artifactId>
	<version>15.0.1</version>
</dependency>
<dependency>
	<groupId>org.openjfx</groupId>
	<artifactId>javafx-media</artifactId>
	<version>15.0.1</version>
</dependency>
<dependency>
	<groupId>org.openjfx</groupId>
	<artifactId>javafx-graphics</artifactId>
	<version>15.0.1</version>
</dependency>
<dependency>
	<groupId>org.openjfx</groupId>
	<artifactId>javafx-fxml</artifactId>
	<version>15.0.1</version>
</dependency>
```

## Database Configuration and other configuration.
```
# spring config
spring.main.banner-mode=off
spring.application.name=TodoList
spring.profiles.active: production

# logger config
logging.file.path=logs/
logging.file.name=logs/todo_list.log
logging.level.root=info

# MONGODB (MongoProperties)
spring.data.mongodb.uri=mongodb://localhost:27017/todoList

```

## Image
![Image 1](https://github.com/jeetendra29gupta/javafx-mongodb/blob/master/src/main/resources/image/Screenshot%20from%202020-11-02%2013-23-42.png)
![Image 2](https://github.com/jeetendra29gupta/javafx-mongodb/blob/master/src/main/resources/image/Screenshot%20from%202020-11-02%2013-24-07.png)

## JavaFX Tutorial Source
- [Javatpoint](https://www.javatpoint.com/javafx-tutorial)
- [Tutorialspoint](https://www.tutorialspoint.com/javafx/index.html)
- [Jenkov.com](http://tutorials.jenkov.com/javafx/index.html)
