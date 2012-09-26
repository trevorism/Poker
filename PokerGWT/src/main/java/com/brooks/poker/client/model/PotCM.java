package com.brooks.poker.client.model;

/**
 * @author Trevor
 *
 */
public class PotCM{
    private static final long serialVersionUID = 1L;
    
    private int pot;
    private int amountOwed;
    
    public int getPot(){
        return pot;
    }
    public void setPot(int pot){
        this.pot = pot;
    }
    public int getAmountOwed(){
        return amountOwed;
    }
    public void setAmountOwed(int amountOwed){
        this.amountOwed = amountOwed;
    }
}
