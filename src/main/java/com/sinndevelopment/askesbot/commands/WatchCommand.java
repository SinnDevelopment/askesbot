package com.sinndevelopment.askesbot.commands;

import java.util.List;

public class WatchCommand extends ChatCommand
{
    public WatchCommand()
    {
        super("watch", PermissionLevel.MODERATOR);
    }

    @Override
    protected void onCommand(String channel, String sender, String login, String hostname, List<String> args)
    {
        if (args.size() > 0)
        {

            String strippedUser = args.get(0).replaceAll("@", "");
            if (bot.getTeamKittyMembers().containsKey(strippedUser.toLowerCase()))
            {
                bot.sendChannelMessage("Please check out my amazing teammate " + strippedUser + " & follow at twitch.tv/" + strippedUser + ". They were last playing "
                        + bot.getTeamKittyMembers().get(strippedUser));
                try
                {
                    bot.setTeamKittyMembers(bot.getTwitchAPIHandler().getTeamKittyMembers());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {

                String friend = "";
                if (args.get(0).contains("edrost"))
                {
                    friend = "wedding ";
                }
                if(args.get(0).equalsIgnoreCase("lantheos"))
                {
                    friend = "pirate ";
                }
                bot.sendChannelMessage("Please check out my " + friend + "friend " + strippedUser + " & follow at twitch.tv/" + strippedUser);
            }
        }
    }
}
