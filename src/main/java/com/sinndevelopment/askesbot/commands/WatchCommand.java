package com.sinndevelopment.askesbot.commands;

import java.util.List;

public class WatchCommand extends ChatCommand
{
    public WatchCommand()
    {
        super("watch", PermissionLevel.MODERATOR);
    }

    @Override
    protected void onCommand(String channel, String sender, String login, String hostname, List<String> args)
    {
        if(args.size() > 0)
        {
            String strippedUser = args.get(0).replaceAll("@", "");
            bot.sendChannelMessage("Please check out my friend " + strippedUser + " & follow at twitch.tv/" +strippedUser);
        }
    }
}
