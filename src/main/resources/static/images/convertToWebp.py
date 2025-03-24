from PIL import Image

def convert_to_webp(image_path, output_path):
    with Image.open(image_path) as img:
        img.save(output_path, "webp")

convert_to_webp("campofurbol.jpg", "campofurbol.webp")