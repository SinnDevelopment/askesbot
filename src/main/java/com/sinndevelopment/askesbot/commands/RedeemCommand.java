package com.sinndevelopment.askesbot.commands;

import com.sinndevelopment.askesbot.data.Viewer;
import com.sinndevelopment.askesbot.rewards.*;
import org.pircbotx.hooks.events.PrivateMessageEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.List;

public class RedeemCommand extends ChatCommand
{
    private Reward[] rewards = {
            new AlertReward(), new PetReward(), new RegularReward(), new PunchEricReward(), new HALReward(),
            new TrainsReward(), new SpookReward(), new PewPewReward(), new RandomAlert(), new WhatWhatReward(),
            new MeowReward(), new MeReward(), new ShameReward()
    };
    private StringBuilder validRewards = new StringBuilder();

    public RedeemCommand()
    {
        super("redeem", PermissionLevel.VIEWER);
        for (Reward r : rewards)
        {
            validRewards.append(r.getName()).append(" (").append(r.getCost()).append(")").append(", ");
        }
    }

    @Override
    protected void onPMCommand(PrivateMessageEvent event, String sender, List<String> args)
    {
        Viewer viewer = new Viewer(sender);

        if (args.size() < 1)
        {
            bot.replyMessage(event, sender, "sorry, that's not a valid reward. The valid ones are: "
                    + validRewards.toString());
            return;
        }

        for (Reward r : rewards)
        {
            if (args.get(0).equals(r.getName())
                    || r.isAlias(args.get(0)))
            {
                if (viewer.charge(r.getCost()))
                {
                    if (r.redeem(viewer, 1, event))
                    {
                        bot.replyMessage(event, sender, "sending reward...");
                        bot.replyMessage(event, sender, "You now have " + viewer.getAmount() + " points");
                        return;
                    }
                    else
                    {
                        bot.replyMessage(event, sender, "Something went wrong with redemption :(");
                        return;
                    }
                }
                else
                {
                    bot.replyMessage(event, sender, "you do not have the balance required.");
                    return;
                }
            }
        }
        bot.replyMessage(event, sender, "sorry, that's not a valid reward. The valid ones are: "
                + validRewards.toString());
    }

    @Override
    protected void onCommand(GenericMessageEvent event, String sender, List<String> args)
    {
        Viewer viewer = new Viewer(sender);

        if (args.size() < 1)
        {
            bot.replyMessage(event, sender, "sorry, that's not a valid reward. The valid ones are: "
                    + validRewards.toString());
            return;
        }

        for (Reward r : rewards)
        {
            if (args.get(0).equalsIgnoreCase(r.getName())
                    || r.isAlias(args.get(0)))
            {
                if (viewer.charge(r.getCost()))
                {
                    if (r.redeem(viewer, 1, event))
                    {
                        if (r.isSilent()) return;
                        bot.replyMessage(event, sender, "sending reward...");
                        bot.replyMessage(event, sender, "You now have " + viewer.getAmount() + " points");
                        return;
                    }
                    else
                    {
                        bot.replyMessage(event, sender, "Something went wrong with redemption :(");
                        return;
                    }
                }
                else
                {
                    bot.replyMessage(event, sender, "you do not have the balance required.");
                    return;
                }
            }
        }
        if (sender.equalsIgnoreCase("lantheos"))
            bot.replyMessage(event, sender, " - no scary messages that don't exist.");
        else bot.replyMessage(event, sender, "sorry, that's not a valid reward. The valid ones are: "
                + validRewards.toString());
    }
}
