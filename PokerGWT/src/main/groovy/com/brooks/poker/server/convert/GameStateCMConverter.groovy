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
        
        clientModel.id = data.id;
        clientModel.allUsers = userPlayerConverter.convert(data.getGameState().table.sortedActivePlayers)
        clientModel.potState = potCMConverter.convert(data.getGameState().pots)
        clientModel.communityCards = convertCards(data.gameState.communityCards)
        
    }
    
    private List<CardCM> convertCards(CommunityCards cards){
        cards.getCards().collect{
            CardCM cm = new CardCM(it.getValue().toString(), it.getSuit().toString())
        }
    }

}
