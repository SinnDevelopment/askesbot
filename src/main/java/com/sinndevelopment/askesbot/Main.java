package com.sinndevelopment.askesbot;

import com.sinndevelopment.askesbot.bot.AskesBot;
import org.jibble.pircbot.IrcException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException, IrcException
    {
        AskesBot bot = new AskesBot();

        String OAUTH = "";
        if(args.length == 0)
        {
            try(BufferedReader reader = new BufferedReader(new FileReader(new File("api.txt"))))
            {
                String line;
                while((line = reader.readLine()) != null)
                {
                    if(!line.startsWith("oauth:")) continue;

                    OAUTH = line;
                    break;
                }
            }

            if(OAUTH.equals(""))
            {
                System.out.println("ERROR: Could not find the oauth key for Twitch login. Please either specify in arguments (0) or in the api.txt file");
                return;
            }
        }
        else
            OAUTH=args[0];

        bot.setVerbose(true);
        bot.connect("irc.chat.twitch.tv", 6667, OAUTH);
        bot.joinChannel("#askesienne");
    }
}
