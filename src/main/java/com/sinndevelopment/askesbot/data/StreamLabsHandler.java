package com.sinndevelopment.askesbot.data;

import com.sinndevelopment.askesbot.Main;
import org.json.JSONObject;

import java.io.BufferedReader;
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
        Main.getLogger().info(jsonObject.toString());
        Main.getLogger().info(response.toString());
        Main.REFRESH_TOKEN = jsonObject.getString("refresh_token");
        return jsonObject.getString("access_token");
    }

    public static boolean sendAlert(String user, String message) throws Exception
    {
        String url = "https://sinndevelopment.com/oauth/streamlabs/alert.php";

        String urlParameters = "?access_token=" + getAccessToken() +
                "&message='*" + user + "* " + message;
        URL obj = new URL(url+urlParameters);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

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
        Main.getLogger().info(jsonObject.toString());
        Main.getLogger().info(response.toString());

        Main.getLogger().info("Attempted Contacting Streamlabs... R:"+con.getResponseCode());
        ///////
        return con.getResponseCode() == 200;
    }
}
