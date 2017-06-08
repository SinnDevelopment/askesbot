package com.sinndevelopment.askesbot.commands;

import com.sinndevelopment.askesbot.data.YAMLViewerHandler;
import com.sinndevelopment.askesbot.data.Viewer;

import java.util.List;

public class GetPointsCommand extends ChatCommand
{
    public GetPointsCommand()
    {
        super("mypoints", PermissionLevel.VIEWER);
    }

    @Override
    protected void onCommand(String channel, String sender, String login, String hostname, List<String> args)
    {
        Viewer v = YAMLViewerHandler.getViewer(sender);

        if(args.size() > 0)
        {
            v = YAMLViewerHandler.getViewer(args.get(0));
            bot.sendViewerMessage(sender, v.getUsername() + " has " + v.getAmount() + " points");
        }
        else
            bot.sendViewerMessage(sender, "you have " + v.getAmount() + " points");
    }
}
