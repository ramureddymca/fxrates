Build project:
--------------
mvn clean install

Run Application:
----------------
mvn tomcat7:run

URI: http://localhost:8080/user/profile/{userid}
ex: http://localhost:8080/user/profile/101

URI:http://localhost:8080/rates/latest/{userid}

ex:
http://localhost:8080/rates/latest/101


Unit Test:
---------
1) Test cases created, to generate the test coverage report in IntelliJ IDEA right click on project ->Run "all tests" with coverage


Technologies used:
------------------
Java 8
Spring MVC 
Restful webservices
Junit
Mockito
Maven 

Assumption:
----------
1)Get: rates/latest: service need tier information, so assuming that we need to pass userId and get tier to calculate spread. If user not found then default tier "A".




