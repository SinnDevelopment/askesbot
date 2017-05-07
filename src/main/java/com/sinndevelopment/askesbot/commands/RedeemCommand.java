package com.sinndevelopment.askesbot.commands;

import com.sinndevelopment.askesbot.points.Viewer;
import com.sinndevelopment.askesbot.points.rewards.AlertReward;
import com.sinndevelopment.askesbot.points.rewards.PetReward;
import com.sinndevelopment.askesbot.points.rewards.RegularReward;
import com.sinndevelopment.askesbot.points.rewards.Reward;

import java.util.List;

public class RedeemCommand extends ChatCommand
{
    private Reward[] rewards = {new AlertReward(), new PetReward(), new RegularReward()};
    private StringBuilder validRewards = new StringBuilder();
    public RedeemCommand()
    {
        super("redeem", PermissionLevel.VIEWER);
        for(Reward r : rewards)
        {
            validRewards.append(r.getName()).append(", ");
        }
    }

    @Override
    public void onCommand(String channel, String sender, String login, String hostname, List<String> args)
    {
        Viewer viewer = new Viewer(sender);

        if(args.size() < 1)
        {
            bot.sendViewerMessage(sender , "sorry, that's not a valid reward. The valid ones are: "
                    + validRewards.toString());
            return;
        }

        for (Reward r : rewards)
        {
            if(args.get(0).equals(r.getName())
                    || r.isAlias(args.get(0)))
            {
                if(viewer.charge(r.getCost()))
                {
                    if(r.redeem(viewer, 1))
                        bot.sendViewerMessage(sender , "sending reward...");
                    else
                        bot.sendViewerMessage(sender, "Something went wrong with redemption :(");
                }
                else
                    bot.sendViewerMessage(sender , "you do not have the balance required.");

            }
        }
    }
}
