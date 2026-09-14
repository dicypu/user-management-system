# 1. Aşama: Derleme (Build Stage - Java 21)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Bağımlılıkları önbelleğe al
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Kaynak kodları kopyala ve JAR paketle
COPY src ./src
RUN mvn clean package -DskipTests

# 2. Aşama: Çalıştırma (Runtime Stage - Java 21 JRE)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Root yetkisi olmayan kullanıcı
RUN useradd -ms /bin/bash appuser
USER appuser

# Derlenen JAR dosyasını aktar
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "app.jar"]