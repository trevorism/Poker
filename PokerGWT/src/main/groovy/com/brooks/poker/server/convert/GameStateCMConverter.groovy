package com.brooks.poker.server.convert

import com.brooks.poker.client.model.CardCM
import com.brooks.poker.client.model.GameStateCM
import com.brooks.poker.client.model.PotCM
import com.brooks.poker.client.model.PotState
import com.brooks.poker.client.model.User
import com.brooks.poker.game.data.GameState
import com.google.appengine.api.datastore.EmbeddedEntity
import com.google.appengine.api.datastore.Entity

/**
 * @author Trevor
 *
 */
class GameStateCMConverter{

    public static final String GAME_STATE_ENTITY = "gameState-entity";

    public GameStateCM convert(GameState gameState, boolean started, String userNamesTurn = ""){
        GameStateCM clientModel = new GameStateCM()
        UserPlayerConverter userPlayerConverter = new UserPlayerConverter()
        PotCMConverter potCMConverter = new PotCMConverter()
        CardCMConverter cardCMConverter = new CardCMConverter()

        clientModel.allUsers = userPlayerConverter.convert(gameState.table.allPlayers, gameState)
        clientModel.potState = potCMConverter.convert(gameState.pots)
        clientModel.communityCards = cardCMConverter.convert(gameState.communityCards.getCards())
        clientModel.minRaiseAmount = gameState.getMinBet()
        clientModel.started = started
        clientModel.actionOnUserName = userNamesTurn
        clientModel.id = gameState.getId();

        return clientModel;
    }


    public Entity convert(GameStateCM clientModel){
        Entity entity = new Entity(GAME_STATE_ENTITY,GAME_STATE_ENTITY)

        entity.setProperty("id", clientModel.id)
        entity.setProperty("actionOnUserName", clientModel.actionOnUserName)
        entity.setProperty("minRaiseAmount", clientModel.minRaiseAmount)
        entity.setProperty("started", clientModel.started)
        entity.setProperty("allUsers", createEmbeddedUsers(clientModel.allUsers))
        entity.setProperty("potState", createEmbeddedPot(clientModel.potState))
        entity.setProperty("communityCards", createEmbeddedCards(clientModel.communityCards))

        return entity
    }

    private List<EmbeddedEntity> createEmbeddedUsers(List<User> listUsers){
        listUsers.collect {
            EmbeddedEntity ee = new EmbeddedEntity()
            ee.setProperty("user-name", it.name)
            ee.setProperty("user-chips", it.chips)
            ee.setProperty("user-card1", createEmbeddedEntityFromCard(it.card1))
            ee.setProperty("user-card2", createEmbeddedEntityFromCard(it.card2))
            ee.setProperty("user-pendingBet", it.pendingBet)
            ee.setProperty("user-inHand", it.inHand)
            ee.setProperty("user-index", it.index)
            return ee
        }
    }

    private List<EmbeddedEntity> createEmbeddedPot(PotState potState){
        if(potState == null)
        potState = new PotState()
        potState.getPots().collect {
            EmbeddedEntity ee = new EmbeddedEntity()
            ee.setProperty("pot", it.pot)
            ee.setProperty("amountOwed", it.amountOwed)
            return ee
        }
    }

    private List<EmbeddedEntity> createEmbeddedCards(List<CardCM> cards){
        cards.collect { createEmbeddedEntityFromCard(it) }
    }

    private EmbeddedEntity createEmbeddedEntityFromCard(CardCM it) {
        EmbeddedEntity ee = new EmbeddedEntity()
        ee.setProperty("value", it.value)
        ee.setProperty("suit", it.suit)
        return ee
    }


    public GameStateCM convert(Entity entity){
        GameStateCM gameState = new GameStateCM()
        gameState.id = entity.getProperty("id")
        gameState.actionOnUserName = entity.getProperty("actionOnUserName")
        gameState.minRaiseAmount = entity.getProperty("minRaiseAmount")
        gameState.started = entity.getProperty("started")
        gameState.potState = createPot(entity.getProperty("potState"))
        gameState.communityCards = createCards(entity.getProperty("communityCards"))
        gameState.allUsers = createUsers(entity.getProperty("allUsers"))
        return gameState;
    }

    private List<User> createUsers(List<EmbeddedEntity> entities){
        entities.collect {
            User user = new User()
            user.name = it.getProperty("user-name")
            user.chips = it.getProperty("user-chips")
            user.pendingBet = it.getProperty("user-pendingBet")
            user.inHand = it.getProperty("user-inHand")
            user.index = it.getProperty("user-index")
            user.card1 = createSingleCard(it.getProperty("user-card1"))
            user.card2 = createSingleCard(it.getProperty("user-card2"))

            return user
        }
    }

    private PotState createPot(List<EmbeddedEntity> potEntity){
        PotState potState = new PotState()
        if(!potEntity)
            return potState

        potEntity.each { EmbeddedEntity ee ->
            PotCM pot = new PotCM()
            pot.pot = ee.getProperty("pot")
            pot.amountOwed = ee.getProperty("amountOwed")
            return pot
        }
    }

    private List<CardCM> createCards(List<EmbeddedEntity> cards){
        cards.collect { createEmbeddedEntityFromCard(it) }
    }

    private CardCM createSingleCard(EmbeddedEntity it) {
        CardCM cm = new CardCM()
        cm.value = it.getProperty("value")
        cm.suit = it.getProperty("suit")
        return cm
    }
}
