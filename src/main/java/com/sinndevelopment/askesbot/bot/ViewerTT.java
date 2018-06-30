package com.sinndevelopment.askesbot.bot;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinndevelopment.askesbot.Main;
import com.sinndevelopment.askesbot.data.Viewer;
import com.sinndevelopment.askesbot.data.YAMLViewerHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

public class ViewerTT extends TimerTask
{
    private String[] blockedPoints = {"nightbot", "askesbot", "null"};

    public static Map<String, Object> getChatters()
    {
        Map<String, Object> ret = null;
        try
        {
            InputStream is = new URL("http://tmi.twitch.tv/group/user/askesienne/chatters").openConnection().getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
            {
                result.append(line);
            }
            Gson gson = new Gson();

            ret = gson.fromJson(result.toString(), new TypeToken<Map<String, Object>>()
            {
            }.getType());

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
        System.out.println(viewers.toString());
        AskesBot.getInstance().setViewers(viewers);
        boolean blocked;
        for (String s : viewers)
        {
            blocked = false;
            s = s.toLowerCase();
            for (String b : blockedPoints)
            {
                if (s.equalsIgnoreCase(b))
                    blocked = true;
            }
            if (!blocked)
            {
                try
                {
                    Viewer v = YAMLViewerHandler.getViewer(s);


                    System.out.println("Added a point to " + v.getUsername());
                    v.addPoint();
                    YAMLViewerHandler.saveViewer(v);
                    Main.getLogger().info("Added a point to " + v.getUsername());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }
    }
}
