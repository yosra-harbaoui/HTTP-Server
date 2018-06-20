package ch.heig;

import ch.heig.payloads.Partie1FirstAnswerPayload;
import ch.heig.payloads.Partie1FirstPayload;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Partie1
{
    static String SERVICE_URL_1 = "http://novaflux.heig-vd.ch/challengeTurtle";
    static String SERVICE_URL_2 = "http://novaflux.heig-vd.ch/challengeTurtle/responses/";

    static String MY_EMAIL = "yosra.harbaoui@heig-vd.ch";
    static String MY_TOKEN = "1a2b3c4d5e";

    public static void main(String[] args) throws IOException
    {
        String answer1 = get(MY_EMAIL);

        System.out.println(answer1);

        Partie1FirstAnswerPayload partie1FirstAnswerPayload = JsonObjectMapper.parseJson(answer1, Partie1FirstAnswerPayload.class);

        String newToken = partie1FirstAnswerPayload.getToken().toUpperCase();
        newToken  = newToken.replace("9","N");

        Partie1FirstPayload partie1FirstPayload = new Partie1FirstPayload( partie1FirstAnswerPayload.getToken(), newToken, MY_EMAIL);

        String answer = post(partie1FirstPayload);
    }

    public static String get(String email)
    {
        try
        {
            String email_enc = URLEncoder.encode(email, "UTF-8");

            String new_url = SERVICE_URL_1 + "?email=" + email_enc;

            System.out.println("URL: " + new_url);

            URL url = new URL(new_url);

            HttpURLConnection httpConnection  = (HttpURLConnection) url.openConnection();
            httpConnection.setDoOutput(true);

            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.setRequestProperty("Accept", "application/json");

            Integer responseCode = httpConnection.getResponseCode();

            BufferedReader bufferedReader;

            // Creates a reader buffer
            if (responseCode > 199 && responseCode < 300)
            {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            }
            else
            {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
            }

            // To receive the response
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line).append("\n");
            }
            bufferedReader.close();

            // Prints the response
            System.out.println("Reponse: " + content.toString());

            return content.toString();
        }
        catch (Exception e)
        {
            System.out.println("Error Message");
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }

        return "";
    }

    public static String post(Object object)
    {
        try
        {
            String json = JsonObjectMapper.toJson(object);

            System.out.println("JSON: " + json);

            URL url = new URL(SERVICE_URL_2);

            HttpURLConnection httpConnection  = (HttpURLConnection) url.openConnection();
            httpConnection.setDoOutput(true);

            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.setRequestProperty("Accept", "application/json");

            // Not required
            // urlConnection.setRequestProperty("Content-Length", String.valueOf(input.getBytes().length));

            // Writes the JSON parsed as string to the connection
            DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream());
            wr.write(json.getBytes());
            Integer responseCode = httpConnection.getResponseCode();

            BufferedReader bufferedReader;

            // Creates a reader buffer
            if (responseCode > 199 && responseCode < 300)
            {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            }
            else
            {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
            }

            // To receive the response
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line).append("\n");
            }
            bufferedReader.close();

            // Prints the response
            System.out.println("Reponse: " + content.toString());

            return content.toString();
        }
        catch (Exception e)
        {
            System.out.println("Error Message");
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }

        return "";
    }
}
