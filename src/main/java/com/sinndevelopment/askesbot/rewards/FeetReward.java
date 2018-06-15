package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.data.Viewer;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class FeetReward extends Reward
{
    public FeetReward()
    {
        super(150, "feet");
        this.silent = true;
    }

    @Override
    public boolean redeem(Viewer v, int count, GenericMessageEvent event)
    {
        return false;
    }
}
