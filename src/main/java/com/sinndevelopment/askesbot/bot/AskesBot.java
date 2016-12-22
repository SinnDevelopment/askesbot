package com.sinndevelopment.askesbot.bot;

import com.sinndevelopment.askesbot.commands.AddPointsCommand;
import com.sinndevelopment.askesbot.commands.ChatCommand;
import com.sinndevelopment.askesbot.commands.RedeemCommand;
import org.jibble.pircbot.PircBot;

import java.util.ArrayList;
import java.util.List;

public class AskesBot extends PircBot
{
    private static AskesBot instance;

    private List<String> moderators = new ArrayList<>();
    private List<String> viewers = new ArrayList<>();

    private List<ChatCommand> commands = new ArrayList<>();

    public AskesBot()
    {
        this.setName("askesbot");
        instance = this;
        commands.add(new AddPointsCommand());
        commands.add(new RedeemCommand());
    }

    public static AskesBot getInstance()
    {
        return instance;
    }

    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message)
    {
        for(ChatCommand command : commands)
        {
            if(message.startsWith(command.getPrefix()+command.getName()))
            {
                command.onMessage(channel, sender, login, hostname, message);
            }
        }
    }

    public List<String> getModerators()
    {
        return moderators;
    }

    public void setModerators(List<String> moderators)
    {
        this.moderators = moderators;
    }

    public List<String> getViewers()
    {
        return viewers;
    }

    public void setViewers(List<String> viewers)
    {
        this.viewers = viewers;
    }

    public void sendChannelMessage(String message)
    {
        this.sendMessage("#askesienne", message);
    }


}
