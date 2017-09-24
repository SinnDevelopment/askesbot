package com.sinndevelopment.askesbot.hooks;

import com.sinndevelopment.askesbot.data.TokenLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class AskesbotWebHandler
{
    private TokenLogger logger;

    private HashMap<String, RedeemMeData> redeemMeData = new HashMap<>();

    public AskesbotWebHandler(TokenLogger logger)
    {
        this.logger = logger;
    }

    public boolean getMe() throws IOException
    {
        redeemMeData.clear();
        String url = "https://askesbot.com/redeem-me.csv";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder data = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
        {
            data.append(inputLine);
            RedeemMeData userData = new RedeemMeData(inputLine);
            redeemMeData.put(userData.getUsername(), userData);
        }
        in.close();
        System.out.println(data.toString());
        System.out.println("Attempted getting _redeem me CSV... R:" + con.getResponseCode());

        return con.getResponseCode() == 200;
    }

    public HashMap<String, RedeemMeData> getRedeemMeData()
    {
        return redeemMeData;
    }

    public class RedeemMeData
    {
        private String username;
        private String soundURL;
        private String imageURL;
        private String message;

        public RedeemMeData(String rawString)
        {
            String[] data = rawString.split("~---~");
            this.username = data[0];
            this.soundURL = data[1];
            this.imageURL = data[2];
            this.message = data[3];
        }

        public String getMessage()
        {
            return message;
        }

        public String getImageURL()
        {
            return imageURL;
        }

        public String getSoundURL()
        {
            return soundURL;
        }

        public String getUsername()
        {
            return username;
        }
    }
}
