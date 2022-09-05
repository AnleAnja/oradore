FROM openjdk:15.0.2

WORKDIR /oradore
COPY ./backend/build/libs/backend-all.jar backend-all.jar
CMD ["java", "-server", "-jar", "backend-all.jar"]