package com.brooks.poker.server.convert

import com.brooks.poker.client.model.GameStateCM
import com.brooks.poker.client.model.User
import com.brooks.poker.game.data.GameState
import com.brooks.poker.game.data.Table
import com.brooks.poker.player.Player
import com.brooks.poker.server.game.GameStateData

/**
 * @author Trevor
 *
 */
class GameStateCMConverter{

    public GameStateCM convert(GameStateData data){
        GameStateCM clientModel = new GameStateCM()
        UserPlayerConverter userPlayerConverter = new UserPlayerConverter()
        PotCMConverter potCMConverter = new PotCMConverter()
        CardCMConverter cardCMConverter = new CardCMConverter()
        
        GameState gameState = data.gameState
        
        clientModel.id = data.id;
        clientModel.allUsers = userPlayerConverter.convert(gameState.table.sortedActivePlayers)
        clientModel.potState = potCMConverter.convert(gameState.pots)
        clientModel.communityCards = cardCMConverter.convert(gameState.communityCards.getCards())
        
        clientModel.minRaiseAmount = gameState.minBet     
        clientModel.usersTurnIndex = -1 //TODO: What does it really equal?
        
    }



    private int findUserIndex(List<User> allUsers, String name){
        allUsers.findIndexOf {
            it.name == name
        }
    }
}
