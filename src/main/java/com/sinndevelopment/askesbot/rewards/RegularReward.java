package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.data.Viewer;

public class RegularReward extends Reward
{
    //TODO: Properly setup the regulars to have whitelist for urls - must bug libby.
    public RegularReward()
    {
        super(500, "regular");
    }

    @Override
    public boolean redeem(Viewer v, int count)
    {
        bot.sendChannelMessage("!regulars add " + v.getUsername());

        return true;
    }
}
