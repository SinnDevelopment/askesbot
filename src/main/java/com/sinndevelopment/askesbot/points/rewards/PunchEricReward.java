package com.sinndevelopment.askesbot.points.rewards;

import com.sinndevelopment.askesbot.points.Viewer;

public class PunchEricReward extends Reward
{
    public PunchEricReward()
    {
        super(350, "lochness");
    }

    @Override
    public boolean redeem(Viewer v, int count)
    {
        bot.sendChannelMessage("@Askesienne, " + v.getUsername() + " needs about tree-fiddy to punch Eric");
        return true;
    }
}
