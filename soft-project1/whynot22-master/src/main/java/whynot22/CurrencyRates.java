package whynot22;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class CurrencyRates implements CurrencyRatesInterface  {

		public ExchangeRate exchangeRate = new ExchangeRate();

		// Drop down menu that launches upon initial running of program
		// Singapore Dollar
		// Japanese Yen
		// Chinese Yuan
		// US Dollar
		// South African Rand
		// Instruction of what users should do; max 3; Maybe suggest input form?
		// E.g. <amountCurrency> <amountCurrency> <amountCurrency>
		// Basically all print statements
		public boolean initialDisplay(){
			System.out.println("\n==========================================================");
			System.out.println("1. Australian Dollar (AUD)");
	    System.out.println("2. US Dollar (USD)");
	    System.out.println("3. Singapore Dollar (SGD)");
	    System.out.println("4. Euro (EUR)");
	    System.out.println("5. Japanese Yen (JPY)");
			System.out.println();
			return true;

    }


		// Store input values in an array
		// One line sumCUR sumCUR sumCUR
		// Example, 100.00AUD 10SGD 20CNY
		public ArrayList<String> takeInput(){
			//instructions given to user
			System.out.println("Please input up to 3 different currencies you could");
			System.out.println("like to convert from, from the following list of currencies");
			System.out.println("e.g. 1300SGD 2000JPY 500USD");
			System.out.println("-----------------------------------------------------------");
			//create array list to store user input
			ArrayList<String> convertFromList = new ArrayList<String>();
			//get user input
			Scanner sc = new Scanner(System.in);

			//put user input into an arraylist
			String line = sc.nextLine();
			String lineArray[] = line.split(" ");

			// transferring array values to ArrayList
			for (int i =0; i < lineArray.length; i ++) {
				convertFromList.add(lineArray[i]);
			}



			//check if user input is more than 4
			if(convertFromList.size() > 3){
				System.out.println("Too many inputs, please recheck input.");
				return null;
			}

				System.out.println("You inputed: ");
				for (int i =0; i < convertFromList.size(); i ++) {
						System.out.print(convertFromList.get(i) + " ");

				}
				System.out.println("\n");
				return convertFromList;
		}



		// Obtain amount and currency where x is amount and y is currency
		// (x,y) using Pair class
		//
		// input: ArrayList returned from takeInput()
		//
		// Example. 100AUD -> (100, AUD)
		//
		// Also use this function to check for valid inputs before creating a Pair object
		// If all inputs are valid then append each pair to a new arraylist, PairList
		// and return PairList
		// Else return and print input error
		public ArrayList<Pair> extractFunction(ArrayList<String> input){
			// checking input isnt null or no inputs provided
			if (input == null || input.size() == 0) {
				System.out.println("No input provided");
				return null;
			}

			// checking max 3 inputs provided
			if (input.size() > 3) {
				System.out.println("Too Many Inputs");
				System.out.println("Please provide maximum 3 inputs");
				return null;
			}

			// checking if any value in input array is null
			for (int i = 0; i < input.size(); i ++) {
				if (input.get(i) == null) {
					System.out.print("Null input provided\n Please try again!\n");
					return null;
				}

			}

			//WHITEBOX TESTING
			//System.out.println("array size: " + input.size());
			//System.out.println("array: " + input);

			//initialising output ArrayList
			ArrayList<Pair> output = new ArrayList<Pair>();

			//splitting string into value and specified exchange rate
			String current;
			String valueString = "invalid";
			String exchangeRate = "invalid";
			for (int i = 0; i < input.size(); i ++) {
				current = input.get(i);

				if (current.length() > 3){
					//extracting float value
					valueString = current.substring(0, current.length() - 3);

					//extracting exchange rate value
					exchangeRate = current.substring(current.length()-3);
				}
				// //extracting float value
				// valueString = current.substring(0, current.length() - 3);
				//
				// //extracting exchange rate value
				// exchangeRate = current.substring(current.length()-3);

				//WHITEBOX TESTING
				//System.out.println("ER: " + exchangeRate);

				if (checkValidExchangeRate(exchangeRate) == false) {
					System.out.println("Please use format YYYYYXXX , where Y is a digit or decimal and X is 3 letter exchange rate code\n");
					return null;
				}

				Double value;

				try {
					value = Double.parseDouble(valueString);
				} catch (Exception e) {
					System.out.println("Invalid value");
					System.out.println("Please use format YYYYYXXX , where Y is a digit or decimal and X is 3 letter exchange rate code\n");
					return null;
				}

				//WHITEBOX TESTING
				//System.out.println("VALUE: " + value);

				Pair tuple = new Pair(value, exchangeRate);
				output.add(tuple);

			}

			return output;
    }

		// used to check if a 3 letter exchange rate code is valid
		private Boolean checkValidExchangeRate(String er) {
			// accepted exchange rates
			String accepted[] = new String[]{"AUD","USD","SGD","EUR","JPY"};

			// if er is found in array check will be flagged
			Boolean check = false;
			for (int i = 0; i < accepted.length; i ++) {
				if (accepted[i].equals(er)) {
					check = true;
				}
			}
			return check;
		}


		// Loop through arrayList, and convert each currency to AUD and append each
		// result to an arrayList, arrayOfSums and return arrayOfSums
		// input: arraylist returned from extractFunction()
		public ArrayList<Double> convertToAUD(ArrayList<Pair> input){
				if (input == null) {
					System.out.println("Error: Null Input!");
					return null;
				}

				if (input.size() == 0 || input.size() > 3) {
					System.out.println("Error: Check number of inputs");
					return null;
				}


				// set output array
				ArrayList<Double> output = new ArrayList<Double>();

				// declare variables used in below for loop
				Pair current;
				double val;
				double conversion;

				// this for loop goes through each pair value converts it to AUD
				// and adds it to output array. This output array gets returned
				for (int i = 0; i < input.size(); i ++) {
					current = input.get(i);

					// getting the conversion value depending on corresponding exchange rate
					if (current.getER().equals("AUD")) {
						conversion = 1;
					} else if (current.getER().equals("USD")) {
						conversion = exchangeRate.USD;
					} else if (current.getER().equals("SGD")) {
						conversion = exchangeRate.SGD;
					} else if (current.getER().equals("EUR")) {
						conversion = exchangeRate.EUR;
					} else if (current.getER().equals("JPY")) {
						conversion = exchangeRate.JPY;
					} else {
						System.out.println("Invalid Currency! Try Again!");
						return null;
					}

					val = (double) current.getVal();
					val = (double) val/conversion;

					output.add(val);
				}

				return output;
    }


		// Loop through arrayOfSums and add each value
		// arrayOfSums: arraylist returned from convertToAUD()
		// Return finalSum
		public double finalSumAUD(ArrayList<Double> arrayOfSums){
			double sum = 0;
			for(int i = 0; i < arrayOfSums.size(); i++){
					sum += arrayOfSums.get(i);

			}
			 return sum;
    }


		// Display with avaiable currencies for user to convert to
		// Print a number with a corresponding currency
		public boolean secondDisplay(){

			System.out.println("===========================================================");
			System.out.println("Please Select Number Value Corresponding to");
			System.out.println("the Exchange Rate you would like to convert to");
			System.out.println("eg if you would like to convert to AUD please select 1");

			System.out.println("-----------------------------------------------------------");

			System.out.println("1. Australian Dollar (AUD)");
			System.out.println("2. US Dollar (USD)");
			System.out.println("3. Singapore Dollar (SGD)");
			System.out.println("4. Euro (EUR)");
			System.out.println("5. Japanese Yen (JPY)");

			System.out.println("-----------------------------------------------------------");
			return true;
    }


		// Convert sum returned from finalSumAUD() to desired currency
		// x: user would input a number that corresponds to a currency they would
		// like to convert to

		// return finalanswer
		public double AUDtoNewCurrency(int x, ExchangeRate rates, double sum){
			double finalAnswer = 0;

				// Valid to continue
				// {AUD, USD, SGD, EUR, JPY};
				// Search list for that currency rate
				if (x == 1){
					finalAnswer = (rates.AUD) * sum;
					finalAnswer = (double) Math.round(finalAnswer * 100) / 100;
					return finalAnswer;
				}else if (x == 2){
					finalAnswer = (rates.USD) * sum;
					finalAnswer = (double) Math.round(finalAnswer * 100) / 100;
					return finalAnswer;
				}else if (x == 3){
					finalAnswer = (rates.SGD) * sum;
					finalAnswer = (double) Math.round(finalAnswer * 100) / 100;
					return finalAnswer;
				}else if (x == 4){
					finalAnswer = (rates.EUR) * sum;
					finalAnswer = (double) Math.round(finalAnswer * 100) / 100;
					return finalAnswer;
				}else if (x == 5){
					finalAnswer = (rates.JPY) * sum;
					finalAnswer = (double) Math.round(finalAnswer * 100) / 100;
					return finalAnswer;
				}else {
					System.out.println("You entered: " + x + "\nThis is not supported! Please enter a number between 1 and 5.");
					return -1;
				}
    }

		//Converts a number to the corresponding currency
		public String desiredCurNumberConversion(int desiredCurrency){
				String desiredCur = "AUD";
				if (desiredCurrency == 2){
						desiredCur = "USD";
				} else if (desiredCurrency == 3){
						desiredCur = "SGD";
				} if (desiredCurrency == 4){
						desiredCur = "EUR";
				} if (desiredCurrency == 5){
						desiredCur = "JPY";
				}
				return desiredCur;
		}

		//Helper Function for main: Takes in userinput after conversion is done.
		//Returns 1 to break out of while loop
		public int runAgain(String userInput){
				if (userInput.equals("y") || userInput.equals("yes")) {
						System.out.println("You inputted: " + userInput);
						return 1;

				} else if (userInput.equals("n") || userInput.equals("no")){
					System.out.println("Thankyou! Come Again XD! PEACE!");
						return 1;
				} else {
					System.out.println("You inputted: " + userInput + ". Please input yes or no!");
					return 2;
				}
		}

}
