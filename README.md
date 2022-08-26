
Definition: The Car Rental application is a web application which is able to be rent a car
directly by using a web browser in your computer, phone, tablet, or mobile device. There will be
different types of user in the CarRental application. While unregistered users are able to see
available cars’ information, these users are able to register and login into application. The users who
have   Customer   Role   are   able   to   make   a   reservation   for   a   car   and   manage   their   own   user
information. In addition to these. administrator of the CarRental application can manage the users,
cars, reservations and create some reports.

 GREENRENT WEB APPLICATION CUSTOMER REQUIREMENTS
 
 1. CarRental Application must be a web application.
2. The application must have user friendly UI. (Non functional requirement)
3. The application must be secure. (Non functional requirement)
4. Language of the application must be English. (Non functional requirement)
5. The application must display response of user request  nearby for 5 seconds. (Non functional
requirement)
6. Anonymous users who will make a process  should be able to register this application with 
their own information
7. There should be able two types of roles for authorized user: Customer, Admin
8. Registered user is able to have at least one role or all roles
9.  Anonymous user should be able to register to the application
10.  Registered user should be able to login to the application after registration
11.  Authenticated user should be able to get its own user information
12.  Authenticated user should be able to update its own user information
13.  Authenticated user should be able to update its own password
14.  User who has the admin role should be able to get all users’ information
15.  User who has the admin role should be able to get a user information
16.  User who has the admin role should be able to update a user information
17.  User who has the admin role should be able to delete a user
18.  Anonymous users should be able to get all cars’ information
19.  Anonymus users should be able to get a car’s information
20.  User who has the admin role should be able to add a car
21.  User who has the admin role should be able to upload a car image
22.  User who has the admin role should be able to get all cars’ image
23.  Anonymous user should be able to get a car’s image
24.  Anonymous user should be able to display a car’s image
25.  User who has the admin role should be able to update a car with an car image
26.  User who has the admin role should be able to delete a car
27.  User who has the admin role should be able to delete a car
28.  Authenticated user should be able to make a reservation for a car
29.  Authenticated user should be able to check availability of a car between selected datetime
30.  User who has the admin role should be able to add a reservation for a car and a user
31.  User who has the admin role should be able to get all reservations’ information
32.  User who has the admin role should be able to get a reservation information
33.  User who has the admin role should be able to get a user’s reservations’ information
34.  Authenticated user should be able to get its own reservation information
35.  Authenticated user should be able to get its own reservations’ information
36.  User who has the admin role should be able to update a reservation
37.  User who has the admin role should be able to delete a reservation
38.  User who has the admin role should be able to get all users’ information in an excel report
39.  User who has the admin role should be able to get all cars’ information in an excel report
40.  User who has the admin role should be able to get all reservations’ information in an excel 
report

In the Unified Modeling Language (UML), a use case diagram can
summarize the details of your system's users (also known as actors) and their interactions with the
system. A UML use case diagram is the primary form of system/software requirements for a new
software program underdeveloped. Use cases specify the expected behavior (what), and not the
exact method of making it happen (how). Use cases once specified can be denoted both textual and
visual representation (i.e. use case diagram). A key concept of use case modeling is that it helps us
design a system from the end user's perspective. It is an effective technique for communicating
system behavior in the user's terms by specifying all externally visible system behavior.

![Graphic1](https://user-images.githubusercontent.com/101723854/186869019-1c6fceb5-a68f-4f78-bc21-fb2f0695a9dd.png)
![Graphic2](https://user-images.githubusercontent.com/101723854/186869271-f3e184a1-7e69-41e0-b772-ec4bb299916e.png)
![Graphic3](https://user-images.githubusercontent.com/101723854/186869356-87144c08-b5be-49e7-a11e-854dd20b9f82.png)
![Graphic4](https://user-images.githubusercontent.com/101723854/186869381-9e74655f-15d2-43ad-87fb-e6c9ffaec861.png)
![Graphic5](https://user-images.githubusercontent.com/101723854/186869405-36484cd8-abaa-4199-906d-3b8de5bc66ad.png)
![Graphic6](https://user-images.githubusercontent.com/101723854/186869433-0e85e485-49c2-4241-a07d-2beb14af4e5f.png)


Entities and Relations Between Entity in the Application
![Relations](https://user-images.githubusercontent.com/101723854/186869543-9194f6c7-0193-43c9-a91b-8426133c7fed.png)

•One User can have many roles and One Role can belongs to Many User (Many To Many 
relation)
•One Reservation has a User (One To One relation)
•One Reservation has a Car (One To One relation)
•One Car can have many ImageFile and ImageFile can belongs to Many Car (Many To Many
relation)
•Role has a RoleType (Enumeration)
•Reservation has a ReservationStatus (Enumeration)


THE TOPICS THAT YOU SHOULD REVIEW BEFORE STARTING PROJECT

1. Core Java: OOPS, classes, enums, interfaces, exception handling, collections, stream 
(foreach, filter, map), lambda,optional key word and other fundamentals.
2. Logging (SLF4J, Logback)
3. Regular expressions
4. What is Spring Framework.
5. What is Spring Boot Framework
6. What is Spring Security Framework. (JWT Based Security)
7. What is inversion of control , dependency injection and  Spring IOC Container
8. What is JPA, Hibernate and Spring Data JPA
9. What is entity class and how to create it
10. What are OneToOne, OneToMany, ManyToOne, ManyToMany relations on hibernate.
11. How to use @JoinTable, @JoinColum annotations. 
12. JPQL (Java Persistence Query Language), Basic SQL knowledge
13. What is REST API
14. How , why to use @Bean, @Autowired, @RestController, @Service, @Repository 
annotations.
15. Jackson annotations (@JsonIgnore, @JsonFormat etc.)
16. Controller-Service-Repository layered structure in spring boot app
17. What is @Transactional annotation in org.springframework.transaction.annotation
18. What is the Data Transfer Object and how to use it.
19. Usage of @ResponseEntity, @ResponseBody, @Request Body, @RequestMapping, 
@PostMapping, @GetMapping, @DeleteMapping, @PutMapping. @Valid
 annotations.
20. HTTP Response Status Codes. (200, 201, 400, 404, etc.) 
21. Why and How to use @PathVariable and @QueryParam annotations
22. Project Lombok
