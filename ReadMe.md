#Project Title 

Customer Profile Microservice provides REST APIs for Create, Update, Delete and Read Customer's Profile information.

#Getting Started

#Versioning
API MAJOR VERSION : v1

#Prerequisites
1. Java JRE version 1.8
2. Apache Maven version 3.3.9 or above.

#Installing

1. Download and Install JRE from http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html. 
	1.1 Add JAVA_HOME variable in the Windows environment and point it to your Java/JRE folder
	1.2 Update PATH variable, append JRE bin folder.
	1.2 For verification run 'java -version' command in the command prompt
2. Download Apache Maven from https://maven.apache.org/download.cgi. 
	2.1 Add both M2_HOME and MAVEN_HOME variables in the Windows environment, and point it to your Maven folder.
	2.2 Update PATH variable, append Maven bin folder – %M2_HOME%\bin, so that you can run the Maven’s command everywhere.
	2.3 For verification run 'mvn –version' in the command prompt.
3. Download and save this Microservice application in the File system.

#Running
 
1. Open Command prompt and change the current working directory to Microservice's Root Directory
2. Run "mvn clean install". This will build the project dependencies, run unit test cases and create the JAR file 
   in Microservice's target directory.
3. Once build is successful, change the current working directory to Microservice's target directory
4. Run command java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n -jar customer-profile-microservice-0.0.1-SNAPSHOT.jar
5. This will run the Microservice locally and REST APIs are now ready to Accept requests.

#Running the tests

1. Use any API testing tool such as PostMan or SoapUI for testing the Application.
2. Use URL - localhost:8080/v1/customers/profile/1234 for POST, GET, PUT and DELETE operations
3. Request Parameters and Response details are available in the Contract Definition file.
