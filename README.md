# Test Often and Prosper

This is a small starter project that acts as a companion to a [talk by the same name](https://github.com/snekse/test-often-and-prosper-slides). 
Since the focus of that talk is "Testing Java Apps with Spock+Groovy", 
the `main` source folder contains Java code. 
The `test` source folder contains both Groovy and Java code 
to illustrate both Java JUnit tests and Groovy Spock tests 
co-existing peacefully in the same project.

## Running the Tests
This is a basic Gradle + Spring Boot project, 
so it follows standard Maven-like conventions.
From the command line at the project root, just run `./gradlew test`

If you would like to view the test report, it's located in `./build/reports/tests`

You can always run the tests in your IDE just like normal Maven + JUnit tests.

## Running the App
If you would like to run the application, open a command line and type `./gradlew bootRun`