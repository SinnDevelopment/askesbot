package com.sinndevelopment.askesbot.commands;

import com.sinndevelopment.askesbot.data.Viewer;
import com.sinndevelopment.askesbot.data.YAMLViewerHandler;
import org.pircbotx.hooks.events.PrivateMessageEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.List;

public class AddPointsCommand extends ChatCommand
{

    public AddPointsCommand()
    {
        super("addpoints", PermissionLevel.MODERATOR);
    }


    @Override
    protected void onPMCommand(PrivateMessageEvent event, String sender, List<String> args)
    {

    }

    @Override
    protected void onCommand(GenericMessageEvent event, String sender, List<String> args)
    {
        if (args.size() < 2)
        {
            return;
        }
        Viewer v = YAMLViewerHandler.getViewer(args.get(0));
        v.addPoints(Integer.parseInt(args.get(1)));
        YAMLViewerHandler.saveViewer(v);
        bot.replyMessage(event, sender, "Successfully added " + args.get(1) + " points to " + args.get(0));
    }
}
