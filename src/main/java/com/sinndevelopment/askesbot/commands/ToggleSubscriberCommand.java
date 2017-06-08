package com.sinndevelopment.askesbot.commands;

import com.sinndevelopment.askesbot.data.YAMLViewerHandler;
import com.sinndevelopment.askesbot.data.Viewer;

import java.util.List;

public class ToggleSubscriberCommand extends ChatCommand
{
    public ToggleSubscriberCommand()
    {
        super("togglesub", PermissionLevel.MODERATOR);
    }

    @Override
    protected void onCommand(String channel, String sender, String login, String hostname, List<String> args)
    {
        if(args.size() < 1 )
        {
            return;
        }
        Viewer v = YAMLViewerHandler.getViewer(args.get(0));

        v.setSubscriber(!v.isSubscriber());
        bot.sendViewerMessage(sender, "successfully set " + args.get(0) + "'s subscriber status to: " + v.isSubscriber());
    }
}
