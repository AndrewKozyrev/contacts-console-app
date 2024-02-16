FROM openjdk:17-slim

WORKDIR /app

COPY ./app/contacts-console-app.jar contacts-console-app.jar

VOLUME ["/app"]

CMD ["java", "-jar", "contacts-console-app.jar"]