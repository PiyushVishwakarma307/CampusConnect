FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy everything
COPY . .

# Build the jar inside Docker
RUN ./mvnw clean package -DskipTests

# Run the jar
CMD ["java", "-jar", "target/*.jar"]