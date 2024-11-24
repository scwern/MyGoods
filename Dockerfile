FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/MyGoods-1.0.jar goods.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "goods.jar"]