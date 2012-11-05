package com.brooks.poker.server;


/**
 * @author Trevor
 * 
 */
public final class SyncUsersWithClient implements Runnable{

    private final String channelId;
    public SyncUsersWithClient(String channelId){
        this.channelId = channelId;
    }

    @Override
    public void run(){
        waitForAsyncReceipt();
//        List<PendingUser> pendingUsers = DataStoreUtils.queryForPendingPlayers();
//        
//        for (PendingUser pendingUser : pendingUsers){
//            UserMessage message = createUserMessage(pendingUser);
//            DataStoreUtils.setNextEvent(channelId, message);
//        }
    }

//    private UserMessage createUserMessage(PendingUser pendingUser){
//        User user = new User();
//        user.setName(pendingUser.getName());
//        UserMessage message = new UserMessage(user,pendingUser.getIndex());
//        return message;
//    }

    private void waitForAsyncReceipt(){
        try{
            Thread.sleep(5000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}