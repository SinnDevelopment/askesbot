package com.sinndevelopment.askesbot.commands;

import java.util.List;

public class PrintCommand extends ChatCommand
{
    public PrintCommand()
    {
        super("print", PermissionLevel.MODERATOR);
    }

    @Override
    protected void onCommand(String channel, String sender, String login, String hostname, List<String> args)
    {
        StringBuilder sb = new StringBuilder();
        for(String s : args)
        {
            if(s.equals("print")) continue;

            sb.append(s).append(" ");
        }
        bot.sendChannelMessage(sb.toString());
    }
}
