package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.data.Viewer;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class DaddyReward extends Reward
{
    public DaddyReward()
    {
        super(100, "daddy");
        super.silent = true;
    }

    @Override
    public boolean redeem(Viewer v, int count, GenericMessageEvent event)
    {
        try
        {
            return bot.getStreamLabs().sendDaddy(v.getUsername());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
