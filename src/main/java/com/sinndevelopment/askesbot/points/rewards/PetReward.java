package com.sinndevelopment.askesbot.points.rewards;

import com.sinndevelopment.askesbot.data.StreamLabsHandler;
import com.sinndevelopment.askesbot.points.Viewer;

public class PetReward extends Reward
{
    public PetReward()
    {
        super(120, "petpets", "pets");
    }

    @Override
    public boolean redeem(Viewer v, int count)
    {
        super.redeem(v, count);
        try
        {
            return StreamLabsHandler.sendAlert(v.getUsername(), "requests a pet be petted");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }
}
