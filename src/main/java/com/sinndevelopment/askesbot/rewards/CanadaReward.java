package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.data.Viewer;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class CanadaReward extends Reward
{

    public CanadaReward()
    {
        super(100, "canada");
    }

    @Override
    public boolean redeem(Viewer v, int count, GenericMessageEvent event)
    {
        try
        {
            return bot.getStreamLabs().sendHonk(v.getUsername());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
