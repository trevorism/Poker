package com.brooks.poker.client.model;

import java.io.Serializable;

/**
 * @author Trevor
 *
 */
public class PotState implements Serializable{
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
