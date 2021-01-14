FROM openjdk:11

WORKDIR /app

COPY ./ /app

RUN chmod 755 /app/gradlew && /app/gradlew bootJar

EXPOSE 8080

CMD ["java", "-jar", "/app/build/libs/oportunidade-1.0.jar"]