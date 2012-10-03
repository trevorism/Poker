package com.brooks.poker.server.convert;

import com.brooks.poker.client.model.CardCM
import com.brooks.poker.client.model.User
import com.brooks.poker.client.push.UserMessage;
import com.brooks.poker.player.Player
import com.brooks.poker.server.playerAction.EventDrivenPlayerAction

/**
 * @author Trevor
 *
 */
public class UserPlayerConverter{

    public Player createNewPlayerFromUser(User user){
        new Player(user.name, 1000, new EventDrivenPlayerAction(user.name))
    }

    public List<User> convert(List<Player> players){
        CardCMConverter converter = new CardCMConverter();
        players.collect{ Player p ->
            convertUser(converter, p)
        }
    }

	private convertUser(CardCMConverter converter, Player p) {
		List<CardCM> cards = converter.convert(p.getHand().getCards())
		User user = new User(name: p.name, chips: p.chipCount, pendingBet: p.pendingBet, inHand:true)
		if(cards.size() >= 2){
			user.card1 = cards.get(0)
			user.card2 = cards.get(1)
		}
		else
			user.clearCards();
		return user
	}
    
    public List<Player> convertMapToList(Map<Integer, Player> playerMap){
        List<Player> pList = []
        playerMap.each {k,v -> pList << v }
        return pList
    }

    public List<UserMessage> convertMapToUserList(Map<Integer, Player> playerMap){
        CardCMConverter converter = new CardCMConverter();
        List<UserMessage> umList = []
        playerMap.each {k,v -> umList << new UserMessage(convertUser(converter, v), k) }
        return umList;
    }

}
