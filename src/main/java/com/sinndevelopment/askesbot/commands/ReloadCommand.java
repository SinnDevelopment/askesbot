package com.sinndevelopment.askesbot.commands;

import com.sinndevelopment.askesbot.Main;

import java.util.List;

public class ReloadCommand extends ChatCommand
{
    public ReloadCommand()
    {
        super("reload", PermissionLevel.MODERATOR);
    }
    @Override
    protected void onCommand(String channel, String sender, String login, String hostname, List<String> args)
    {
        Main.startTT(true);
        bot.sendViewerMessage(sender, "Reloaded Timer Tasks.");
    }
}
