import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;

public class GiftServer {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/gift", (exchange) -> {

            String query = exchange.getRequestURI().getQuery();

            String response = "No suggestion available";

            if(query != null){

                if(query.contains("Birthday") && query.contains("Below%20500"))
                    response="Chocolate Box";

                else if(query.contains("Birthday") && query.contains("500-1000"))
                    response="Teddy Bear, Perfume";

                else if(query.contains("Anniversary"))
                    response="Photo Frame, Flower Bouquet";

                else if(query.contains("Festival"))
                    response="Sweet Box, Gift Hamper";

            }

            // allow browser request
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

            exchange.sendResponseHeaders(200, response.length());

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();

        });

        server.start();

        System.out.println("Server running at http://localhost:8000");

    }
}