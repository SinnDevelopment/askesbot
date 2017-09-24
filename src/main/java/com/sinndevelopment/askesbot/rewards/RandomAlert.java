package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.data.Viewer;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class RandomAlert extends Reward
{
    public RandomAlert()
    {
        super(200, "random");
        super.silent = true;
    }

    @Override
    public boolean redeem(Viewer v, int count, GenericMessageEvent event)
    {
        try
        {
            return bot.getStreamLabs().sendRandom(v.getUsername());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
