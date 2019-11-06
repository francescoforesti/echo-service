FROM armv7/armhf-java8
ARG JAR_FILE
COPY ${JAR_FILE} /app.jar
COPY target/classes/application.properties /

#ENTRYPOINT ["sleep", "1000"]
ENTRYPOINT ["java", "-XX:+UseSerialGC", "-Xss512k", "-XX:MaxRAM=72m", "-jar", "/app.jar"]