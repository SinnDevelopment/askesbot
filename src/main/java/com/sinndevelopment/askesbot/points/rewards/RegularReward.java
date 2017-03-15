package com.sinndevelopment.askesbot.points.rewards;

import com.sinndevelopment.askesbot.points.Viewer;

public class RegularReward extends Reward
{
    public RegularReward()
    {
        super(360, "regular");
    }

    @Override
    public boolean redeem(Viewer v, int count)
    {
        if(!v.charge(this.getCost()))
        {
            return false;
        }

        bot.sendChannelMessage("!regulars add " + v.getUsername());

        return true;
    }
}
