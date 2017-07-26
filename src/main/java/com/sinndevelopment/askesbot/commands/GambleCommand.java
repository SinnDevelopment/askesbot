package com.sinndevelopment.askesbot.commands;

import com.sinndevelopment.askesbot.data.Viewer;
import com.sinndevelopment.askesbot.data.YAMLViewerHandler;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GambleCommand extends ChatCommand
{
    public GambleCommand()
    {
        super("gamble", PermissionLevel.VIEWER);
    }
    @Override
    protected void onCommand(String channel, String sender, String login, String hostname, List<String> args)
    {
        if(args.size() == 0 || args.get(0).equals(""))
        {
            bot.sendViewerMessage(sender, "Please specify the amount to gamble.");
            return;
        }

        int gambleAmount = Integer.parseInt(args.get(0));

        Viewer viewer = YAMLViewerHandler.getViewer(sender);

        if(!viewer.charge(gambleAmount))
        {
            bot.sendViewerMessage(sender, "You do not have enough points!");
            return;
        }
        int rand = ThreadLocalRandom.current().nextInt(1, 11);

        if(rand > 5)
        {
            bot.sendViewerMessage(sender, "Congratulations! You won " + gambleAmount + " points");
            viewer.addPoints(2*gambleAmount);
        }
        else
        {
            bot.sendViewerMessage(sender, "The house wins this time! You lose " + gambleAmount);
        }
    }
}
