import os
from PIL import Image

# Ruta de la carpeta con las imágenes
carpeta_jugadores = "C:/Users/3346m/IdeaProjects/LaLigaInfo2/src/main/resources/static/images"

# Recorrer todos los archivos en la carpeta
for archivo in os.listdir(carpeta_jugadores):
    # Verificar si el archivo tiene extensión .png
    if archivo.lower().endswith('.jpg'):
        ruta_png = os.path.join(carpeta_jugadores, archivo)  # Ruta completa del archivo PNG
        nombre_base = os.path.splitext(archivo)[0]          # Nombre sin extensión
        ruta_webp = os.path.join(carpeta_jugadores, f"{nombre_base}.webp")  # Nueva ruta para el archivo WebP

        try:
            # Abrir la imagen y convertirla a WebP
            with Image.open(ruta_png) as img:
                img.save(ruta_webp, "webp")
            print(f"Convertido: {archivo} -> {nombre_base}.webp")

            # Eliminar el archivo PNG original
            os.remove(ruta_png)
            print(f"Eliminado: {archivo}")
        except Exception as e:
            print(f"Error al procesar {archivo}: {e}")
