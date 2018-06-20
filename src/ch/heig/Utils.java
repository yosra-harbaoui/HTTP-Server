package ch.heig;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Utils
{
    public static String request_get(String target_url, boolean resquest_json) throws Exception
    {
        HttpURLConnection httpURLConnection;
        URL url = new URL(target_url);
        httpURLConnection = (HttpURLConnection) url.openConnection();

        httpURLConnection.setRequestMethod("GET");

        if(resquest_json == true)
        {
            // vous devez ensuite demander une représentation JSON de cette ressource au serveur.
            httpURLConnection.setRequestProperty("Accept", "application/json");
        }

        StringBuilder builder = new StringBuilder();
        builder.append(httpURLConnection.getResponseCode())
                .append(" ")
                .append(httpURLConnection.getResponseMessage())
                .append("\n");

        Map<String, List<String>> map = httpURLConnection.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : map.entrySet())
        {
            if (entry.getKey() == null)
            {
                continue;
            }

            builder.append(entry.getKey()).append(": ");

            List<String> headerValues = entry.getValue();
            Iterator<String> it = headerValues.iterator();
            if (it.hasNext())
            {
                builder.append(it.next());

                while (it.hasNext())
                {
                    builder.append(", ").append(it.next());
                }
            }
            builder.append("\n");
        }

        System.out.println("=======");
        System.out.println("HEADERS");
        System.out.println("=======");
        System.out.println(builder);

        BufferedReader buf = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder answer = new StringBuilder();
        String line;
        while ((line = buf.readLine()) != null) {
            answer.append(line).append("\n");
        }

        System.out.println("=======");
        System.out.println("CONTENT");
        System.out.println("=======");
        System.out.println(answer);

        return answer.toString();
    }

    public static String request_post(String target_url, boolean resquest_json, String json_to_send) throws Exception
    {
        //Create connection
        URL url = new URL(target_url);
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

        httpURLConnection.setRequestMethod("POST");

        if(resquest_json == true)
        {
            // vous devez ensuite demander une représentation JSON de cette ressource au serveur.
            httpURLConnection.setRequestProperty("Accept", "application/json");
        }

        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("Content-Length", "" + Integer.toString(json_to_send.getBytes().length));
        // connection.setRequestProperty("Content-Language", "en-US");

        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);

        // send request
        DataOutputStream wr = new DataOutputStream (httpURLConnection.getOutputStream ());
        wr.writeBytes (json_to_send);
        wr.flush ();
        wr.close ();

        // get response
        StringBuilder builder = new StringBuilder();
        builder.append(httpURLConnection.getResponseCode())
                .append(" ")
                .append(httpURLConnection.getResponseMessage())
                .append("\n");

        Map<String, List<String>> map = httpURLConnection.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : map.entrySet())
        {
            if (entry.getKey() == null)
            {
                continue;
            }

            builder.append(entry.getKey()).append(": ");

            List<String> headerValues = entry.getValue();
            Iterator<String> it = headerValues.iterator();
            if (it.hasNext())
            {
                builder.append(it.next());

                while (it.hasNext())
                {
                    builder.append(", ").append(it.next());
                }
            }
            builder.append("\n");
        }

        System.out.println("=======");
        System.out.println("HEADERS");
        System.out.println("=======");
        System.out.println(builder);

        BufferedReader buf = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder answer = new StringBuilder();
        String line;
        while ((line = buf.readLine()) != null) {
            answer.append(line).append("\n");
        }

        System.out.println("=======");
        System.out.println("CONTENT");
        System.out.println("=======");
        System.out.println(answer);

        return answer.toString();
    }

    /**
     * transforme toutes les lettres en majuscule
     * et en change toutes les occurrences du chiffre 9
     * par la lettre ’N’ (e.g. a83b99 > A83BNN).
     */
    public static String convert(String str)
    {
        String res = str.toUpperCase();

        res = res.replace("9", "N");

        return res;
    }


    public static String responseServer(){
        String res = "";
        return res;
    }
}

