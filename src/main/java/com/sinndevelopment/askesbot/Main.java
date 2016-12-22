package com.sinndevelopment.askesbot;

import com.sinndevelopment.askesbot.bot.AskesBot;
import org.jibble.pircbot.IrcException;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException, IrcException
    {
        if(args.length < 1)
        {
            System.out.println("ERROR: Please specify the oauth password for the bot account.");
            return;
        }

        AskesBot bot = new AskesBot();

        bot.setVerbose(true);
        bot.connect("irc.chat.twitch.tv", 443, args[0]);
        bot.joinChannel("#askesienne");
    }
}
