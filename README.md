
# Proyecto Sisinfo 🚀

Este proyecto utiliza **Docker Compose** para facilitar su despliegue y ejecución.

---

## 🛠 Requisitos previos

1. **Docker**: Asegúrate de tener Docker instalado y en funcionamiento. Si no lo tienes, descárgalo desde [Docker](https://www.docker.com/get-started).
   
   Para verificar la instalación, ejecuta en tu terminal:
   ```bash
   docker --version
   ```

2. **Docker Compose**: Es parte de las versiones modernas de Docker. Verifica que esté disponible:
   ```bash
   docker compose version
   ```

---

## 🚀 Cómo ejecutar el proyecto

1. Abre una terminal.

2. Navega a la carpeta raíz del proyecto:
   ```bash
   cd /ruta/a/tu/carpeta/sisinfo
   ```

3. Ejecuta el siguiente comando:
   ```bash
   docker compose up
   ```

4. Una vez que los contenedores se hayan iniciado, abre tu navegador web y accede a:
   ```
   http://localhost:8080
   ```

---

## 🛑 Cómo detener el proyecto

Para detener y eliminar los contenedores en ejecución, presiona `Ctrl + C` en la terminal donde ejecutaste el comando o abre otra terminal en la misma carpeta y ejecuta:

```bash
docker compose down
```

---

## 📂 Estructura del proyecto

```plaintext
.
├── src/                 # Código fuente del proyecto
├── backup               # Archivos de respaldo de la base de datos
├── docker-compose       # Archivo de configuración Docker Compose
├── Dockerfile           # Instrucciones para construir la imagen Docker
├── pom                  # Archivo de configuración Maven (Java)
```

---

## ✨ Notas adicionales

- Asegúrate de que el puerto `8080` no esté ocupado por otro servicio en tu máquina.
- Si necesitas reconstruir los contenedores, utiliza:
  ```bash
  docker compose up --build
  ```

---

¡Disfruta usando el proyecto! 🚀
