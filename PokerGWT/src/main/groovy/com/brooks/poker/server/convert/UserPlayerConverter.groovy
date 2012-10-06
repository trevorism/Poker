package com.brooks.poker.server.convert;

import com.brooks.poker.client.model.CardCM
import com.brooks.poker.client.model.User
import com.brooks.poker.client.push.UserMessage
import com.brooks.poker.game.data.GameState
import com.brooks.poker.player.Player
import com.brooks.poker.server.playerAction.EventDrivenPlayerAction


/**
 * @author Trevor
 *
 */
public class UserPlayerConverter{

    public List<User> convert(List<Player> players, GameState gameState = null){
        CardCMConverter converter = new CardCMConverter();
        players.collect{ Player p ->
            convertUser(converter, p, gameState)
        }
    }

	private convertUser(CardCMConverter converter, Player p, GameState gameState = null) {
		List<CardCM> cards = converter.convert(p.getHand().getCards())
        
		User user = new User(name: p.name, chips: p.chipCount, pendingBet: p.pendingBet, inHand:!gameState?.getTable()?.isInactive(p))
		if(cards.size() >= 2){
			user.card1 = cards.get(0)
			user.card2 = cards.get(1)
		}
		else
			user.clearCards();
		return user
	}
    


}
