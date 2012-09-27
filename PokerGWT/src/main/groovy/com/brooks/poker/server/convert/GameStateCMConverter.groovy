package com.brooks.poker.server.convert

import com.brooks.poker.cards.Card
import com.brooks.poker.cards.Card.Suit
import com.brooks.poker.cards.Card.Value
import com.brooks.poker.client.model.CardCM
import com.brooks.poker.client.model.GameStateCM
import com.brooks.poker.game.data.CommunityCards
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
        
        clientModel.id = data.id;
        clientModel.allUsers = userPlayerConverter.convert(data.getGameState().table.sortedActivePlayers)
        clientModel.potState = potCMConverter.convert(data.getGameState().pots)
        clientModel.communityCards = cardCMConverter.convert(data.gameState.communityCards.getCards())
        clientModel.usersTurnIndex = userAction(data.getGameState());
    }
    


    private int userAction(){
        return -1;
    }
}
