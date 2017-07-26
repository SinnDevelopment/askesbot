package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.data.Viewer;

public class WhatWhatReward extends Reward
{
    public WhatWhatReward()
    {
        super(100, "whatwhat");
    }
    @Override
    public boolean redeem(Viewer v, int count)
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
