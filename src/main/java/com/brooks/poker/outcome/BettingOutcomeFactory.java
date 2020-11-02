package com.brooks.poker.outcome;



/**
 * @author Trevor
 * 
 */
public class BettingOutcomeFactory{

    public static CallOutcome createCallOutcome(){
        return new CallOutcome();
    }

    public static FoldOutcome createFoldOutcome(){
        return new FoldOutcome();
    }

    public static RaiseOutcome createRaiseOutcome(int fixedBet){
        return new RaiseOutcome(fixedBet);
    }

    public static BlindsOutcome createBlindsOutcome(int fixedBet){
        return new BlindsOutcome(fixedBet);
    }
}
