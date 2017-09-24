package com.sinndevelopment.askesbot.bot;

import com.sinndevelopment.askesbot.commands.*;
import com.sinndevelopment.askesbot.data.TokenLogger;
import com.sinndevelopment.askesbot.hooks.AskesbotWebHandler;
import com.sinndevelopment.askesbot.hooks.GameWispHandler;
import com.sinndevelopment.askesbot.hooks.StreamLabsHandler;
import com.sinndevelopment.askesbot.hooks.TwitchAPIHandler;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.cap.EnableCapHandler;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.PrivateMessageEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AskesBot extends ListenerAdapter
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

    private PircBotX pircBotX;

    private AskesBot()
    {
    }

    public AskesBot(String oauth) throws IOException, IrcException
    {
        Configuration config = new Configuration.Builder()
                .setAutoNickChange(false)
                .setOnJoinWhoEnabled(false)
                .setCapEnabled(true)
                .addCapHandler(new EnableCapHandler("twitch.tv/membership"))
                .addCapHandler(new EnableCapHandler("twitch.tv/tags"))
                .addCapHandler(new EnableCapHandler("twitch.tv/commands"))
                .addServer("irc.twitch.tv")
                .setName("askesbot")
                .setServerPassword(oauth)
                .addListener(new AskesBot())
                .setVersion("3.0")
                .buildConfiguration();

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

        pircBotX = new PircBotX(config);
        pircBotX.startBot();
    }

    public static AskesBot getInstance()
    {
        return instance;
    }


    @Override
    public void onGenericMessage(final GenericMessageEvent event) throws Exception
    {
        String message = event.getMessage();
        System.out.println(message);
        String sender = event.getUser().getNick();

        if (event instanceof PrivateMessageEvent)
        {
            PrivateMessageEvent pmevent = (PrivateMessageEvent) event;
            ChatCommand command = isCommand(message);
            if (command != null)
            {
                if (isCooldown(sender))
                    return;

                command.onPMRecieved(sender, message.substring(
                        command.getPrefix().length() + command.getName().length()), pmevent);
                setCooldown(sender);
            }
            return;
        }

        if (message.startsWith("_help"))
        {
            this.replyMessage(event, "@" + sender + " my commands are: " + helpString);
            return;
        }

        ChatCommand command = isCommand(message);
        if (command != null)
        {
            if (isCooldown(sender))
                return;

            command.onMessage(sender, message.substring(
                    command.getPrefix().length() + command.getName().length()), event);
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

    public void replyMessage(GenericMessageEvent event, String message)
    {
        event.respondWith(message);
    }

    public void replyMessage(GenericMessageEvent event,  String user, String message)
    {
        replyMessage(event, "@" + user + ": " + message);
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
