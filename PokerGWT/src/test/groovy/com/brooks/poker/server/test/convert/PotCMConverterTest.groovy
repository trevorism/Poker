package com.brooks.poker.server.test.convert

import org.junit.Test

import com.brooks.poker.client.model.PotCM;
import com.brooks.poker.client.model.PotState
import com.brooks.poker.game.data.pot.Pot
import com.brooks.poker.game.data.pot.Pots
import com.brooks.poker.server.convert.PotCMConverter

/**
 * @author Trevor
 *
 */
class PotCMConverterTest{
    
    @Test
    public void testConvert(){
        Pots pots = new Pots();
        pots.reset([]);
        
        PotCMConverter converter = new PotCMConverter();
        PotState potState = converter.convert(pots);
        
        assert potState
        assert potState.pots
        assert potState.pots.size() == 1
        
    }
    
    @Test
    public void testWithValue(){
        Pots pots = new Pots();

        pots.reset([]);
        PotCMConverter converter = new PotCMConverter();
        PotState potState = converter.convert(pots);
        
        assert potState
        assert potState.pots
        assert potState.pots.size() == 1
    }
}
