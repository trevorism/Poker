package com.brooks.poker.evaluator.hand;

import com.brooks.poker.cards.HandValue.HandValueType;


/**
 * @author Trevor
 *
 */
public class Pair extends OfAKind {

	@Override
	protected int ofAKindCount() {		
		return 2;
	}

    @Override
    protected HandValueType handValueType(){
        
        return HandValueType.PAIR;
    }


}
