set ProjectPath=%~dp0
cd %ProjectPath%
set p=%PATH%
java -javaagent:"%ProjectPath%libAllure\aspectjweaver-1.9.8.jar" -classpath "%ProjectPath%bin;%ProjectPath%libAllure\*;%ProjectPath%libLog4J\*;%ProjectPath%libReportNG\*;%ProjectPath%libSelenium\*;%ProjectPath%libWebDriverManager\*" org.testng.TestNG "%ProjectPath%bin\runTechPandaTestcases.xml"
allure serve ./allure-json/