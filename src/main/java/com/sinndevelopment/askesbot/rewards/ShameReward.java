package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.data.Viewer;

public class ShameReward extends Reward
{
    public ShameReward()
    {
        super(60, "shame");
        super.silent = true;
    }
    @Override
    public boolean redeem(Viewer v, int count)
    {
        try
        {
            return bot.getStreamLabs().sendShame(v.getUsername());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
