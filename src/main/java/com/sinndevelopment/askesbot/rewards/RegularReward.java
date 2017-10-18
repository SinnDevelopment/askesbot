package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.data.Viewer;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class RegularReward extends Reward
{
    //TODO: Properly setup the regulars to have whitelist for urls - must bug libby.
    public RegularReward()
    {
        super(500, "regular");
    }

    @Override
    public boolean redeem(Viewer v, int count, GenericMessageEvent event)
    {
        bot.replyMessage(event, "!regulars add " + v.getUsername());
        return true;
    }
}
