package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.data.Viewer;

public class PetReward extends Reward
{
    public PetReward()
    {
        super(120, "petpets", "pets");
    }

    @Override
    public boolean redeem(Viewer v, int count)
    {
        bot.sendChannelMessage("@Askesienne, " + v.getUsername() + " requests a pet be petted! askesLove askesCoopa askesHazel");
        return true;
    }
}
