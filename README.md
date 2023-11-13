## Angular 11, Spring Boot, Postgresql, SockJs/Stomp Stack

This project is solely for learning purposes. I tried both Kotlin and Java

## Set up (OLD)

Set database up and configure in **application.properties**

Start spring boot server: ```mvn string-boot:run```

or use Kotlin Server with docker

Start client server: ```ng serve <--port>```

## Set up Docker

just use ```docker-compose up```. Make sure your ports are free.


## Chat

In order to have full duplex between server and clients, we use Sockjs and STOMP to build the chat.

## Credits

This project is based on the repository of
bezkoder https://github.com/bezkoder/angular-11-spring-boot-jwt-authentication.git

Following features were already implemented:

* Basic code structure (frontend/backend)
* Registration based on JWT
* Routing
* Basic Roles
