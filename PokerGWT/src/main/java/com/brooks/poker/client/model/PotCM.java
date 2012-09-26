package com.brooks.poker.client.model;

import java.io.Serializable;

/**
 * @author Trevor
 *
 */
public class PotCM implements Serializable{
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
