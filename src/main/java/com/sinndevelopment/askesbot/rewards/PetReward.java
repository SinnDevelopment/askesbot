package com.sinndevelopment.askesbot.rewards;

import com.sinndevelopment.askesbot.data.Viewer;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class PetReward extends Reward
{
    public PetReward()
    {
        super(15, "petpets", "pets");
    }

    @Override
    public boolean redeem(Viewer v, int count, GenericMessageEvent event)
    {
        bot.replyMessage(event, "@Askesienne, " + v.getUsername() + " requests a pet be petted! askesLove askesCoopa askesHazel askesiGOLD");
        return true;
    }
}
