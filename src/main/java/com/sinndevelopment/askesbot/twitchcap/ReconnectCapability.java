package com.sinndevelopment.askesbot.twitchcap;

import com.sinndevelopment.askesbot.Main;

public class ReconnectCapability extends TwitchCapability
{
    public ReconnectCapability()
    {
        super("RECONNECT");
    }

    @Override
    public void handle()
    {
        Main.reconnect();
    }
}
