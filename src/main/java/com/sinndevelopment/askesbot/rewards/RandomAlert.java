package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.data.Viewer;

public class RandomAlert extends Reward
{
    public RandomAlert()
    {
        super(200, "random");
        super.silent = true;
    }
    @Override
    public boolean redeem(Viewer v, int count)
    {
        try
        {
            return bot.getStreamLabs().sendRandom(v.getUsername());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
