FROM adoptopenjdk/openjdk17:openlogic-openjdk-17.0.13+11-windows-x64
WORKDIR /opt
COPY target/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar