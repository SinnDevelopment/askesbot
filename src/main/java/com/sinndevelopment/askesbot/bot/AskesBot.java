package com.sinndevelopment.askesbot.bot;

import com.sinndevelopment.askesbot.commands.*;
import com.sinndevelopment.askesbot.data.TokenLogger;
import com.sinndevelopment.askesbot.hooks.AskesbotWebHandler;
import com.sinndevelopment.askesbot.hooks.GameWispHandler;
import com.sinndevelopment.askesbot.hooks.StreamLabsHandler;
import com.sinndevelopment.askesbot.hooks.TwitchAPIHandler;
import org.jibble.pircbot.PircBot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AskesBot extends PircBot
{
    private static AskesBot instance;

    private List<String> moderators = new ArrayList<>();
    private List<String> viewers = new ArrayList<>();

    private List<ChatCommand> commands = new ArrayList<>();
    private StringBuilder helpString = new StringBuilder();

    private HashMap<String, Long> cooldown = new HashMap<>();

    private StreamLabsHandler streamLabs;
    private GameWispHandler gameWisp;
    private TwitchAPIHandler twitchAPIHandler;
    private AskesbotWebHandler askesbotWebHandler;

    private HashMap<String, String> teamKittyMembers = new HashMap<>();

    public AskesBot()
    {
        this.setName("askesbot");
        this.setLogin("askesbot");
        this.setVersion("1.0");
        this.setVerbose(true);
        this.streamLabs = new StreamLabsHandler(new TokenLogger("streamlabs"));
        this.gameWisp = new GameWispHandler(new TokenLogger("gamewisp"));
        this.twitchAPIHandler = new TwitchAPIHandler(new TokenLogger("twitch"));
        this.askesbotWebHandler = new AskesbotWebHandler(new TokenLogger("askesbot-web"));
        instance = this;
        commands.add(new AddPointsCommand());
        commands.add(new RedeemCommand());
        commands.add(new ToggleSubscriberCommand());
        commands.add(new GetPointsCommand());
        commands.add(new PrintCommand());
        commands.add(new WatchCommand());
        commands.add(new BanCommand());
        commands.add(new ReloadCommand());
        commands.add(new GambleCommand());

        try
        {
            this.askesbotWebHandler.getMe();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        commands.forEach(c -> helpString.append(c.getPrefix()).append(c.getName()).append(", "));
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
        ChatCommand command = isCommand(message);
        if (command != null)
        {
            if (isCooldown(sender))
                return;

            command.onMessage(channel, sender, login, hostname, message.substring(
                    command.getPrefix().length() + command.getName().length()));
            setCooldown(sender);
        }
    }

    @Override
    protected void onPrivateMessage(String sender, String login, String hostname, String message)
    {
        ChatCommand command = isCommand(message);
        if (command != null)
        {
            if (isCooldown(sender))
                return;

            command.onPMRecieved(sender, login, hostname, message.substring(
                    command.getPrefix().length() + command.getName().length()));
            setCooldown(sender);
        }
    }

    private ChatCommand isCommand(String chat)
    {
        for (ChatCommand command : commands)
        {
            if (chat.startsWith(command.getFullCommand())
                    || command.isAlias(chat))
                return command;
        }
        return null;
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

    public void sendUserMessage(String user, String message)
    {
        this.sendMessage(user, message);
    }

    public void reload()
    {
        try
        {
            this.reconnect();
            this.joinChannel("#askesienne");
        }
        catch (Exception e)
        {
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

    @Override
    protected void onDisconnect()
    {
        while (!isConnected())
        {
            reload();
        }
    }

    public void setCooldown(String user)
    {
        cooldown.put(user, System.currentTimeMillis());
    }

    public long getCooldown(String user)
    {
        if (cooldown.containsKey(user))
            return cooldown.get(user) + 5 * 1000;
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


    public StreamLabsHandler getStreamLabs()
    {
        return streamLabs;
    }

    public GameWispHandler getGameWisp()
    {
        return gameWisp;
    }

    public TwitchAPIHandler getTwitchAPIHandler()
    {
        return twitchAPIHandler;
    }

    public HashMap<String, String> getTeamKittyMembers()
    {
        return teamKittyMembers;
    }

    public void setTeamKittyMembers(HashMap<String, String> teamKittyMembers)
    {
        this.teamKittyMembers = teamKittyMembers;
    }

    public AskesbotWebHandler getAskesbotWebHandler()
    {
        return askesbotWebHandler;
    }
}
