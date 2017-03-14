package com.sinndevelopment.askesbot.commands;

import com.sinndevelopment.askesbot.bot.AskesBot;

import java.util.Arrays;
import java.util.List;

public abstract class ChatCommand
{
    private String name;
    private String prefix = "_";
    private PermissionLevel permissionLevel;
    protected AskesBot bot = AskesBot.getInstance();

    public ChatCommand(String name, PermissionLevel permissionLevel)
    {
        this.name = name;
        this.permissionLevel = permissionLevel;
    }

    public void onMessage(String channel, String sender, String login, String hostname, String message)
    {
        switch (permissionLevel)
        {
            case STREAMER:
                if(!sender.equals("askesienne"))
                {
                    noPermission(sender);
                    return;
                }
                break;
            case MODERATOR:
                if(!bot.getModerators().contains(sender))
                {
                    noPermission(sender);
                    return;
                }
                break;
            default:
                break;
        }

        List<String> args = Arrays.asList(message.split(" "));

        onCommand(channel, sender, login, hostname, args);
    }

    private void noPermission(String sender)
    {
        bot.sendChannelMessage("@" + sender + " You do not have permission to use " + name);
    }

    protected abstract void onCommand(String channel, String sender, String login, String hostname, List<String> args);

    public String getPrefix()
    {
        return prefix;
    }

    public String getName()
    {
        return name;
    }
}
