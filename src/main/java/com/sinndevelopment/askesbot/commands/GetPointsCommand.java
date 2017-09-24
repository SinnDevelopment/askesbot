package com.sinndevelopment.askesbot.commands;

import com.sinndevelopment.askesbot.data.Viewer;
import com.sinndevelopment.askesbot.data.YAMLViewerHandler;
import org.pircbotx.hooks.events.PrivateMessageEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.List;

public class GetPointsCommand extends ChatCommand
{
    public GetPointsCommand()
    {
        super("mypoints", PermissionLevel.VIEWER);
    }


    @Override
    protected void onPMCommand(PrivateMessageEvent event, String sender, List<String> args)
    {

    }

    @Override
    protected void onCommand(GenericMessageEvent event, String sender, List<String> args)
    {
        Viewer v = YAMLViewerHandler.getViewer(sender);

        if (args.size() > 0)
        {
            v = YAMLViewerHandler.getViewer(args.get(0));
            bot.replyMessage(event, sender, v.getUsername() + " has " + v.getAmount() + " points");
        }
        else
            bot.replyMessage(event, sender, "you have " + v.getAmount() + " points");
    }
}
