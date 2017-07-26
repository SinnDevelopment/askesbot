package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.data.Viewer;

public class PetReward extends Reward
{
    public PetReward()
    {
        super(15, "petpets", "pets");
    }

    @Override
    public boolean redeem(Viewer v, int count)
    {
        bot.sendChannelMessage("@Askesienne, " + v.getUsername() + " requests a pet be petted! askesLove askesCoopa askesHazel askesiGOLD");
        return true;
    }
}
