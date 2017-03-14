package com.sinndevelopment.askesbot.points.rewards;

import com.sinndevelopment.askesbot.data.StreamLabsHandler;
import com.sinndevelopment.askesbot.points.Viewer;

public class AlertReward extends Reward
{
    public AlertReward()
    {
        super(1500, "alert");
    }

    @Override
    public boolean redeem(Viewer v, int count)
    {
        try
        {
            return StreamLabsHandler.sendAlert(v.getUsername(), " says BOO!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        v.setCount(v.getCount() - 1500);
        return false;
    }
}
