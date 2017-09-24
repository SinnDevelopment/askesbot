package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.data.Viewer;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class PunchEricReward extends Reward
{
    public PunchEricReward()
    {
        super(35, "lochness");
    }

    @Override
    public boolean redeem(Viewer v, int count, GenericMessageEvent event)
    {
        bot.replyMessage(event, "@Askesienne, " + v.getUsername() + " needs about tree-fiddy to punch Eric");
        return true;
    }
}
