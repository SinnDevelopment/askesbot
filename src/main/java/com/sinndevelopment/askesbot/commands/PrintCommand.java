package com.sinndevelopment.askesbot.commands;

import org.pircbotx.hooks.events.PrivateMessageEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.List;

public class PrintCommand extends ChatCommand
{
    public PrintCommand()
    {
        super("print", PermissionLevel.MODERATOR);
    }


    @Override
    protected void onPMCommand(PrivateMessageEvent event, String sender, List<String> args)
    {

    }

    @Override
    protected void onCommand(GenericMessageEvent event, String sender, List<String> args)
    {
        StringBuilder sb = new StringBuilder();
        for (String s : args)
        {
            if (s.equals("print") || s.equals(" ")) continue;

            sb.append(s).append(" ");
        }
        bot.replyMessage(event, sb.toString());
    }
}
