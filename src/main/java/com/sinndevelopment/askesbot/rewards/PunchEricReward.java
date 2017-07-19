package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.data.Viewer;

public class PunchEricReward extends Reward
{
    public PunchEricReward()
    {
        super(35, "lochness");
    }

    @Override
    public boolean redeem(Viewer v, int count)
    {
        bot.sendChannelMessage("@Askesienne, " + v.getUsername() + " needs about tree-fiddy to punch Eric");
        return true;
    }
}
