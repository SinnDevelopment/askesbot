package com.sinndevelopment.askesbot.bot;

import com.sinndevelopment.askesbot.commands.*;
import org.jibble.pircbot.PircBot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AskesBot extends PircBot
{
    private static AskesBot instance;

    private List<String> moderators = new ArrayList<>();
    private List<String> viewers = new ArrayList<>();
    private ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);

    private List<ChatCommand> commands = new ArrayList<>();
    private String helpString = "";

    private HashMap<String, Long> cooldown = new HashMap<>();

    public AskesBot()
    {
        this.setName("askesbot");
        this.setLogin("askesbot");
        this.setVersion("1.0");
        this.setVerbose(true);
        instance = this;
        commands.add(new AddPointsCommand());
        commands.add(new RedeemCommand());
        commands.add(new ToggleSubscriberCommand());
        commands.add(new GetPointsCommand());
        commands.add(new PrintCommand());

        ses.scheduleAtFixedRate(new ViewerTT(this), 3000, 60 * 1000, TimeUnit.MILLISECONDS);

        for (ChatCommand c : commands)
        {
            helpString += c.getPrefix() + c.getName() + ", ";
        }
    }

    public static AskesBot getInstance()
    {
        return instance;
    }

    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message)
    {
        if (message.startsWith("_help"))
        {
            sendChannelMessage("@" + sender + " my commands are: " + helpString);
        }
        for (ChatCommand command : commands)
        {
            if (message.startsWith(command.getPrefix() + command.getName()))
            {
                if (isCooldown(sender))
                    return;

                command.onMessage(channel, sender, login, hostname, message.substring(
                        command.getPrefix().length() + command.getName().length()));
                setCooldown(sender);
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


    @Override
    protected void onDisconnect()
    {
        while (!isConnected()) {
            try {
                reconnect();
            }
            catch (Exception e) {
                try
                {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException e1)
                {
                    e1.printStackTrace();
                }
            }
        }

    }

    public void setCooldown(String user)
    {
        cooldown.put(user, System.currentTimeMillis());
    }

    public long getCooldown(String user)
    {
        if (cooldown.containsKey(user))
            return cooldown.get(user) + 15 * 1000;
        return System.currentTimeMillis();
    }

    public boolean isCooldown(String user)
    {
        if (cooldown.containsKey(user))
        {
            long oldTime = getCooldown(user);
            if (System.currentTimeMillis() < oldTime)
            {
                return true;
            }
        }
        return false;
    }

    public void sendViewerMessage(String name, String mess)
    {
        sendChannelMessage("@" + name + " " + mess);
    }


}
