package com.brooks.poker.client.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Trevor
 *
 */
public class PotState implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private final List<PotCM> pots;
    
    public PotState(){
        pots = new LinkedList<PotCM>();
    }

    public void addPot(PotCM pot){
        pots.add(pot);
    }
    
    public PotCM getPot(int index){
        while(pots.size() <= index)
            pots.add(new PotCM());
        
        return pots.get(index);
    }

    public List<PotCM> getPots(){
        return pots;
    }

    public void clear(){
        pots.clear();
    }
       
    public boolean hasOnePot(){
        return pots.size() == 1;
    }
}
