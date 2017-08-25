package com.sinndevelopment.askesbot.hooks;

import com.sinndevelopment.askesbot.data.TokenLogger;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

public class TwitchAPIHandler
{
    private String CLIENT_ID = "";
    private TokenLogger logger;
    public TwitchAPIHandler(TokenLogger logger)
    {
        this.logger = logger;
        this.CLIENT_ID = logger.getLatest();
    }

    public String getAPIResponseRaw(String path) throws Exception
    {
        String url = "https://api.twitch.tv/" + path;

        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Client-ID", CLIENT_ID);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null)
        {
            response.append(inputLine);
        }
        in.close();
        System.out.println(con.getResponseCode() + " " + con.getResponseMessage());

        return response.toString();
    }

    public JSONObject getAPIResponse(String path) throws Exception
    {
        return new JSONObject(getAPIResponseRaw(path));
    }

    public HashMap<String, String> getTeamKittyMembers() throws Exception
    {
        HashMap<String, String> tkMembers = new HashMap<>();
        JSONObject fullreply = getAPIResponse("kraken/teams/teamkitty?api_version=5");
        JSONArray members = fullreply.getJSONArray("users");
        for(Object o : members)
        {
            JSONObject member = (JSONObject) o;
            String name = member.getString("name");
            String lastPlayed = member.getString("game");

            tkMembers.put(name.toLowerCase(), lastPlayed);
            System.out.println(name.toLowerCase() + ", " + lastPlayed);
        }

        return tkMembers;
    }
}
