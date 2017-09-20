package com.sinndevelopment.askesbot.hooks;

import com.sinndevelopment.askesbot.Main;
import com.sinndevelopment.askesbot.data.TokenLogger;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;

public class StreamLabsHandler
{
    public String REFRESH_TOKEN = "";

    private int alertCount = 5;

    private TokenLogger token;

    public StreamLabsHandler(TokenLogger token)
    {
        this.token = token;
        REFRESH_TOKEN = token.getLatest();
    }

    public boolean sendRandom(String user) throws Exception
    {
        int randomNum = ThreadLocalRandom.current().nextInt(1, alertCount + 1);

        Thread.sleep(randomNum * 1000);

        switch (randomNum)
        {
            case 1:
                return sendBoo(user);
            case 2:
                return sendHAL(user);
            case 3:
                return sendTrains(user);
            case 4:
                return sendPewPew(user);
            case 5:
                return sendSpook(user);
            default:
                return sendTrains(user);
        }
    }

    private String getAccessToken() throws Exception
    {
        String url = "https://sinndevelopment.com/oauth/streamlabs/token.php?refresh_token=" + REFRESH_TOKEN;

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
        token.updateCurrent(REFRESH_TOKEN);
        return jsonObject.getString("access_token");
    }

    public boolean sendBoo(String user) throws Exception
    {
        return sendStreamLabs("*" + user + "* says boo!",
                "http://i.imgur.com/eU9dBRP.png",
                "https://s3.ca-central-1.amazonaws.com/askesbot/audio/nootnoot.ogg");
    }

    public boolean sendHAL(String user) throws Exception
    {
        return sendStreamLabs("*" + user + "* I'm sorry Libby, I'm afraid I can't do that...",
                "http://i.imgur.com/DiSyfXB.png",
                "https://s3.ca-central-1.amazonaws.com/askesbot/audio/dave.ogg");
    }

    public boolean sendTrains(String user) throws Exception
    {
        return sendStreamLabs("*" + user + "* likes trains too!",
                "http://i.imgur.com/33oHinu.png",
                "https://s3.ca-central-1.amazonaws.com/askesbot/audio/trains.ogg");
    }

    public boolean sendSpook(String user) throws Exception
    {
        return sendStreamLabs("*" + user + "* is spooked too!",
                "https://cdn.discordapp.com/attachments/194128662843490304/329463381612429314/sp00k.PNG",
                "https://s3.ca-central-1.amazonaws.com/askesbot/audio/spoop.ogg");
    }

    public boolean sendPewPew(String user) throws Exception
    {
        return sendStreamLabs("*" + user + "* (☞ﾟヮﾟ)☞",
                "http://i.imgur.com/8Y8mDzr.png",
                "https://s3.ca-central-1.amazonaws.com/askesbot/audio/pewpew.ogg");
    }

    public boolean sendWhatWhat(String user) throws Exception
    {
        return sendStreamLabs("*" + user + "* ಠ_ಠ",
                "http://i.imgur.com/RBnwQV6.png",
                "https://s3.ca-central-1.amazonaws.com/askesbot/audio/whatwhat.ogg");
    }

    public boolean sendMeow(String user) throws Exception
    {
        return sendStreamLabs("*"+user + "* says MEOW!",
                "https://media.giphy.com/media/fyMWf5BQ7eIW4/giphy.gif",
                "https://s3.ca-central-1.amazonaws.com/askesbot/audio/meow.ogg");
    }

    public boolean sendStreamLabs(String message, String image, String sound) throws Exception
    {
        String url = "https://sinndevelopment.com/oauth/streamlabs/alert.php";

        String urlParameters = "?access_token=" + getAccessToken() +
                "&message=" + message +
                "&image=" + image +
                "&sound=" + sound;
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

        Main.getLogger().info("Attempted Contacting Streamlabs... R:" + con.getResponseCode());
        ///////
        return con.getResponseCode() == 200;
    }
}
