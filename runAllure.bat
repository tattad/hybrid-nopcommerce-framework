set ProjectPath=%~dp0
cd %ProjectPath%
echo %ProjectPath%
set p=%PATH%
java -javaagent:"%ProjectPath%\libAllure\aspectjweaver-1.9.25.1.jar" -classpath "%ProjectPath%\out\production\03-Java Hybrid Framework;%ProjectPath%\libAllure\*;%ProjectPath%\libLog4J2\*;%ProjectPath%\ibExtent5\*;%ProjectPath%\libReportNG\*;%ProjectPath%\libraries\*;%ProjectPath%\resources\allure.xml"
allure serve .\allure-results\
pause