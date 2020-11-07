/**
 * 
 */
package com.brooks.poker.evaluator.hand;

import com.brooks.poker.cards.HandValue.HandValueType;


/**
 * @author Trevor
 *
 */
public class HighCard extends OfAKind {

    @Override
    protected int ofAKindCount(){
        return 1;
    }

    @Override
    protected HandValueType handValueType(){
        return HandValueType.HIGH_CARD;
    }


}
