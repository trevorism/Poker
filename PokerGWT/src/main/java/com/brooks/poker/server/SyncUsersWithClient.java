package com.brooks.poker.server;

import java.util.List;

import no.eirikb.gwtchannelapi.server.ChannelServer;

import com.brooks.poker.client.push.UserMessage;
import com.brooks.poker.server.convert.UserPlayerConverter;
import com.brooks.poker.server.game.GameServer;

/**
 * @author Trevor
 * 
 */
public final class SyncUsersWithClient implements Runnable{
   
    @Override
    public void run(){
        waitForAsyncReceipt();
        UserPlayerConverter converter = new UserPlayerConverter();
        List<UserMessage> umList = converter.convertMapToUserList(GameServer.getInstance().getPendingPlayers());
        for (UserMessage userAndIndex : umList){
            ChannelServer.send(GameServer.getInstance().getLatestChannelKey(), userAndIndex);
        }
    }

    private void waitForAsyncReceipt(){
        try{
            Thread.sleep(5000);
        }
        catch (InterruptedException e){

            e.printStackTrace();
        }
    }
}