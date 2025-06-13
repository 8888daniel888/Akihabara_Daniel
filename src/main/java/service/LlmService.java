package service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

//Clase que encapsula la comunicación con el modelo LLM a través de OpenRouter
public class LlmService {
 private static final String ENDPOINT = "https://openrouter.ai/api/v1/chat/completions"; // URL del endpoint de OpenRouter
 private static final String MODEL = "meta-llama/llama-4-maverick:free"; // Modelo específico a usar

 private final Gson gson = new Gson();  // Gson para manejo de JSON
 private final HttpClient client = HttpClient.newHttpClient();  // Cliente HTTP

 private String apiKey;  // Clave API cargada desde config.properties

 // Constructor que carga la clave API desde el archivo de configuración
 public LlmService() throws IOException {
     Properties properties = new Properties();
     try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
         if (input == null) {
             throw new IOException("No se encontró el archivo config.properties en el classpath.");
         }
         properties.load(input);
         apiKey = properties.getProperty("OPENROUTER_API_KEY");

         if (apiKey == null || apiKey.isEmpty()) {
             throw new IllegalStateException("La API KEY no está definida en config.properties.");
         }
     }
 }

 // Método principal para consultar la IA con un prompt dado
 public String consultarIA(String prompt) throws Exception {
     // Armar cuerpo de la solicitud con formato OpenAI Chat API
     JsonObject requestBody = new JsonObject();
     requestBody.addProperty("model", MODEL);

     JsonArray messages = new JsonArray();
     JsonObject userMessage = new JsonObject();
     userMessage.addProperty("role", "user");
     userMessage.addProperty("content", prompt);
     messages.add(userMessage);

     requestBody.add("messages", messages);

     // Crear la solicitud HTTP
     HttpRequest request = HttpRequest.newBuilder()
             .uri(URI.create(ENDPOINT))
             .header("Authorization", "Bearer " + apiKey)
             .header("Content-Type", "application/json")
             .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(requestBody)))
             .build();

     // Enviar solicitud y obtener respuesta
     HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

     // Verificar el estado de la respuesta
     if (response.statusCode() != 200) {
         throw new RuntimeException("Error en respuesta del servidor IA: " + response.body());
     }

     // Procesar la respuesta JSON y extraer el contenido generado
     JsonObject json = gson.fromJson(response.body(), JsonObject.class);
     JsonArray choices = json.getAsJsonArray("choices");
     JsonObject message = choices.get(0).getAsJsonObject().getAsJsonObject("message");
     return message.get("content").getAsString().trim();
 }
}