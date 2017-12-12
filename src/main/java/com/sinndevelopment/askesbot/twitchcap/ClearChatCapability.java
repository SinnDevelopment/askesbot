package com.sinndevelopment.askesbot.twitchcap;

public class ClearChatCapability extends TwitchCapability
{
    public ClearChatCapability()
    {
        super("CLEARCHAT");
    }

    @Override
    public void handle()
    {
        System.out.println("Unknown event was a clearchat.");
        this.event.getBot().sendIRC().message("#askesienne", "I literally have no idea who you are. Have a good one!");
    }
}
