package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.data.Viewer;
import com.sinndevelopment.askesbot.hooks.AskesbotWebHandler;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.Arrays;
import java.util.List;

public class StreamlabsReward extends Reward
{
    public StreamlabsReward(String name)
    {
        super(100, name);
    }

    @Override
    public boolean redeem(Viewer v, int count, GenericMessageEvent event)
    {
        try
        {
            switch (getName())
            {
                case "canada":
                    return bot.getStreamLabs().sendHonk(v.getUsername());
                case "daddy":
                    return bot.getStreamLabs().sendDaddy(v.getUsername());
                case "hal":
                    return bot.getStreamLabs().sendHAL(v.getUsername());
                case "meow":
                    return bot.getStreamLabs().sendMeow(v.getUsername());
                case "alert":
                    return bot.getStreamLabs().sendBoo(v.getUsername());
                case "fail":
                    return bot.getStreamLabs().sendFail(v.getUsername());
                case "me":
                    switch (v.getUsername())
                    {
                        case "askesienne":
                            List<String> args = Arrays.asList(event.getMessage().split(" "));
                            if (args.size() >= 1)
                                args = args.subList(1, args.size());
                            bot.getAskesbotWebHandler().getMe();
                            String target = args.get(1);
                            if (target.equalsIgnoreCase("lantheos"))
                            {
                                return bot.getStreamLabs().sendStreamLabs("Hi I'm *Lantheos*...",
                                        "https://media.giphy.com/media/IXs4NJprHyqzu/giphy.gif",
                                        "https://s3.ca-central-1.amazonaws.com/askesbot/audio/cock.ogg");
                            }
                            if (bot.getAskesbotWebHandler().getRedeemMeData().containsKey(target.toLowerCase()))
                            {
                                AskesbotWebHandler.RedeemMeData data = bot.getAskesbotWebHandler().getRedeemMeData().get(target.toLowerCase());
                                System.out.println(data.toString());
                                return bot.getStreamLabs().sendStreamLabs(data.getMessage(), data.getImageURL(), data.getSoundURL());
                            }
                            break;
                        case "lantheos":
                            return bot.getStreamLabs().sendStreamLabs("Hi I'm *Lantheos*...",
                                    "https://media.giphy.com/media/IXs4NJprHyqzu/giphy.gif",
                                    "https://s3.ca-central-1.amazonaws.com/askesbot/audio/cock.ogg");
                        default:
                            bot.getAskesbotWebHandler().getMe();
                            if (bot.getAskesbotWebHandler().getRedeemMeData().containsKey(v.getUsername().toLowerCase()))
                            {
                                AskesbotWebHandler.RedeemMeData data = bot.getAskesbotWebHandler().getRedeemMeData().get(v.getUsername().toLowerCase());
                                System.out.println(data.toString());
                                return bot.getStreamLabs().sendStreamLabs(data.getMessage(), data.getImageURL(), data.getSoundURL());
                            }
                            bot.replyMessage(event, v.getUsername(), "Sorry, but you're not listed. If you want a custom alert, become a subscriber on Twitch!" +
                                    " If you're already a subscriber, set your message here: https://askesbot.com");
                            return false;
                    }
                case "muppet":
                    return bot.getStreamLabs().sendMuppet(v.getUsername());
                case "pewpew":
                    return bot.getStreamLabs().sendPewPew(v.getUsername());
                case "random":
                    return bot.getStreamLabs().sendRandom(v.getUsername());
                case "shame":
                    return bot.getStreamLabs().sendShame(v.getUsername());
                case "spook":
                    return bot.getStreamLabs().sendSpook(v.getUsername());
                case "trains":
                    this.setCost(5);
                    return bot.getStreamLabs().sendTrains(v.getUsername());
                case "whatwhat":
                    return bot.getStreamLabs().sendWhatWhat(v.getUsername());
                default:
                    //TODO: Load from custom/dynamic commands
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
