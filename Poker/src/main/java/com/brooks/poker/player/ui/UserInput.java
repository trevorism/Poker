/**
 * 
 */
package com.brooks.poker.player.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.brooks.poker.validator.BettingValidator;

/**
 * @author Trevor
 * 
 */
public class UserInput{

    private static final String FOLD_CALL_RAISE = "Do you want to fold(F), call(C), or raise(R)? ";
    private static final String CHECK_BET = "Do you want to check(C), or bet(B)? ";
    private static final String HOW_MUCH_BET = "How much do you want to bet? ";

    private static final String INPUT_NOT_VALID = "The input was not valid.";
    private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    public static ActionResult checkBet(){

        System.out.print(CHECK_BET);
        try{
            String option = stdin.readLine();
            return readInCheckBet(option);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return checkBet();
        }

    }

    private static ActionResult readInCheckBet(String option) throws Exception{
        if(option == null)
            throw new Exception(INPUT_NOT_VALID);
        
        if (option.toUpperCase().equals("C")){
            return ActionResult.CALL;
        }
        if (option.toUpperCase().equals("B")){
            return ActionResult.BET;
        }

        throw new Exception(INPUT_NOT_VALID);
    }

    public static ActionResult foldCallRaise(){

        System.out.print(FOLD_CALL_RAISE);
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        try{
            String option = stdin.readLine();
            return readInFoldCallRaise(option);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return foldCallRaise();
        }

    }

    private static ActionResult readInFoldCallRaise(String option) throws Exception{
        if(option == null)
            throw new Exception(INPUT_NOT_VALID);

        if (option.toUpperCase().equals("F")){
            return ActionResult.FOLD;
        }
        if (option.toUpperCase().equals("C")){
            return ActionResult.CALL;
        }
        if (option.toUpperCase().equals("R")){
            return ActionResult.BET;
        }

        throw new Exception(INPUT_NOT_VALID);
    }

    public static int betPrompt(BettingValidator validator){
        System.out.println(HOW_MUCH_BET);
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        try{
            String amount = stdin.readLine();
            return readInBetAmount(amount, validator);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return betPrompt(validator);
        }
    }

    private static int readInBetAmount(String amount, BettingValidator validator) throws Exception{
        if(amount == null)
            throw new Exception(INPUT_NOT_VALID);
        
        Integer value = Integer.parseInt(amount);

        if (!validator.validateBet(value))
            throw new Exception("Bet is smaller than minimum bet");

        return value.intValue();
    }

}
