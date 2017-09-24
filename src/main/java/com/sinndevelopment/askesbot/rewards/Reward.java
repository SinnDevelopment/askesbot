package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.bot.AskesBot;
import com.sinndevelopment.askesbot.data.Viewer;
import org.pircbotx.hooks.types.GenericMessageEvent;

public abstract class Reward
{
    protected AskesBot bot;
    protected boolean silent = false;
    private int cost;
    private String name;
    private String[] aliases = {};

    public Reward(int cost, String name)
    {
        this.cost = cost;
        this.name = name;

        this.bot = AskesBot.getInstance();
    }

    public Reward(int cost, String name, String... aliases)
    {
        this(cost, name);
        if (aliases.length != 0 && this.aliases.length != 0)
            System.arraycopy(aliases, 0, this.aliases, 0, aliases.length);
    }

    public abstract boolean redeem(Viewer v, int count, GenericMessageEvent event);


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
        for (String a : aliases)
            if (a.equals(s))
                return true;
        return false;
    }

    public boolean isSilent()
    {
        return silent;
    }
}
