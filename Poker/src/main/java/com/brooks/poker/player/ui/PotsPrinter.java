package com.brooks.poker.player.ui;

import java.util.List;

import com.brooks.poker.game.data.pot.Pot;
import com.brooks.poker.game.data.pot.Pots;

/**
 * @author Trevor
 * 
 */
public class PotsPrinter{

    public static String createPotsString(Pots pots){
        List<Pot> potList = pots.getPots();

        if (potList.isEmpty()){
            return "";
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0, n = potList.size(); i < n; i++){
            String label = "Subpot " + i + ": ";
            if (i == 0){
                label = "Main pot: ";
            }

            Pot pot = potList.get(i);
            builder.append(label);
            builder.append(pot.getPotAmount());
            builder.append("  Current bet: ");
            builder.append(pot.getAmountOwed());
            builder.append("\n");
        }

        return builder.toString();
    }
}
