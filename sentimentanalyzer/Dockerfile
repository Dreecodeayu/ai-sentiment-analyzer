FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy everything
COPY . .

# Make Maven wrapper executable
RUN chmod +x mvnw

# Build the project
RUN ./mvnw clean package -DskipTests

# IMPORTANT: Use the correct JAR name from pom.xml
CMD ["java", "-jar", "target/sentiment-analyzer-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080