package com.sinndevelopment.askesbot.hooks;

import com.sinndevelopment.askesbot.twitchcap.*;

import java.util.ArrayList;
import java.util.List;

public class TwitchCapHandler
{
    private List<TwitchCapability> twitchCapabilityList = new ArrayList<>();

    public TwitchCapHandler()
    {
        twitchCapabilityList.add(new ClearChatCapability());
        twitchCapabilityList.add(new PrivateMessageCapability());
        twitchCapabilityList.add(new RoomStateCapability());
        twitchCapabilityList.add(new UserNoticeCapability());
    }

    public List<TwitchCapability> getCaps()
    {
        return twitchCapabilityList;
    }
}
