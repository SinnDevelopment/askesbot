package com.sinndevelopment.askesbot.twitchcap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class TwitchCapability
{
    private Pattern pattern;

    public TwitchCapability(String rawRegex)
    {
        pattern = Pattern.compile(rawRegex);
    }

    public boolean matches(String line)
    {
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }

    protected abstract void handle();
}
