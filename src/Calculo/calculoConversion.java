package Calculo;

import Modelo.Moneda;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class calculoConversion {
    private static final String API_KEY = "be582ac5a0c7f66b9ec1097e";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public Moneda buscarConversion(double cantidad, String moneda, String cambio) {
        URI direccion = URI.create(BASE_URL + API_KEY + "/pair/" + moneda + "/" + cambio + "/" + cantidad);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() == 200) {
                JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
                if (jsonResponse.has("conversion_result") && jsonResponse.has("conversion_rate")) {
                    double conversionRate = jsonResponse.get("conversion_rate").getAsDouble();
                    double conversionResult = jsonResponse.get("conversion_result").getAsDouble();
                    System.out.printf("Tasa de cambio: %.4f%n", conversionRate);
                    return new Moneda(conversionResult, moneda, cambio);
                } else {
                    throw new RuntimeException("La respuesta no contiene los datos esperados.");
                }
            } else {
                throw new RuntimeException("Error en la API. Código de estado: " + response.statusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("No se pudo realizar la conversión: " + e.getMessage(), e);
        }
    }
}
