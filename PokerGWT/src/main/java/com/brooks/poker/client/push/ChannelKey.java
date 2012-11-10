package com.brooks.poker.client.push;

import java.io.Serializable;

/**
 * @author Trevor
 * 
 */
public class ChannelKey implements Serializable{

    private static final long serialVersionUID = -199171398127719651L;

    private String clientKey;
    private long gameStateId;

    public ChannelKey(){
        
    }
    
    public ChannelKey(String clientKey, long gameStateId){
        this.clientKey = clientKey;
        this.gameStateId = gameStateId;
    }

    public String getClientKey(){
        return clientKey;
    }

    public void setClientKey(String clientKey){
        this.clientKey = clientKey;
    }

    public long getGameStateId(){
        return gameStateId;
    }

    public void setGameStateId(long gameStateId){
        this.gameStateId = gameStateId;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((clientKey == null) ? 0 : clientKey.hashCode());
        result = prime * result + (int) (gameStateId ^ (gameStateId >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChannelKey other = (ChannelKey) obj;
        if (clientKey == null){
            if (other.clientKey != null)
                return false;
        }
        else if (!clientKey.equals(other.clientKey))
            return false;
        if (gameStateId != other.gameStateId)
            return false;
        return true;
    }

}
