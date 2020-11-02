package com.brooks.poker.evaluator.hand;

import com.brooks.poker.cards.HandValue.HandValueType;



/**
 * @author Trevor
 *
 */
public class FourOfAKind extends OfAKind {
	
	public int ofAKindCount() {		
		return 4;
	}

    @Override
    protected HandValueType handValueType(){
        
        return HandValueType.FOUR_OF_A_KIND;
    }


}
