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
    private String[] aliases = {};

    public ChatCommand(String name, PermissionLevel permissionLevel)
    {
        this.name = name;
        this.permissionLevel = permissionLevel;
    }

    public ChatCommand(String name, PermissionLevel permissionLevel, String... aliases)
    {
        this(name, permissionLevel);
        if(aliases != null)
        {
            System.arraycopy(aliases, 0, this.aliases, 0, aliases.length);
        }
    }

    public void onMessage(String channel, String sender, String login, String hostname, String message)
    {
        switch (permissionLevel)
        {
            case STREAMER:
                if(!sender.equalsIgnoreCase("askesienne"))
                {
                    noPermission(sender);
                    return;
                }
                break;
            case MODERATOR:
                if(!bot.getModerators().contains(sender.toLowerCase()))
                {
                    noPermission(sender);
                    return;
                }
                break;
            case SUBSCRIBER:
            default:
                break;
        }

        List<String> args = Arrays.asList(message.split(" "));
        if(args.size() >= 1)
            args = args.subList(1, args.size());

        onCommand(channel, sender, login, hostname, args);
    }

    private void noPermission(String sender)
    {
        bot.sendViewerMessage(sender, "You do not have permission to use " + name);
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

    public String getFullCommand()
    {
        return prefix + name;
    }

    public String[] getAliases()
    {
        return aliases;
    }

    public boolean isAlias(String s)
    {
        for(String a : aliases)
            if(a.equals(prefix+s)) return true;
        return false;
    }
}
