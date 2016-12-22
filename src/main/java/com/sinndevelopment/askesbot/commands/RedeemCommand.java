package com.sinndevelopment.askesbot.commands;

import com.sinndevelopment.askesbot.points.Viewer;
import com.sinndevelopment.askesbot.points.rewards.Reward;

import java.util.List;

public class RedeemCommand extends ChatCommand
{
    public RedeemCommand()
    {
        super("redeem", PermissionLevel.VIEWER);
    }

    @Override
    public void onCommand(String channel, String sender, String login, String hostname, List<String> args)
    {
        Viewer viewer = new Viewer(sender);

        Reward reward = null;
        // Reflection to load class based on name. Error otherwise and say that the reward does not exist.
    }
}
