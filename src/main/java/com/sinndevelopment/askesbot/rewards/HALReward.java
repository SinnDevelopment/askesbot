package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.data.Viewer;

public class HALReward extends Reward
{
    public HALReward()
    {
        super(150, "hal");
    }

    @Override
    public boolean redeem(Viewer v, int count)
    {
        try
        {
            return bot.getStreamLabs().sendHAL(v.getUsername());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
