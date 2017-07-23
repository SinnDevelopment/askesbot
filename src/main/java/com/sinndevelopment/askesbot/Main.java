package com.sinndevelopment.askesbot;

import com.sinndevelopment.askesbot.bot.AskesBot;
import com.sinndevelopment.askesbot.bot.ViewerTT;
import org.jibble.pircbot.IrcException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main
{
    private static String OAUTH_TWITCH = "";
    private static Logger logger = Logger.getLogger("Askesbot");
    private static Timer timer = new Timer();
    private static AskesBot bot;

    public static void main(String[] args) throws IOException, IrcException
    {
        try
        {
            FileHandler fh = new FileHandler("askes.log", true);
            logger.setUseParentHandlers(false);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        }
        catch (SecurityException | IOException e)
        {
            e.printStackTrace();
        }

        AskesBot bot = new AskesBot();

        if (args.length == 0)
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(new File("api.txt"))))
            {
                String line;
                while ((line = reader.readLine()) != null)
                {
                    if (line.startsWith("oauth:"))
                        OAUTH_TWITCH = line;
                }
            }

            if (OAUTH_TWITCH.equals(""))
            {
                logger.info("ERROR: Could not find the oauth key for Twitch login. Please either specify in arguments (0) or in the api.txt file");
                return;
            }
        }
        else
            OAUTH_TWITCH = args[0];

        bot.setVerbose(true);
        bot.connect("irc.chat.twitch.tv", 6667, OAUTH_TWITCH);
        bot.joinChannel("#askesienne");
        Main.bot = bot;

        startTT(false);

    }

    public static void startTT(boolean stop)
    {
        if(stop)
            timer.cancel();

        timer.scheduleAtFixedRate(new ViewerTT(bot), 3000, 60 * 1000);
    }
    public static Logger getLogger()
    {
        return logger;
    }
}
