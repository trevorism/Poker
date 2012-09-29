package com.brooks.poker.client;

import java.io.Serializable;

/**
 * @author Trevor
 * 
 */
public class PokerException extends RuntimeException implements Serializable{

    private static final long serialVersionUID = 1L;

    public PokerException(){}
    
    public PokerException(String message){
        super(message);
    }

}
