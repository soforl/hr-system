FROM gradle:8-jdk17
VOLUME gradle-cache:/home/gradle/.gradle
VOLUME /tmp
USER root
ADD . /home/gradle/project
WORKDIR /home/gradle/project
RUN chown gradle:gradle -R /home/gradle
USER gradle
RUN gradle bootJar -x test
#Start from a java:8
RUN mv /home/gradle/project/build/libs/*.jar /home/gradle/project/
RUN echo $(ls /home/gradle/project/)
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-Djava.security.egd=file:/dev/./urandom","-jar","/home/gradle/project/SovcombankChallenge-0.0.1.jar"]