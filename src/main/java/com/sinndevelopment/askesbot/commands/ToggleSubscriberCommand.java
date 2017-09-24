package com.sinndevelopment.askesbot.commands;

import com.sinndevelopment.askesbot.data.Viewer;
import com.sinndevelopment.askesbot.data.YAMLViewerHandler;
import org.pircbotx.hooks.events.PrivateMessageEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.List;

public class ToggleSubscriberCommand extends ChatCommand
{
    public ToggleSubscriberCommand()
    {
        super("togglesub", PermissionLevel.MODERATOR);
    }

    @Override
    protected void onPMCommand(PrivateMessageEvent event, String sender, List<String> args)
    {

    }

    @Override
    protected void onCommand(GenericMessageEvent event, String sender, List<String> args)
    {
        if (args.size() < 1)
        {
            return;
        }
        Viewer v = YAMLViewerHandler.getViewer(args.get(0));

        v.setSubscriber(!v.isSubscriber());
        bot.replyMessage(event, sender, "successfully set " + args.get(0) + "'s subscriber status to: " + v.isSubscriber());
    }
}
