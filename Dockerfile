# ========================
# Stage 1: Build JAR
# ========================
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder

WORKDIR /app

# Copy dependency files dulu (untuk caching layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Baru copy source code
COPY src ./src

# Build tanpa run tests (lebih cepat)
RUN mvn clean package -DskipTests

# ========================
# Stage 2: Runtime Image
# ========================
FROM eclipse-temurin:17-jre-alpine

# Install curl for healthcheck
RUN apk add --no-cache curl

WORKDIR /app

# Copy JAR dari stage builder
COPY --from=builder /app/target/*.jar app.jar

# Expose port (sesuaikan dengan application.yml)
EXPOSE 8761

# Jalankan aplikasi
ENTRYPOINT ["java", "-jar", "app.jar"]
