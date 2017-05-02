package com.sinndevelopment.askesbot.commands;

import com.sinndevelopment.askesbot.points.Viewer;
import com.sinndevelopment.askesbot.points.rewards.AlertReward;
import com.sinndevelopment.askesbot.points.rewards.PetReward;
import com.sinndevelopment.askesbot.points.rewards.RegularReward;
import com.sinndevelopment.askesbot.points.rewards.Reward;

import java.util.Arrays;
import java.util.List;

public class RedeemCommand extends ChatCommand
{
    public RedeemCommand()
    {
        super("redeem", PermissionLevel.VIEWER);
    }

    private Reward[] rewards = {new AlertReward(), new PetReward(), new RegularReward()};

    @Override
    public void onCommand(String channel, String sender, String login, String hostname, List<String> args)
    {
        Viewer viewer = new Viewer(sender);

        if(args.size() < 1)
        {
            bot.sendViewerMessage(sender , "sorry, that's not a valid reward. The valid ones are: "
                    + Arrays.toString(rewards));
            return;
        }

        for (Reward r : rewards)
        {
            if(args.get(0).equals(r.getName())
                    || r.isAlias(args.get(0)))
            {
                if(r.redeem(viewer, 1))
                    bot.sendViewerMessage(sender , "sending reward...");
                else
                    bot.sendViewerMessage(sender , "you do not have the balance required.");
            }
        }
    }
}
