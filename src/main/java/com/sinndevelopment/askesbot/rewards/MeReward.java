package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.data.Viewer;
import com.sinndevelopment.askesbot.hooks.AskesbotWebHandler;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class MeReward extends Reward
{

    public MeReward()
    {
        super(0, "me");
    }

    @Override
    public boolean redeem(Viewer v, int count, GenericMessageEvent event)
    {
        try
        {
            switch (v.getUsername())
            {
                case "lantheos":
                    return bot.getStreamLabs().sendStreamLabs("Hi I'm *Lantheos*...",
                            "https://media.giphy.com/media/IXs4NJprHyqzu/giphy.gif",
                            "http://wat.sinnpi.com/dl/cock.ogg");
                default:

                    if (bot.getAskesbotWebHandler().getRedeemMeData().containsKey(v.getUsername().toLowerCase()))
                    {
                        AskesbotWebHandler.RedeemMeData data = bot.getAskesbotWebHandler().getRedeemMeData().get(v.getUsername().toLowerCase());
                        return bot.getStreamLabs().sendStreamLabs(data.getMessage(), data.getImageURL(), data.getSoundURL());
                    }
                    bot.replyMessage(event, v.getUsername(), "Sorry, but you're not listed. If you want a custom alert, become a subscriber on Twitch!" +
                            " If you're already a subscriber, set your message here: https://askesbot.com");
                    return false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
