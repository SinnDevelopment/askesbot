package com.sinndevelopment.askesbot.hooks;

import com.sinndevelopment.askesbot.Main;
import com.sinndevelopment.askesbot.data.TokenLogger;
import com.sinndevelopment.askesbot.data.Viewer;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GameWispHandler
{
    public String REFRESH_TOKEN = "";
    private TokenLogger logger;

    public GameWispHandler(TokenLogger logger)
    {
        this.logger = logger;
        REFRESH_TOKEN = this.logger.getLatest();
    }

    private String getAccessToken() throws Exception
    {
        String url = "https://twitch.sinndevelopment.com/oauth/gamewisp/token.php?refresh_token=" + REFRESH_TOKEN;

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
        REFRESH_TOKEN = jsonObject.getString("refresh_token");
        logger.updateCurrent(REFRESH_TOKEN);
        return jsonObject.getString("access_token");
    }

    public boolean isSubscriber(Viewer viewer) throws Exception
    {
        String url = "https://twitch.sinndevelopment.com/oauth/gamewisp/subscribers.php";

        String urlParameters = "?access_token=" + getAccessToken() +
                "&type=twitch" +
                "&user_name=" + viewer.getUsername() +
                "&include=user";
        URL obj = new URL(url + urlParameters);
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

        Main.getLogger().info("Attempted Contacting Gamewisp... R:" + con.getResponseCode());

        boolean active = jsonObject.getJSONArray("data").getJSONObject(0).getString("status").equals("active");
        viewer.setSubscriber(active);
        return active;
    }


}
