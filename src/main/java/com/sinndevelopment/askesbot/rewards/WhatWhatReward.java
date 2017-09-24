package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.data.Viewer;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class WhatWhatReward extends Reward
{
    public WhatWhatReward()
    {
        super(100, "whatwhat");
    }

    @Override
    public boolean redeem(Viewer v, int count, GenericMessageEvent event)
    {
        try
        {
            return bot.getStreamLabs().sendWhatWhat(v.getUsername());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
