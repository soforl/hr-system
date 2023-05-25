FROM gradle:7.5.1 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon -x test

FROM openjdk:18.0.2-jdk
EXPOSE 8080
RUN mkdir /backend
#COPY --from=build /home/gradle/src/build/libs/*.jar /backend/spring-boot-application.jar
#ENTRYPOINT ["java","-jar","/backend/spring-boot-application.jar"]

COPY /build/libs/*.jar /backend/
CMD ["java","-jar","/backend/SovcombankChallenge-0.0.1.jar"]