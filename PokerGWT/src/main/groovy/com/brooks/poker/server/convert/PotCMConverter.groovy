package com.brooks.poker.server.convert

import com.brooks.poker.client.model.PotCM
import com.brooks.poker.client.model.PotState
import com.brooks.poker.game.data.pot.Pot
import com.brooks.poker.game.data.pot.Pots

class PotCMConverter{

    public PotState convert(Pots pots){
        PotState potState = new PotState();
        pots.getPots().each {
            potState.addPot(convertPot(it))
        }
        if(pots.getPots().size() == 0)
            potState.addPot(new PotCM())
        return potState
    }
    
    private PotCM convertPot(Pot pot){
        new PotCM(pot: pot.pot, amountOwed: pot.amountOwed)
    }
}
