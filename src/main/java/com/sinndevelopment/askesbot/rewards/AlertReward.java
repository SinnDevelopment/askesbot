package com.sinndevelopment.askesbot.rewards;


import com.sinndevelopment.askesbot.data.Viewer;

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
            return bot.getStreamLabs().sendBoo(v.getUsername());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
