#!/bin/sh
echo "------ Step 01: Set project path to variable -------"
project_path="/Users/macos/Automation Testing/03-Java Hybrid Framework"
echo "------ Step 02: Go to project path folder -------"
cd "$project_path"
echo "------ Step 03: Run the testcases -------"
java -javaagent:"$project_path/libAllure/aspectjweaver-1.9.25.1.jar" -classpath "$project_path/out/production/03-Java Hybrid Framework:$project_path/libAllure/*:$project_path/libLog4J2/*:$project_path/libExtent5/*:$project_path/libReportNG/*:$project_path/libWDM/*:$project_path/libraries/*" org.testng.TestNG "$project_path/resources/allure.xml"
echo "------ Step 04: Load allure command line setting -------"
source ~/.bash_profile
echo "------ Step 05: Generate Allure HTML Report -------"
allure serve ./allure-results/

