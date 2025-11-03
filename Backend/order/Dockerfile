# 1. Use official JDK image
FROM openjdk:24-jdk-slim

# 2. Set working directory
WORKDIR /app

# 3. Copy jar file
COPY target/*.jar app.jar

# 4. Expose service port (change for each service if needed)
EXPOSE 8085

# 5. Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
