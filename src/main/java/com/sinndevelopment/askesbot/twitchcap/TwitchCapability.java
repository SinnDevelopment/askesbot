package com.sinndevelopment.askesbot.twitchcap;

import java.util.HashMap;

public abstract class TwitchCapability
{
    private HashMap<String, String> tags = new HashMap<>();
    private String typeFlag;

    public TwitchCapability(HashMap<String, String> tags)
    {
        this.tags = tags;
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
