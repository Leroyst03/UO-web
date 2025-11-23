# Etapa de construcción: compila el proyecto con Maven y JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos pom.xml y descargamos dependencias primero (cache eficiente)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el código fuente y construimos el jar
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de ejecución: imagen ligera con JDK 21
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copiamos el jar generado desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Render inyecta la variable PORT, Spring Boot debe leerla
ENV PORT=8080
EXPOSE 8080

# Arranque de la aplicación
CMD ["java", "-jar", "app.jar"]
