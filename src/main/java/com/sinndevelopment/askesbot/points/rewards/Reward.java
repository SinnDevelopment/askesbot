package com.sinndevelopment.askesbot.points.rewards;

import com.sinndevelopment.askesbot.points.Viewer;

public abstract class Reward
{
    protected String name;
    protected int cost;

    public Reward(String name, int cost)
    {

    }

    public abstract boolean redeem(Viewer v, int count);
}
