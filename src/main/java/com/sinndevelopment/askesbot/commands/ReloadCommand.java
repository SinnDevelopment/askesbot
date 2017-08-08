package com.sinndevelopment.askesbot.commands;

import com.sinndevelopment.askesbot.Main;
import com.sinndevelopment.askesbot.bot.ViewerTT;

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
        if(ViewerTT.isRunning())
        {
            bot.sendViewerMessage(sender, "TimerTask already running... but starting anyway.");
        }
        Main.startTT();
        bot.sendViewerMessage(sender, "Reloaded Timer Tasks.");
    }
}
