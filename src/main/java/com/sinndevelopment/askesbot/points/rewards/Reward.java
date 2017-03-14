package com.sinndevelopment.askesbot.points.rewards;

import com.sinndevelopment.askesbot.points.Viewer;

public abstract class Reward
{
    private int cost;
    private String name;

    public Reward(int cost, String name)
    {

        this.cost = cost;
        this.name = name;
    }

    public abstract boolean redeem(Viewer v, int count);

    public int getCost()
    {
        return cost;
    }

    public String getName()
    {
        return name;
    }

}
