[![Derivative Auto Tests](https://github.com/sarojsarab/derivatives/actions/workflows/main.yml/badge.svg)](https://github.com/sarojsarab/derivatives/actions/workflows/main.yml)

# DERIVATIVES AUTOMATION SUITE

Software requirements - apache-maven-3.8.1 allure-2.14.0 Java 1.8
============================================================================================================================================

To Run this (use below on command line)

- mvn clean install [<-Dgroups={GROUPNAME}] -Denv={ENVIRONMENT}

"env" takes one of the following values as input - {UAT, QA, LOCAL}

"groups" is optional - Group names are a set of keywords which are used into the Classes which contains the tests to
label and group the tests.

1. When it is not supplied from command line, all the tests from the test classes will be executed.
2. When it is supplied from the command line, it will execute all the tests which are labelled with given name. ex:
   SMOKE, REGRESSION, DERIVATIVES, PENTAGON, DE, WALLET etc

To view the TEST Run Report -

From the terminal go to the project root directory and hit below command - allure serve allure-results
