package com.sinndevelopment.askesbot.commands;

import com.sinndevelopment.askesbot.data.YAMLViewerHandler;
import com.sinndevelopment.askesbot.points.Viewer;

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

        bot.sendChannelMessage("@"+sender + " you have " + v.getCount() + " points");
    }
}
