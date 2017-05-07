package com.sinndevelopment.askesbot.points.rewards;


import com.sinndevelopment.askesbot.data.StreamLabsHandler;
import com.sinndevelopment.askesbot.points.Viewer;

public class AlertReward extends Reward
{
    public AlertReward()
    {
        super(150, "alert", "hello", "spoop");
    }

    @Override
    public boolean redeem(Viewer v, int count)
    {
        try
        {
            return StreamLabsHandler.sendAlert(v.getUsername(), "says hello!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
