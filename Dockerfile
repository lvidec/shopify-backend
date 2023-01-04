FROM maven:3.8.6 AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app

RUN mvn clean package -DskipTests


FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=WebShop-0.0.1-SNAPSHOT.jar
ENV APP_FILE=${JAR_FILE}

WORKDIR /opt/app
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/

CMD ["sh", "-c", "java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 -jar $APP_FILE"]
