package com.sinndevelopment.askesbot;

import com.sinndevelopment.askesbot.bot.AskesBot;
import com.sinndevelopment.askesbot.bot.ViewerTT;
import org.pircbotx.PircBotX;

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
    private static PircBotX pircBotX;
    public static void main(String[] args) throws Exception
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


        AskesBot bot = new AskesBot(OAUTH_TWITCH);
        pircBotX = new PircBotX(bot.getConfiguration());
        pircBotX.startBot();
    }

    public static void reconnect()
    {
        pircBotX.stopBotReconnect();
    }

    public static PircBotX getPircBotX()
    {
        return pircBotX;
    }

    public static void startTT()
    {
        timer = null;
        timer = new Timer();
        timer.scheduleAtFixedRate(new ViewerTT(), 3000, 60 * 1000);
        System.out.println("Started Points Timer Task...");
    }

    public static Logger getLogger()
    {
        return logger;
    }
}
