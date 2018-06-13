# Prime-List-Calculator
Spring Boot application for calculating a list of prime numbers between two numeric boundaries.

What you need to run this application:
1. Maven
2. Java (1.8) & Spring Boot (1.5.14)
3. Redis Server

Alternatively, if you know how to use Eclipse or Intellij, you can use the built-in tools to compile and run the program.

Steps to run:
1. Check out prime-service-api, prime-service-impl and prime-server.
2. Maven install in the following order:
    - prime-service-api
    - prime-service-impl
    - prime-server
       - When you maven install the prime-server, it will run unit tests.
3. You will need to spring-boot:run the prime-server once everything has been maven installed properly (assuming there were no complications).
4. Run the Redis-server (you shouldn't need to change any variables).

Alternatively, you could take the war file produced from the maven install and drop it in a tomcat server for testing.

Endpoints:

-{host}:8080/calculatePrimes - This is a POST endpoint looking for two parameters, start and end (both integers). Returns an ID that should be used with the second endpoint.

-{host}:8080/getPrimes - This is a GET endpoint looking for a single parameter, an ID. Returns a list of all the primes. If there are no prime numbers within the initial boundaries given from the first call to /calculatePrimes, this will return nothing but will have a 200 status code.

Some Notes:
- I'm almost certain this app will break if you give a number larger than the size of the Java integer (2^31). This can be something I work on later.
