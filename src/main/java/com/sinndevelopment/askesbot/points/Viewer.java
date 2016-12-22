package com.sinndevelopment.askesbot.points;

import com.sinndevelopment.askesbot.data.YAMLViewerHandler;

public class Viewer
{
    private int count;
    private String username;
    private boolean subscriber;

    public Viewer()
    {

    }

    public Viewer(String username)
    {
        Viewer viewer = YAMLViewerHandler.getViewer(username);
        this.count = viewer.count;
        this.username = username;
        this.subscriber = viewer.isSubscriber();
    }
    public Viewer(int count, String username, boolean subscriber)
    {
        this.count = count;
        this.username = username;
        this.subscriber = subscriber;
    }

    public boolean isSubscriber()
    {
        return subscriber;
    }

    public void setSubscriber(boolean subscriber)
    {
        this.subscriber = subscriber;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public int getCount()
    {
        return count;
    }

    public void addPoints(int amount)
    {
        this.count += amount;
    }

    public void addPoint()
    {
        addPoints(1);
    }
    public void setCount(int count)
    {
        this.count = count;
    }

    @Override
    public String toString()
    {
        return "Username: " + username + " Points: " + count + " Subscriber: " + subscriber;
    }
}
