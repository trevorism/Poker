package com.brooks.poker.client.push;

/**
 * @author Trevor
 * 
 */
public class UniqueIdGenerator{
    private static final char[] POSSIBLE_CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    public static String uuid(){
        return uuid(16, 16);
    }

    /**
     * Generate a random uuid of the specified length, and radix. Radix is the
     * number of possible characters, and should be less than POSSIBLE_CHARACTERS.length
     */
    private static String uuid(int length, int radix){
        if (radix > POSSIBLE_CHARACTERS.length){
            throw new IllegalArgumentException();
        }
        char[] uuid = new char[length];

        for (int i = 0; i < length; i++){
            uuid[i] = POSSIBLE_CHARACTERS[(int) (Math.random() * radix)];
        }
        return new String(uuid);
    }

}
