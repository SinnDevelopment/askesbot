package com.sinndevelopment.askesbot.commands;

import org.pircbotx.hooks.events.PrivateMessageEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.List;

public class BanCommand extends ChatCommand
{
    public BanCommand()
    {
        super("ban", PermissionLevel.MODERATOR);
    }


    @Override
    protected void onPMCommand(PrivateMessageEvent event, String sender, List<String> args)
    {

    }

    @Override
    protected void onCommand(GenericMessageEvent event, String sender, List<String> args)
    {
        if (args.size() > 0)
        {
            String strippedUser = args.get(0).replaceAll("@", "");
            bot.replyMessage(event, "/ban " + strippedUser);
            bot.replyMessage(event, "/me I literally have no idea who you are. Have a good one!");
        }
    }
}
