package com.sinndevelopment.askesbot.points.rewards;

import com.sinndevelopment.askesbot.data.StreamLabsHandler;
import com.sinndevelopment.askesbot.points.Viewer;

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
            return StreamLabsHandler.sendHAL(v.getUsername());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
