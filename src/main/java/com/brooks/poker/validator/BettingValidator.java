package com.brooks.poker.validator;


/**
 * @author Trevor
 * 
 */
public interface BettingValidator{

    boolean validateBet(int bet);

    int getMinRaise();
}
