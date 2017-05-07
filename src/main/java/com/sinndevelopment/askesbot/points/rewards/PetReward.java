package com.sinndevelopment.askesbot.points.rewards;

import com.sinndevelopment.askesbot.points.Viewer;

public class PetReward extends Reward
{
    public PetReward()
    {
        super(120, "petpets", "pets");
    }

    @Override
    public boolean redeem(Viewer v, int count)
    {
        bot.sendChannelMessage("@Askesienne, " + v.getUsername() + " requests a pet be petted! askesLove askesDoggo askesHazel");
        return true;
    }
}
