package com.sinndevelopment.askesbot;

import com.sinndevelopment.askesbot.bot.AskesBot;
import org.jibble.pircbot.IrcException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main
{
    public static String OAUTH_TWITCH = "";
    public static String REFRESH_TOKEN = "";
    private static Logger logger = Logger.getLogger("Askesbot");
    private static FileHandler fh;

    public static void main(String[] args) throws IOException, IrcException
    {
        try
        {
            fh = new FileHandler("askes.log", true);
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
                    else if (line.startsWith("!"))
                        REFRESH_TOKEN = line.substring(1);

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
    }

    public static Logger getLogger()
    {
        return logger;
    }
}
