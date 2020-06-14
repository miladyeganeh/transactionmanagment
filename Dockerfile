FROM openjdk:11 AS TEMP_BUILD_IMAGE
ENV ARTIFACT_NAME=account-manager.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle gradlew $APP_HOME
COPY gradle $APP_HOME/gradle
RUN ./gradlew dockerBuild -PjarName=$ARTIFACT_NAME -Pdocker || return 0
COPY . .
RUN ./gradlew dockerBuild -PjarName=$ARTIFACT_NAME -Pdocker

FROM openjdk:11
ENV ARTIFACT_NAME=account-manager.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .
EXPOSE 8080

ENV WAIT_VERSION 2.7.2
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/$WAIT_VERSION/wait /wait
RUN chmod +x /wait

CMD ["sh", "-c", "java -jar -Dspring.profiles.active=docker ${ARTIFACT_NAME}"]
