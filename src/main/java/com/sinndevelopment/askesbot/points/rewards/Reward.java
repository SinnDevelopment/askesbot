package com.sinndevelopment.askesbot.points.rewards;

import com.sinndevelopment.askesbot.bot.AskesBot;
import com.sinndevelopment.askesbot.points.Viewer;

public abstract class Reward
{
    private int cost;
    private String name;
    protected AskesBot bot;

    public Reward(int cost, String name)
    {
        this.cost = cost;
        this.name = name;

        this.bot = AskesBot.getInstance();
    }

    public boolean redeem(Viewer v, int count)
    {
        if(!v.charge(this.getCost()))
        {
            return false;
        }
        return false;
    }

    public int getCost()
    {
        return cost;
    }

    public String getName()
    {
        return name;
    }

}
