package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.data.Viewer;

public class RegularReward extends Reward
{
    public RegularReward()
    {
        super(360, "regular");
    }

    @Override
    public boolean redeem(Viewer v, int count)
    {
        bot.sendChannelMessage("!regulars add " + v.getUsername());

        return true;
    }
}
