# Usa una imagen base de OpenJDK
FROM maven:eclipse-temurin

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Copia el archivo JAR generado por Maven al contenedor
#COPY target/LaLigaInfo-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que corre la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "target/LaLigaInfo-0.0.1-SNAPSHOT.jar"]
