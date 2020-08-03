package whynot22;
import java.util.Scanner;
import java.lang.Thread;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class mainFunction {
    public static void start() {

      // initialise object
      CurrencyRates one = new CurrencyRates();

      while (true){
        // initial display
        one.initialDisplay();

        // take input
        ArrayList<String> userInput = one.takeInput();
        // convert outputed string by previous func to array of pairs
        ArrayList<Pair> userInputPair = one.extractFunction(userInput);

        while(true){
            if (userInputPair == null){
                userInput = one.takeInput();
                userInputPair = one.extractFunction(userInput);
            } else {
                break;
            }
        }

        // Convert pair values from different currencies to an array of only AUD
        ArrayList<Double> userInputAUD = one.convertToAUD(userInputPair);

        // Sum that value and output as a single double
        double AUDSum = one.finalSumAUD(userInputAUD);

        // asks for a desired currency to convert our AUDSum to
        one.secondDisplay();

        // this takes input of desired current and converts it to appropriate ER
        Scanner input = new Scanner(System.in);
        // System.out.println();

        int desiredCurrency = input.nextLine().charAt(0) - '0';

        //Continue to ask to desired currency input if the input does not exist in the list
        while (true){
            if (one.AUDtoNewCurrency(desiredCurrency, one.exchangeRate, AUDSum) == -1){
                desiredCurrency = input.nextLine().charAt(0) - '0';
            } else {
                break;
            }
        }

        double finalAnswer = one.AUDtoNewCurrency(desiredCurrency, one.exchangeRate, AUDSum);

        // output final value
        System.out.printf("The conversion to your desired currency is " + String.format("%.2f", finalAnswer) + one.desiredCurNumberConversion(desiredCurrency) + "\n" + "===========================================================\nWould you like to input more values input? (y/n)");

        // reading for input
        String option = input.next().toLowerCase();

        while (true){
            int ret_value = one.runAgain(option);
            if (ret_value == 1){
                break;
            } else {
              option = input.next().toLowerCase();
            }
        }

        if (option.equals("n") || option.equals("no") || option.equals("nope")){
            break;
        }

      }
    }

    public static void main(String[] args) {

      start();

    }


}
