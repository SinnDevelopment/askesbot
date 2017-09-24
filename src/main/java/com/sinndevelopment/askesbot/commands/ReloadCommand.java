package com.sinndevelopment.askesbot.commands;

import com.sinndevelopment.askesbot.Main;
import org.pircbotx.hooks.events.PrivateMessageEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.List;

public class ReloadCommand extends ChatCommand
{
    public ReloadCommand()
    {
        super("reload", PermissionLevel.MODERATOR);
    }

    @Override
    protected void onPMCommand(PrivateMessageEvent event, String sender, List<String> args)
    {

    }

    @Override
    protected void onCommand(GenericMessageEvent event, String sender, List<String> args)
    {
        Main.startTT();
        bot.replyMessage(event, sender, "Reloaded Timer Tasks.");
    }
}
