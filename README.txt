#### Starting application on Windows OS
in folder back-end open PowerShell
run commands:
	mvn clean install
	mvn spring-boot:run
in folder front-end open PowerShell
run commands:
	npm install
	npm start
application will run on http://localhost:3000

Database files:
	./back-end/analyserDB.lock.db
	./back-end/analyserDB.mv.db
Human readable DB access:
	http://localhost:8080/console
Login to database:
	Driver Class:	org.h2.Driver
	JDBC URL:	jdbc:h2:file:./analyserDB;
	User Name:	sa
	Password:	*leave empty*

####Technologies
 - Java 1.8
 - Spring-boot 2.3.2
 - H2-Database 1.4.200
 - Maven 4.0.0
 - Lombok 1.18.12