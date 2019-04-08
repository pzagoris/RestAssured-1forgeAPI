A BDD (Java - Cucumber) framework is developed for the 1froge Rest API (https://1forge.com/forex-data-api)

1. Java 1.8 
2. rest assured 3.3.0 
3. testng 6.11 
4. maven 
5. ...
More details about the project dependencies can be found in the maven pom.xml file. All the code can be found in the path “src/test/java“.

• Feature File  Package: Src/test/java/features

• Step Definitions Package: Src/test/java/stepdefinitions 

• Test Runner Package: Src/test/java/cucumberOptions File: TestRunner.java 

• Api Key In order to set the value for the Api Key, you need to fill the “ApiKey.properties“ 

• To run the test Run the following command in project directory 
  
  mvn test 
  
  If the test runs successfully it generates csv files in the root directory.
