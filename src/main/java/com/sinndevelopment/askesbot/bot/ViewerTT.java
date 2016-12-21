package com.sinndevelopment.askesbot.bot;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinndevelopment.askesbot.points.Viewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

public class ViewerTT extends TimerTask
{
    private AskesBot bot;
    private Map<String, Object> chatters = new HashMap<>();
    public ViewerTT(AskesBot bot)
    {
        this.bot = bot;
    }

    public static Map<String, Object> getChatters()
    {
        Map<String, Object> ret = null;
        try
        {
            InputStream is = new URL("http://tmi.twitch.tv/group/user/askesienne/chatters").openConnection().getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String result = "";
            String line;
            while((line = reader.readLine()) != null)
            {
                result += line;
            }
            Gson gson = new Gson();

            ret = gson.fromJson(result, new TypeToken<Map<String, Object>>(){}.getType());

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return ret;
    }

    public void run()
    {
        Map<String, List<String>> json = (Map<String, List<String>>) getChatters().get("chatters");
        List<String> mods = json.get("moderators");
        List<String> viewers = json.get("viewers");

        viewers.addAll(mods);

        for(String s : viewers)
        {
            Viewer v = new Viewer(0, s, false);

        }
    }
}
