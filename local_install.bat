@echo off

rem Check if JAR file exists in app/ directory
if exist app\contacts-console-app.jar (
    echo JAR file found. Running application...
    cd app
    java -jar contacts-console-app.jar
) else (
    echo JAR file not found. Building project...
    call mvnw.cmd clean package
    echo Building complete. Running application...
    cd app
    java -jar contacts-console-app.jar
)

echo Finished!