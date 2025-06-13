# Akihabara
Esta aplicación permite gestionar productos otaku con funciones CRUD
y una herramienta de inteligencia artificial para ayuda para generar escripciones de producto o sugerencia de categoría.

## Autor
- **Autor:** Daniel Presedo
- **Versión:** 1.0

## Tecnologías
- Java (aplicación de consola)
- MySQL (base de datos)
- JDBC
- API de OpenRouter (servicio IA)

## Archivos incluidos:
- Akihabara.jar: es la aplicación.
- Ejecutable.bat: archivo para ejecutar Akihabara.jar.
- crear_tabla.sql: es el script para crear la base de datos.

## Configuración MySQL
- Descarga MySQL Workbench.
- Elige el usuario como 'root' y la contraseña 'campusfp'
- Cuando acabes dirigete arriba a la izquierda y selecciona 'Open Script SQL'.
- Selecciona el archivo crear_tabla.sql.
- Dale al rayo que se encuentra en el medio para ejecutar todo.

## Configuración de la API
- Dirigete a https://openrouter.ai/
- Una vez dentro regístrate.
- Después selecciona la pestaña API Keys.
- Le damos a Create API Key y generamos la API Key.
- Crea un archivo llamado 'apikey.txt' en la misma carpeta donde esté el '.jar'.
- Dentro del archivo pegamos la API.

## Ejecución de la aplicación
- Haz doble clic en 'Ejecutable.bat'
- Si usar la terminal pon: java -jar Akihabara.jar

## Funcionalidades
- Alta, baja, modificación y listado de productos otaku.
- Integración con IA.

## Estructura
- MainApp.java → clase principal con el menú e interacción
- ProductoDAO.java → operaciones CRUD
- ProductoOtaku.java → clase modelo del producto
- DatabaseConnection.java → conexión con MySQL
- InterfazConsola.java → entrada/salida por consola
- LlmService.java → integración con la IA de OpenRouter

## Enlace GitHub
https://github.com/8888daniel888/Akihabara_Daniel.git
