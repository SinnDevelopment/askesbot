package com.sinndevelopment.askesbot.test;

import com.sinndevelopment.askesbot.bot.AskesBot;
import com.sinndevelopment.askesbot.commands.WatchCommand;
import org.junit.Assert;

public class WatchCommandTest
{
    //@Test
    public void TestTeamKitty()
    {
        FakeGenericMessageEvent event = new FakeGenericMessageEvent("_watch KittyPlays");
        AskesBot bot = new AskesBot();

        WatchCommand command = new WatchCommand();
        command.setBot(bot);
        command.onMessage("Askesienne", event.getMessage(), event);
        Assert.assertEquals(event.getResponse(), "Please check out my amazing teammate KittyPlays & follow at twitch.tv/" +
                "KittyPlays - They were last playing " + bot.getTeamKittyMembers().get("KittyPlays"));
    }
}
