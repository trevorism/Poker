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
    private List<PotCM> pots;
    
    public void addPot(PotCM pot){
        checkPotsNotNull();
        pots.add(pot);
    }
    
    public PotCM getPot(int index){
        checkPotsNotNull();
        return pots.get(index);
    }

    public List<PotCM> getPots(){
        checkPotsNotNull();
        return pots;
    }

    public void clear(){
        checkPotsNotNull();
        pots.clear();
    }
       
    public boolean hasOnePot(){
        checkPotsNotNull();
        return pots.size() == 1;
    }

    private void checkPotsNotNull(){
        if(pots == null)
            pots = new LinkedList<PotCM>();
    }
}
