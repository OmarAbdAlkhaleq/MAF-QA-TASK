# MAF-QA-TASK

This project is used to implement the task assigned by MAF.
What you need to run it:

## What you need before you install the code:

    Java 1.8
    Maven
    

## Technology used:

    RestAssured
    Cucumber
    Junit

## Run test
To execute the tests you have to run this command:
```
mvn clean verify -D"cucumber.filter.tags=@tag1 or @tag2"
```

### Tags available:
```
@Regression [To execute all tests]
@Functional [To execute all functional tests]
@Smoke [To execute all smoke tests]
```
## Reports

Reports are located under the followin path /target/cucmber-html-reports
