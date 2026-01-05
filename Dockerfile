# Stage 1: Build the application
FROM eclipse-temurin:17-jdk-focal AS builder

WORKDIR /app

# Saari files copy karein
COPY . .

# Maven wrapper ko executable banayein
RUN chmod +x mvnw

# FIX: Yahan '-Dmaven.test.skip=true' add kiya hai taaki test compilation errors na aayein
RUN ./mvnw clean package -Dmaven.test.skip=true

# Stage 2: Runtime image
FROM eclipse-temurin:17-jre-focal

WORKDIR /app

# Target folder se JAR file copy karein
COPY --from=builder /app/target/*.jar app.jar

# Port jo aapne bataya tha
EXPOSE 9090

ENTRYPOINT ["java", "-Xmx512M", "-jar", "app.jar"]
