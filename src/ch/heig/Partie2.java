package ch.heig;

import ch.heig.payloads.RequestPayload;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Partie2
{
    static HttpServer server;

    static int PORT = 8080;

    static String URL = "/challenge-b/test1";

    static String MY_EMAIL = "yosra.harbaoui@heig-vd.ch";

    public static void main(String[] args) throws IOException
    {
        System.out.println("================");
        System.out.println("Java HTTP Server");
        System.out.println("================");

        server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext(URL, new MyHandler());
        server.setExecutor(null); // creates a default executor

        System.out.println("starting server ...");
        server.start();
    }

    static class MyHandler implements HttpHandler
    {
        public void handle(HttpExchange httpExchange) throws IOException
        {
            Headers requestHeaders = httpExchange.getRequestHeaders();

            String accept = requestHeaders.getFirst("Accept");

            System.out.println("Accept: " + accept);

            String str_response = "";

            int code = 200; // https://fr.wikipedia.org/wiki/Liste_des_codes_HTTP

            Headers responseHeaders = httpExchange.getResponseHeaders();

            switch(accept)
            {
                case "application/json":
                {
                    str_response = JsonObjectMapper.toJson(new RequestPayload(MY_EMAIL));

                    responseHeaders.add("Content-Type", "application/json");

                    break;
                }
                case "application/xml":
                {
                    code = 406; // https://fr.wikipedia.org/wiki/Liste_des_codes_HTTP

                    break;
                }
                default:
                {
                    str_response = "My email is <b>" + MY_EMAIL + "</b>";

                    responseHeaders.add("Content-Type", "text/html");
                }
            }

            byte [] response = str_response.getBytes();

            httpExchange.sendResponseHeaders(code, (response.length == 0 ? -1 : response.length));

            OutputStream os = httpExchange.getResponseBody();
            os.write(response);
            os.close();
        }
    }
}
