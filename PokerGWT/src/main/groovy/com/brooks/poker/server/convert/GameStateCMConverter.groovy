package com.brooks.poker.server.convert

import com.brooks.poker.client.model.GameStateCM
import com.brooks.poker.client.model.User
import com.brooks.poker.game.data.GameState
import com.brooks.poker.server.game.GameServer

/**
 * @author Trevor
 *
 */
class GameStateCMConverter{

    public GameStateCM convert(GameState gameState, String userNamesTurn = ""){
        GameStateCM clientModel = new GameStateCM()
        UserPlayerConverter userPlayerConverter = new UserPlayerConverter()
        PotCMConverter potCMConverter = new PotCMConverter()
        CardCMConverter cardCMConverter = new CardCMConverter()

        clientModel.allUsers = userPlayerConverter.convert(gameState.table.sortedActivePlayers)
        clientModel.potState = potCMConverter.convert(gameState.pots)
        clientModel.communityCards = cardCMConverter.convert(gameState.communityCards.getCards())
        clientModel.minRaiseAmount = gameState.getMinBet()
        clientModel.started = true
        clientModel.channelKey = GameServer.getInstance().getChannelFromGameState(gameState)
        clientModel.usersTurnIndex = findUserIndex(clientModel.allUsers, userNamesTurn)

        return clientModel;
    }

    private int findUserIndex(List<User> allUsers, String name){
        allUsers.findIndexOf { it.name == name }
    }
}
