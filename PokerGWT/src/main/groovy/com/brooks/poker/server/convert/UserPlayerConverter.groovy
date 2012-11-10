package com.brooks.poker.server.convert;

import com.brooks.poker.client.model.CardCM
import com.brooks.poker.client.model.User
import com.brooks.poker.game.data.GameState
import com.brooks.poker.player.Player


/**
 * @author Trevor
 *
 */
public class UserPlayerConverter{

    public List<User> convert(Set<Player> players, GameState gameState = null){
        CardCMConverter converter = new CardCMConverter();
        players.collect{ Player p ->
            convertUser(converter, p, gameState)
        }
    }

	private convertUser(CardCMConverter converter, Player p, GameState gameState = null) {
		List<CardCM> cards = converter.convert(p.getHand().getCards())
        
        boolean inHand = true;
        if(gameState && gameState.id != -1)
            inHand = !(gameState.getTable().isInactive(p))
        
		User user = new User(name: p.name, chips: p.chipCount, pendingBet: p.pendingBet, inHand:inHand)
		if(cards.size() >= 2){
			user.card1 = cards.get(0)
			user.card2 = cards.get(1)
		}
		else
			user.clearCards();
		return user
	}
    


}
