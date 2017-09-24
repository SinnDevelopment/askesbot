package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.data.Viewer;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class TrainsReward extends Reward
{
    public TrainsReward()
    {
        super(5, "trains");
    }

    @Override
    public boolean redeem(Viewer v, int count, GenericMessageEvent event)
    {
        try
        {
            return bot.getStreamLabs().sendTrains(v.getUsername());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
