package com.sinndevelopment.askesbot;

import com.sinndevelopment.askesbot.bot.AskesBot;
import org.jibble.pircbot.IrcException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main
{
    public static String OAUTH_TWITCH;
    public static String REFRESH_TOKEN;
    public static void main(String[] args) throws IOException, IrcException
    {
        AskesBot bot = new AskesBot();

        if(args.length == 0)
        {
            try(BufferedReader reader = new BufferedReader(new FileReader(new File("api.txt"))))
            {
                String line;
                while((line = reader.readLine()) != null)
                {
                    if(line.startsWith("oauth:"))
                        OAUTH_TWITCH = line;
                    else if(line.startsWith("!"))
                        REFRESH_TOKEN = line.substring(1);
                    break;
                }
            }

            if(OAUTH_TWITCH.equals(""))
            {
                System.out.println("ERROR: Could not find the oauth key for Twitch login. Please either specify in arguments (0) or in the api.txt file");
                return;
            }
        }
        else
            OAUTH_TWITCH=args[0];

        bot.setVerbose(true);
        bot.connect("irc.chat.twitch.tv", 6667, OAUTH_TWITCH);
        bot.joinChannel("#askesienne");
    }
}
