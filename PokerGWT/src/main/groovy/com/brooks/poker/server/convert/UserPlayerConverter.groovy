package com.brooks.poker.server.convert;

import com.brooks.poker.client.model.CardCM
import com.brooks.poker.client.model.User
import com.brooks.poker.player.Player
import com.brooks.poker.player.action.NullPlayerAction

/**
 * @author Trevor
 *
 */
public class UserPlayerConverter{

    public Player createNewPlayerFromUser(User user){
        new Player(user.name, 1000, new NullPlayerAction())
    }

    public List<User> convert(List<Player> players){
        CardCMConverter converter = new CardCMConverter();
        players.collect{ Player p ->
            List<CardCM> cards = converter.convert(p.getHand().getCards())
            User user = new User(name: p.name, chips: p.chipCount, pendingBet: p.pendingBet, sitting:true, inHand:true)
            if(cards.size() >= 2){
                user.card1 = cards.get(0)
                user.card2 = cards.get(1)
            }
            else
                user.clearCards();
            return user
        }
    }
}
