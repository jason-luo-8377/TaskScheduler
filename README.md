
# Task Scheduler

Task Scheduler is an application built on spring boot that exposes an endpoint accepting a post body for scheduling a task. 
It also has a task handler running in the background, scaning the tasks in the database with delivery time approaching, 
and processing them. While processing the tasks, if the application goes down, 
the un-processed tasks will be picked up next time when the application launches.

## Download
git clone https://github.com/jason-luo-8377/TaskScheduler.git

## How to run
### Command line
- Unix-based OS: ./mvnw spring-boot:run
- Windows: mvnw.cmd spring-boot:run

## IDE(IntelliJ/Eclipse)
- Navigate to src/main/java/com/iqvia/challenge/challenge/ChallengeApplication.java (sorry for double "challenge" in the package)
- Right click the mouse then choose "Run ChallengeApplication.java::main"

## Configuration (application.properties)
There are two config items for the background task handler.
- task.scan.interval

  It is used to adjust the interval that the handler scans the database.
- task.scan.delivery_time.offset (is it a good naming?)

  It is used to adjust how many millseconds in the future based on current time 
  the handler scans the tasks according the delivery time. For example, if it is 5000, 
  then all the tasks before current date + 5 seconds will be fetched.
  
**Please note that using the two config items in conjunction can adjust the number of tasks fetched from the database.**

## Use the endpoint to schedule a task
- URL: http://localhost:8080/challenge/scheduleTask
- Method: post
- Content-Type: application/json
- Post body

  ```JSON
  {
    "content": "hi, there",
    "deliveryTime": "2023-01-08 16:09:50"
  }
  ```
- Time zone for delivery time: AST(america/Halifax)

## Database
h2 database is being used in the application to persist the tasks.
After the application is launched, you can use the link in the browser to access the database.
- URL: http://localhost:8080/h2-console
- User name & password: empty

## Unit test (not all code are unit test covered due to time limit)
- Unix-based OS: ./mvnw test
- Windows: mvnw.cmd test