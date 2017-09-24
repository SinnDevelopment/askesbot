package com.sinndevelopment.askesbot.commands;

import com.sinndevelopment.askesbot.bot.AskesBot;
import org.pircbotx.hooks.events.PrivateMessageEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.Arrays;
import java.util.List;

public abstract class ChatCommand
{
    protected AskesBot bot = AskesBot.getInstance();
    private String name;
    private String prefix = "_";
    private PermissionLevel permissionLevel;
    private String[] aliases = {};

    public ChatCommand(String name, PermissionLevel permissionLevel)
    {
        this.name = name;
        this.permissionLevel = permissionLevel;
    }

    public ChatCommand(String name, PermissionLevel permissionLevel, String... aliases)
    {
        this(name, permissionLevel);
        if (aliases != null)
        {
            System.arraycopy(aliases, 0, this.aliases, 0, aliases.length);
        }
    }

    public void onMessage(String sender, String message, GenericMessageEvent event)
    {
        if (!checkPermissions(sender, permissionLevel))
        {
            bot.replyMessage(event, sender, "You do not have permission to use "  + name + ". It requires " + permissionLevel.toString());
            return;
        }

        List<String> args = Arrays.asList(message.split(" "));
        if (args.size() >= 1)
            args = args.subList(1, args.size());

        onCommand(event, sender, args);
    }

    public void onPMRecieved(String sender, String message, PrivateMessageEvent event)
    {
        if (!checkPermissions(sender, permissionLevel))
        {
            bot.replyMessage(event, sender, "You do not have permission to use " + name + ". It requires " + permissionLevel.toString());
            return;
        }

        List<String> args = Arrays.asList(message.split(" "));
        if (args.size() >= 1)
            args = args.subList(1, args.size());

        onPMCommand(event, sender, args);
    }

    protected abstract void onPMCommand(PrivateMessageEvent event, String sender, List<String> args);

    protected abstract void onCommand(GenericMessageEvent event, String sender, List<String> args);

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
        for (String a : aliases)
            if (a.equals(prefix + s)) return true;
        return false;
    }

    private boolean checkPermissions(String sender, PermissionLevel perms)
    {
        switch (perms)
        {
            case STREAMER:
                if (!sender.equalsIgnoreCase("askesienne"))
                    return false;
                break;
            case VIEWER:
                break;
            case SUBSCRIBER:
                break;
            case MODERATOR:
                if (sender.equalsIgnoreCase("jamiesinn") || sender.equalsIgnoreCase("askesienne"))
                    break;
                if (!bot.getModerators().contains(sender.toLowerCase()))
                    return false;
                break;
        }
        return true;
    }
}
