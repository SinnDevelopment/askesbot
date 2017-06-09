package com.sinndevelopment.askesbot.commands;

import java.util.List;

public class BanCommand extends ChatCommand
{
    public BanCommand()
    {
        super("ban", PermissionLevel.MODERATOR);
    }

    @Override
    protected void onCommand(String channel, String sender, String login, String hostname, List<String> args)
    {
        if(args.size() > 0)
        {
            String strippedUser = args.get(0).replaceAll("@", "");
            bot.sendChannelMessage("/ban " + strippedUser);
            bot.sendChannelMessage("/me I literally have no idea who you are. Have a good one!");
        }
    }
}
