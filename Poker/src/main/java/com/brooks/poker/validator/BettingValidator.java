package com.brooks.poker.validator;


/**
 * @author Trevor
 * 
 */
public interface BettingValidator{

    public boolean validateBet(int bet);

    public int getMinRaise();
}
