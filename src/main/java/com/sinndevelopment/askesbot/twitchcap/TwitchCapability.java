package com.sinndevelopment.askesbot.twitchcap;

import org.pircbotx.hooks.events.UnknownEvent;

public abstract class TwitchCapability
{
    private UnknownEvent event;
    private String typeFlag;

    public TwitchCapability(UnknownEvent event)
    {
        this.event = event;
    }

    TwitchCapability(String typeFlag)
    {
        this.typeFlag = typeFlag;
    }

    public abstract void handle();

    public String getTypeFlag()
    {
        return typeFlag;
    }
}
