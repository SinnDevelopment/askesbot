package com.sinndevelopment.askesbot.points.rewards;

import com.sinndevelopment.askesbot.data.StreamLabsHandler;
import com.sinndevelopment.askesbot.points.Viewer;

public class PetReward extends Reward
{
    public PetReward()
    {
        super(100, "petpets");
    }

    @Override
    public boolean redeem(Viewer v, int count)
    {
        try
        {
            return StreamLabsHandler.sendAlert(v.getUsername(), " requests a pet be petted");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        v.setCount(v.getCount() - 100);
        return false;
    }
}
