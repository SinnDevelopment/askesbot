package com.sinndevelopment.askesbot.points.rewards;

import com.sinndevelopment.askesbot.bot.AskesBot;
import com.sinndevelopment.askesbot.points.Viewer;

public abstract class Reward
{
    private int cost;
    private String name;
    private String[] aliases = {};
    protected AskesBot bot;

    public Reward(int cost, String name)
    {
        this.cost = cost;
        this.name = name;

        this.bot = AskesBot.getInstance();
    }

    public Reward(int cost, String name, String... aliases)
    {
        this(cost, name);
        System.arraycopy(aliases, 0, this.aliases, 0, aliases.length);
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

    public String[] getAliases()
    {
        return aliases;
    }

    public boolean isAlias(String s)
    {
        for(String a : aliases)
            if (a.equals(s))
                return true;
        return false;
    }
}
