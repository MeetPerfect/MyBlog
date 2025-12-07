FROM openjdk:8-jdk-alpine

RUN mkdir -p /weblog
WORKDIR /weblog

COPY weblog-web-0.0.1-SNAPSHOT.jar app.jar
# 设置时区
ENV TZ=Asia/Shanghai

ENV JAVA_OPTS="-Xms300m -Xmx300m -Djava.security.egd=file:/dev/./urandom"

ENV ARGS=""

EXPOSE 8080

CMD java ${JAVA_OPTS} -jar app.jar ${ARGS}