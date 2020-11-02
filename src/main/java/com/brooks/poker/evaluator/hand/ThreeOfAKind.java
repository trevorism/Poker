package com.brooks.poker.evaluator.hand;

import com.brooks.poker.cards.HandValue.HandValueType;


/**
 * @author Trevor
 *
 */
public class ThreeOfAKind extends OfAKind {

	@Override
	protected int ofAKindCount() {		
		return 3;
	}
	
    @Override
    protected HandValueType handValueType(){
        
        return HandValueType.THREE_OF_A_KIND;
    }


}
