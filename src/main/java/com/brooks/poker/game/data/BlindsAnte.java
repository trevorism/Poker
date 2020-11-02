package com.brooks.poker.game.data;

/**
 * @author Trevor
 */
public final class BlindsAnte {

    public static final BlindsAnte NO_BLINDS_ANTE = new BlindsAnte(0, 0, 0);
    public static final BlindsAnte STANDARD_TOURNAMENT = new BlindsAnte(25, 10, 0);

    public final int bigBlind;
    public final int smallBlind;
    public final int ante;

    public BlindsAnte(int bigBlind, int smallBlind, int ante) {
        this.bigBlind = bigBlind;
        this.smallBlind = smallBlind;
        this.ante = ante;
    }


}
