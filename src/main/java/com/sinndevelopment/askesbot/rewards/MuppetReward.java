package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.data.Viewer;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class MuppetReward extends Reward
{
    public MuppetReward()
    {
        super(100,"muppet");
    }
    @Override
    public boolean redeem(Viewer v, int count, GenericMessageEvent event)
    {
        try
        {
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
