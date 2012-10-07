package com.brooks.poker.server.test.convert

import org.junit.Test

import com.brooks.poker.cards.Card
import com.brooks.poker.cards.Card.Suit
import com.brooks.poker.cards.Card.Value
import com.brooks.poker.client.model.CardCM
import com.brooks.poker.client.model.User
import com.brooks.poker.player.Player
import com.brooks.poker.player.action.NullPlayerAction
import com.brooks.poker.server.convert.UserPlayerConverter



/**
 * @author Trevor
 *
 */
class UserPlayerConverterTest{

    @Test
    public void testConvertNewPlayerToUser(){
        Player player = new Player("Trevor", 1000, new NullPlayerAction())
        UserPlayerConverter converter = new UserPlayerConverter();
        List<User> users = converter.convert([player] as Set)
        
        assert users
        assert users.size() == 1
        User user = users.get(0)
        assert user.card1 == CardCM.NULL_CARD
        assert user.card2 == CardCM.NULL_CARD
        assert user.chips == 1000;
        assert user.name == "Trevor";
    }

    
    @Test
    public void testConvertPlayerWithHandToUser(){
        Player player = new Player("Trevor", 1000, new NullPlayerAction())
        player.hand.addCard(new Card(Suit.CLUBS, Value.ACE));
        player.hand.addCard(new Card(Suit.CLUBS, Value.FIVE));
        player.hand.addCard(new Card(Suit.DIAMONDS, Value.SEVEN));
        
        UserPlayerConverter converter = new UserPlayerConverter();
        List<User> users = converter.convert([player]  as Set)
        
        assert users
        assert users.size() == 1
        User user = users.get(0)
        assert user.card1.toString() == "ACE_CLUBS" 
        assert user.card2.toString() == "FIVE_CLUBS"
        assert user.chips == 1000;
        assert user.name == "Trevor";
    }
}
