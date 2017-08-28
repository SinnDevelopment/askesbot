package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.data.Viewer;

public class MeReward extends Reward
{

    public MeReward()
    {
        super(0, "me");
    }

    @Override
    public boolean redeem(Viewer v, int count)
    {
        try
        {
            switch (v.getUsername())
            {
                case "lantheos":
                    return bot.getStreamLabs().sendStreamLabs("Hi I'm *Lantheos*...",
                            "http://i.imgur.com/9uuUzV8.png",
                            "http://wat.sinnpi.com/dl/cock.ogg");
                case "edrost":
                    return bot.getStreamLabs().sendTrains("edrost");
                default:
                    bot.sendViewerMessage(v.getUsername(), "Sorry, but you're not listed. If you want a custom alert, become a subscriber on Twitch!");
                    return false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
