package com.sinndevelopment.askesbot.data;

import com.sinndevelopment.askesbot.Main;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StreamLabsHandler
{
    public StreamLabsHandler()
    {

    }


    private static String getAccessToken() throws Exception
    {
        String url = "https://sinndevelopment.com/oauth/streamlabs/token.php?refresh_token=" + Main.REFRESH_TOKEN;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null)
        {
            response.append(inputLine);
        }
        in.close();

        JSONObject jsonObject = new JSONObject(response.toString());

        return jsonObject.getString("access_token");
    }

    public static boolean sendAlert(String user, String message) throws Exception
    {

        String url = "https://sinndevelopment.com/oauth/streamlabs/alert.php";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        String urlParameters = "access_token=" + getAccessToken() +
                "&type=donation" +
                "&message='*" + user + "*" + message;

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        System.out.println("Attempted Contacting Streamlabs... R:"+responseCode);
        return responseCode == 200;
    }
}
