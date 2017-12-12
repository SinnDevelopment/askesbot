package com.sinndevelopment.askesbot.test;

import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.UserHostmask;
import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class FakeGenericMessageEvent implements GenericMessageEvent
{
    private String message;
    private String response;

    public FakeGenericMessageEvent(String message)
    {
        this.message = message;
    }
    @Override
    public String getMessage()
    {
        return message;
    }

    @Override
    public void respondPrivateMessage(String s)
    {

    }

    @Override
    public void respondWith(String s)
    {
        response = s;
    }

    @Override
    public UserHostmask getUserHostmask()
    {
        return null;
    }

    @Override
    public User getUser()
    {
        return null;
    }

    @Override
    public void respond(String s)
    {

    }

    @Override
    public <T extends PircBotX> T getBot()
    {
        return null;
    }

    @Override
    public long getTimestamp()
    {
        return 0;
    }

    @Override
    public int compareTo(Event o)
    {
        return 0;
    }

    public String getResponse()
    {
        return response;
    }
}
