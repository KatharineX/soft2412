package VM;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.lang.Thread;
import java.lang.Math;
import java.util.Arrays;
import java.io.*;

//SPRINT3TODO: if there is n

public class VendingMachine {
    //List of daily transactions
    Date date = new Date();
    int TransactionID = 0;

    //Question: does daily transaction include cancelledTransactions
    ArrayList<Transaction> dailyTransactions = new ArrayList<Transaction>();

    /*    Not sure what cancelledTransactions should display   */
    ArrayList<Transaction> cancelledTransactions = new ArrayList<Transaction>();
    Stock stocks = new Stock();

  	//Used to store all the items one customer would want
  	ArrayList<Pair> customerBasket = new ArrayList<Pair>();

    ExchangeRate currency = new ExchangeRate();

  	public void newCustomer(){
  			customerBasket = new ArrayList<Pair>();
  	}

    //TO BE IMPLEMENTED : Give better printout statement
    /*    Might also want to display the cost of each item(s)   */
    public void display(){
        System.out.println("Hello!! \nWe have a variety of Drinks, Chocolates, Chips and Lollies\nPlease find options below!\n");
        // try {
        //   Thread.sleep(3500);
        // } catch (InterruptedException e) {}

        stocks.printDrinksStock();
        //
        // try {
        //   Thread.sleep(2000);
        // } catch (InterruptedException e) {}

        stocks.printChocolatesStock();

        // try {
        //   Thread.sleep(2000);
        // } catch (InterruptedException e) {}

        stocks.printChipsStock();

        // try {
        //   Thread.sleep(2000);
        // } catch (InterruptedException e) {}

        stocks.printLolliesStock();

        System.out.println("What would you like to purchase?");
        System.out.println("Please enter in this form: <itemNum> <quantity>");

    }

    public void display2(){
        System.out.println("What else would you like to purchase?");
        System.out.println("Please enter in this form: <itemNum> <quantity>");
    }

    //Helper Function for checking we have enough stock if customer were to make another entry of the same item
    //Returns the number of quantity in the basket of an item
    /*public int checkBasket(String item){
        //if item not in basket, return 0
        // else add up all the quanity if tehre is muliple entries
        Pair product = null;
        for (int i = 0; i < customerBasket.size(); i ++) {
          if (customerBasket.get(i).getItem().equals(item)) {
            product = customerBasket.get(i);
            break;
          }
        }

        if (product == null) {
          return 0;
        }

        return product.getQuantity();
    }*/


    //TO BE IMPLEMENTED
    // Helper Function: Put input in form in Pair object <item, quantity>
    // Also, store item toLowerCase().
    // checks if the user input matches the form ItemName Quantity, if not return null

    //AdminCode is 1111; Check for this first. If it is the admin code, store it as pair String 1111, quantity 1.

    //TODONEW: need to check that itemQuantity[0] is actually a string and not a
    // number if in the case the user inputs in the wrong format. If wrong, print
    // Please enter in this form: <itemNum> <quantity> return null

    //TODONEW: Also need to account for if the item input is e.g. sour worms 3
    // Here the user input as a length 3. Suggestion of split everything by space,
    //obtain the last element as the numeral and join the first 2 element together
    public Pair inputPairForm(String userInput){
      String input = userInput.toLowerCase();
      String item = null;
      int quantity = 0;
      //if admin code is given, then store pair as String 1111, quantity 1
      if (input.equals("1111")){
          item = input;
          quantity = 1;
      } else{
        //otherwise, separate the item and quantity into a list
        //if the list is greater than 2, then merge the first and second element together
          String[] itemQuantity = input.split(" ");
          //check if the itemQuantity array is in the form <quantity> <itemNum>
          try{
            quantity = Integer.parseInt(itemQuantity[itemQuantity.length - 1]);
          } catch (NumberFormatException e){
            System.out.println("Please enter in the form: <item> <quantity>");
            return null;
          }
          //get the last element of the array (this will always be the quantity)
          quantity = Integer.parseInt(itemQuantity[itemQuantity.length - 1]);
          if (itemQuantity.length == 2){
            item = itemQuantity[0];
            quantity = Integer.parseInt(itemQuantity[1]);
          } else if (itemQuantity.length > 2){
            //make quantity be the last element of the array
            quantity = Integer.parseInt(itemQuantity[itemQuantity.length - 1]);
            ArrayList<String> itemArray = new ArrayList<String>(Arrays.asList(itemQuantity));
            //remove quantity from the list so we end up with an arraylist of item names
            itemArray.remove(itemQuantity.length - 1);
            //concatenate the item names in the list
            String str = itemArray.toString();
            item = str.replaceAll("[\\[\\]\\, ]", "");
          }
      }
      Pair pairForm = new Pair(item, quantity);
      return pairForm;
    }

    //CHANGE RETURN STATEMENT TO ACCOUNT IF NO STOCK
    //TO BE IMPLEMENTED
    // check if it is admin code 1111.
    //checks if the ItemName is actually part of the list of items on sale in the vending machine
    //check if we have in stock the number of quanity
    //     - also checkBasket() //TO DO: NEW FOR AYRA
    //This funcion would take in the output from inputPairForm

    // return 0 if input is good
    // return 1 if items does not exist
    // return 2 if theres not enough stock
    // return 3 if for any other problems
    // return 4 if adminCode

    public int checkInput(Pair input){
      boolean item = true;
      boolean quantity = false;
      if (input == null){
        return 3;
      }

      String itemName = input.getItem();
      int itemIndex = findStockIndex(itemName);
      //admin function
      //if not admin then,
      //else check if the current stock - quantity requested is greater than 0.
      //if it is greater than or equal to 0 then actually decrease stock.

      if (input.getItem().equals("1111")){
        return 0;
      }
      if (itemIndex == -1 ){
        return 1;
      }
      //if requested quantity - stock available is greater than 0, then decrease the quantity requested with stock available
      //and update the stock amount
      else if (stocks.getAllStock().get(itemIndex).getStock() - input.getQuantity() >= 0){
        stocks.getAllStock().get(itemIndex).decreaseStock(input.getQuantity());
        quantity = true;
      }
      // return 0 if input is good
      if (item == true && quantity == true){
        return 0;
      // return 2 if theres not enough stock
      } else if (item == true && quantity == false){
        return 2;
      }
      // return 3 if for any other problems
      return 3;
    }


    public boolean obtainInput(){
      Scanner scanner = new Scanner(System.in);

      while(true){

        String input = scanner.nextLine();
        Pair customerPurchase = inputPairForm(input);
        //If the given input is good, it adds the item to the customerBasket
        if (checkInput(customerPurchase) == 0){
          //given input is good, but it might be the admin function.
            if (customerPurchase.getItem().equals("1111")){
                while (true){
                    int ret = adminInput();
                    if  (ret == 0){
                        display();
                        break;
                    }
                    ret = adminFunction();
                    if  (ret == 0){
                        display();
                        break;
                    }
                }
            }
            else {
              //if it's not the admin function, then just add it to the customer basket
              customerBasket.add(customerPurchase);
              return true;
            }

        } else if (checkInput(customerPurchase) == 1){
          //item is incorrect
            System.out.println("Please check the item requested and try again.");

        } else if (checkInput(customerPurchase) == 2){
          String itemName = customerPurchase.getItem();
          int totalStockIndex = findStockIndex(itemName);
          System.out.println("Unfortunately we currently only have " + stocks.getAllStock().get(totalStockIndex).getStock() + " units of " + itemName + ", please try again");
          //quantity requested is too big
          // System.out.println("Unfortunately we've run out of stock, please try again.");
        }else {
            System.out.println("Error! Please check your order and try again.");
        }

    }
  }

  public int findStockIndex(String itemName){
    for(int i = 0; i < stocks.getAllStock().size() ; i++){
      if (stocks.getAllStock().get(i).getItem().equals(itemName)){
        return i;
      }
    }
    //if stock does not exist
    return -1;
  }

    // For user want to input more snacks
	  // This function would be in a loop in the mainFunction
    // Returning 0 indicates to break out of the loop this function would be in
    // (i.e in the mainFunction);
	  public int moreInput(){
		    System.out.println("Would you like buy anything else?");

        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine();
            if (input.equals("yes")){
                return 1;
            } else if (input.equals("no")) {
                return 0;
            } else {
                System.out.println("Invalid input, please enter yes or no");
            }
        }
	  }

    /* KATHARINE */
    /* This function will iterate through the arraylist stocks,
    and find the corresponding pair.item and then return the cost */
    public double getPrice(Pair item){
        /*double cost;
        //iterate through stocks.getAllStock() and find the corresponding pair.item and return the cost
        return cost;*/
        double cost = 0;
        try{

          String name = item.getItem();

          // Iterates through the stocks Arraylist
          for(int i=0; i< stocks.getAllStock().size(); i++){
            // if item is found then we get the amount and cost
            if(name.equals(stocks.getAllStock().get(i).getItem())){
              //System.out.println("triggered");
              String purchasedItem = item.getItem();
              cost = stocks.getAllStock().get(i).getCost();
              // cost will be updated and return correct amount
              return cost;
            }else{


            }

          }
        }catch(Exception e){

        }

        // when item is not found, the cost will be 0
        return cost;
    }


    /* To find the totalCost, we iterate through all the pair.items in the customerBasket
    and add the prices together */
    public double totalCost(ArrayList<Pair> customerBasket){
        double totalCost = 0;

        // We want to find the items in the basket and the related price
        for(int i=0; i<customerBasket.size(); i++){
          // use getPrice() function to find the cost of each pair
          Pair purchase = customerBasket.get(i);
          String item = purchase.getItem();
          int amount = purchase.getQuantity();

          totalCost += (getPrice(purchase) * amount);
        }

        return totalCost;
    }


    //TO BE IMPLEMENTED
    public boolean paymentMethodCheck(String input){
      if (input.equals("card") || input.equals("cash")){
        return true;
      }
        return false;
    }

    //TODONEW: better print out statemnts
    //practically done
    public String paymentMethod(){
        System.out.println("Would you like to pay via cash or card? If you would like to cancel, please enter cancel." );
        Scanner scanner = new Scanner(System.in);
        while(true){
            String userMethod = scanner.nextLine();
            String method = userMethod.toLowerCase();
            if (method.equals("cancel")){
              cancel();
              return "none";
            } else if(paymentMethodCheck(method) == true){
                return method;
            } else {
                System.out.println("Invalid payment method, please input either 'cash' or 'card'");
            }
        }
    }

    /* Checks if you can convert string to Double, return -1 if can't,
    return -2 if user input is "cancel" */
    public double stringToDouble(String input){
        try{
          if(input != null){
            // clean the input to have no whitespace
            //input.replaceAll(" ","").toLowerCase();

            if(input.equals("cancel")){
              return -2;
            }else{
              double newDouble = 0;
              newDouble = Double.parseDouble(input);
              return newDouble;
            }
          }else{
            System.out.println("Null - not accepted");
          }
        }catch(Exception e){
          System.out.println(e);
        }
        return -1;
    }

    //Check if cash input are valid amounts. Can only be 5,10,20,50 cents or
    // 1 2 5 10 20 50 100 dollars.
    //This function would take in the output of stringToDouble()
    // if input == -1, if so return 0, this indicates, input is bad
    // if input == -2, if so return 1, this indicates string is cancel
    public int checkCashInput(double input){
        if (input == -1) {return 0;}

        if (input == -2) {return 1;}

        double[] acceptedVals = {0.5, 0.1, 0.2, 0.5, 1, 2, 5, 10, 20, 50, 100};

        boolean check = false;
        for (int i = 0; i < acceptedVals.length; i ++) {
          if (input == acceptedVals[i]) {
            return 0;
          }
        }
        return -1;
        //return -1 if input is invalid
    }

    public double cashInput(double totalCost){
        double remainingCost = totalCost;
        double userCash = 0;
        remainingCost = Math.round(remainingCost * 100.0) / 100.0;
        System.out.println("The total cost is: $" + totalCost);
        System.out.println("Please enter your moneys like the following");
        System.out.println("Enter 1.00 for $1, enter 0.05 for 5c...etc");
        System.out.println("Or if you want to cancel transaction, type cancel");

        Scanner scanner = new Scanner(System.in);
        while (remainingCost > 0){
            String input = scanner.nextLine();
            if (checkCashInput(stringToDouble(input)) == 0){
                remainingCost -= stringToDouble(input);
                userCash += stringToDouble(input);
                userCash = Math.round(userCash * 100.0) / 100.0;

                if (remainingCost < 0){
                  remainingCost = 0;
                }

                System.out.println("You still owe: " + remainingCost);
            } else if (checkCashInput(stringToDouble(input)) == 1){
                  //this is a cancelled transaction
                  Transaction cancelled = new Transaction(TransactionID, date, customerBasket, "cash", totalCost, "n/a", userCash, userCash);
                  cancelledTransactions.add(cancelled);
                  cancel();
                  break;
            } else {
                System.out.println("You inputted: " + input + " This is invalid.");
                System.out.println("Please Enter 1.00 for $1, enter 0.05 for 5c...etc");
                System.out.println("Or if you want to cancel transaction, type cancel");

            }

        }
        return userCash;

    }

    // will purchase all stock in customerBasket by looping through stock and deducting relevant items
    // 0 is failed purchase, 1 is successful
    public int purchaseStock() {
      for (int i = 0; i < customerBasket.size(); i ++) {
        String itemName = customerBasket.get(i).getItem();
        ArrayList<ObjectCostStock> inventory = stocks.getAllStock();
        ObjectCostStock item = null;
        for (int j = 0; j < inventory.size(); j ++) {
          // looking to find our item in inventory
          // and break out of inside loop once we have;
          //System.out.println("Inventory item: " + inventory.get(j).getItem());
          //System.out.print(" Compared against: ");
          //System.out.println(j +" "+ itemName);

          if (inventory.get(j).getItem().equals(itemName)) {
            item = inventory.get(j);
            //System.out.println(j + " Trigger: " + itemName);
            item.stock -= customerBasket.get(i).getQuantity();
            break;
          }
        }
      }
      return 1;
    }

    //TO BE IMPLEMENTED: dedection of stock on successful transactions
    // return -1 if transaction is cancelled
    // otherwise return change given
    public double giveChange(double userCash, double totalCost){
        if (userCash < totalCost){
            System.out.println("You have cancelled the transaction");
            System.out.println("Returning your money");
            System.out.println("Returned $" + userCash);
            return -1;

        } else {
            double change = userCash - totalCost;
            change = Math.round(change * 100.0) / 100.0;

            // Add this successful transaction to the daily transactions
            Transaction newTransaction = new Transaction(TransactionID, date, customerBasket, "cash", totalCost, "n/a", userCash, change);
            dailyTransactions.add(newTransaction);
            TransactionID ++; // for next transaction

            // TODONEW: Do deductions of stock here
            // Loop through customerBasket find corresponding object in stocks.getAllStock()
            // and purchaseStock(customerBasket.get(i).getQuantity()) on the items in stocks.getAllStock()

            //purchaseStock();

            System.out.println("The total cost is: " + totalCost);
            System.out.println("You have given: " + userCash);
            System.out.println("Here is $" + change + " in change.");
            return change;
        }

    }

    public void printBasket() {
      System.out.println("---------------------\n Your Basket");
      for (int i = 0; i < customerBasket.size(); i ++) {
        System.out.println("  - " + customerBasket.get(i).getItem() + " x" + customerBasket.get(i).getQuantity());
      }
      System.out.println("---------------------");
    }

    //KATHARINE //
    //TODONEW: better print out statements + IMPLEMENT BELOW
    public String inputCurrencyChoice(){

        //there's a global variable currency.
        Scanner scanner = new Scanner(System.in);
        try{
        while(true){
            System.out.println("What currency do you want to pay in?");
            System.out.println("Please note that paying with a different currency will have a 2% fee.");
            System.out.println("We offer the following currencies: AUD, USD, SGD, EUR, JPY");
            //view more currencies in exchangeRates class -- print these out as well
            System.out.println( "Please enter which currency you would like to pay in.");
            System.out.println( "AUD if you want to use Australian dollars");
            System.out.println( "USD if you want to use US dollars");
            System.out.println( "SGD if you want to use Singaporean dollars");
            System.out.println( "EUR if you want to use Euros");
            System.out.println( "JPY if you want to use Japanese dollars");
            String input = scanner.nextLine();
            //if user chooses an currecy we offer in correct form, then return input
            // check against currency.getCurrencies() which is an array list so iterate through it
            if(input.equals("AUD") || input.equals("USD")||input.equals("SGD")||input.equals("EUR")||input.equals("JPY")){
              System.out.println(input);
              return input;
            }else if (input == null){
              return "It Null";

            }else{
              // else, ask user to input something again
              System.out.println("Invalid Currency!");
            }
        }}

      catch(Exception e){

      }
        return "Not right";
    }

    // KATHARINE //
    public boolean checkValidCard(String input){
      //check if number is 8 digits (first element), else prints invalid number , need to be 8 digits, return false
      try{
        //split by space. make sure this produced 2 elements
        String[] numbers = input.split(" ");
        String ccNumber = numbers[0];
        String date = numbers[1];
        int ccLength = ccNumber.length();
        int year = Integer.parseInt(date.substring(2));

        if (year < 20) {
          System.out.println("Card expired");
          return false;
        }

        if (ccNumber.length() != 8) {
          return false;
        }

      }catch(Exception E){
        return false;
      }
      return true;
    }

    public boolean cardPayment(String userCurrency, double totalCost){
      double finalAmount = transactionFee(userCurrency, totalCost);

      System.out.println("The total cost in " + userCurrency + " is " + finalAmount);

      System.out.println("Please enter your card details in this form <cardno> <expirydate>");
      System.out.println("you card number should be 8 digits e.g. 12341234");
      System.out.println("expirydate in the form MMYY e.g. 0520");
      System.out.println("if you would like to cancel, please input cancel");


      Scanner scanner = new Scanner(System.in);
      while(true){
          String input = scanner.nextLine();
          if (checkValidCard(input) == true) {
              // NEWTASK: final amount would be in the deisred currency
              Transaction newTransaction = new Transaction(TransactionID, date, customerBasket, "card", finalAmount, userCurrency, 0, 0);
              dailyTransactions.add(newTransaction);
              TransactionID ++; // for next transaction

              System.out.println("BEEP Success! Thanks");

              // TODONEW: Do deductions of stock here
              // deduction of stock handled when input is taken
              //purchaseStock();
              return true;
              // Loop through customerBasket find corresponding object in stocks.getAllStock()
              // and purchaseStock(customerBasket.get(i).getQuantity()) on the items in stocks.getAllStock()
          } else if (input.toLowerCase().equals("cancel")){
              Transaction cancelled = new Transaction(TransactionID, date, customerBasket, "card", finalAmount, userCurrency, 0, 0);
              cancelledTransactions.add(cancelled);
              cancel();
              return false;
          } else {
              System.out.println("Please re-enter your card details");
              System.out.println("Use a 8 digit code and MMYY for expiry");
              System.out.println("ie FORMAT: <CARD NUMBER> <MMYY>");

          }

      }

    }

    //Helper function for transactionFee
    public double currencyConversion(String userCurrency, double totalCost){
      //there's a global variable currency.
      //obtain the rate of userCurrency via currency.getCurrencies() which is an array list so iterate through it
      // and then getCurrency.
      CurrencyPair chosenCurrency;
      if (userCurrency.equals("AUD")) {
        chosenCurrency = currency.AUD;
      } else if (userCurrency.equals("USD")) {
        chosenCurrency = currency.USD;
      } else if (userCurrency.equals("SGD")) {
        chosenCurrency = currency.SGD;
      } else if (userCurrency.equals("EUR")) {
        chosenCurrency = currency.EUR;
      } else if (userCurrency.equals("JPY")) {
        chosenCurrency = currency.JPY;
      } else {
        System.out.println("Invalid currency selected");
        return -1;
      }
      //then do the conversion on totalCost
      //return now the new totalCost in the userCurrency form
      return totalCost*chosenCurrency.getRate();
    }

    // KATHARINE //
    public double transactionFee(String userCurrency, double totalCost){
        double convertedAmount = currencyConversion(userCurrency, totalCost);
        // converted amount is cost -- multiply by 1.02 to get the total + extra fee
        double finalAmount = convertedAmount * 1.02;
        double roundOff = Math.round(finalAmount * 100.0) / 100.0;
        //apply 2% free on converted amount and return the final amount
        //System.out.println("The final cost in " + userCurrency + "is " + finalAmount);
        return roundOff;
    }

    public void adminDisplay(){
        System.out.println("What would you like to do?");
        System.out.println("Press 1 to print daily transactions");
        System.out.println("Press 2 to print cancelled transactions");
        System.out.println("Press 3 to print available stock");
        System.out.println("Press 4 to fill up stock");
        System.out.println("Enter cancel to cancel");

    }


    public int adminInput(){
      adminDisplay();

      Scanner scanner = new Scanner(System.in);
      while (true){
          String input = scanner.nextLine();
          if (input.equals("cancel")) {
              return 0;
          }
          try {
            int number = Integer.parseInt(input);
            if (number == 1){
                printDailyTransactions();
                break;
            } else if (number == 2){
                printCancelledTransactions();
                break;
            } else if (number == 3){
                printStock();
                break;
            } else if (number == 4){
                while (true){
                    fillStock();
                    int ret = fillMoreStock();
                    if (ret == 0){
                        adminDisplay();
                        break;
                    }
                }
            } else {
                System.out.println("invalid input");
            }
          } catch(Exception e) {
            System.out.println("invalid input");
          }
      }

      return 1;

    }


    public int adminFunction(){
        System.out.println("Would you like to do anything else?");


        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine();
            if (input.equals("yes")){
                return 1;
            } else if (input.equals("no")) {
                return 0;
            } else {
                System.out.println("Invalid input, please enter yes or no");
            }
        }



    }

    public void printStock(){
        System.out.println("Available Stock in the Vending Machine:");
        stocks.printQuantity();

    }

    //looop through dailyTransactions and call printTransaction() in Transaction class
    public void printDailyTransactions(){
      if (dailyTransactions.size() == 0){
          System.out.println("No transactions today");
      }

      for(int i = 0; i < dailyTransactions.size(); i++){
        //iterate through each transaction object, if obj.getpaymentType == cash
        if(dailyTransactions.get(i).getpaymentType().equals("cash")){
          dailyTransactions.get(i).printCashTransaction();
        }else{
          // printCashTransaction else printCardTransaction
          dailyTransactions.get(i).printCardTransaction();
        }
      }
    }


    //looop through cancelledTransactions and call printTransaction() in Transaction class
    public void printCancelledTransactions(){
        if (cancelledTransactions.size() == 0){
            System.out.println("No cancelled transactions today");
        }
        for(int i = 0; i < cancelledTransactions.size(); i++){
          //iterate through each transaction object, if obj.getpaymentType == cash
          if(cancelledTransactions.get(i).getpaymentType().equals("cash")){
            cancelledTransactions.get(i).printCashTransaction();
          }else{
            // printCashTransaction else printCardTransaction
            cancelledTransactions.get(i).printCardTransaction();
          }
        }
    }

    //Check if the amount admin wants to refill and exisiting SOH does not exceed 10
    public boolean canFill(Pair item){
        boolean check = false;
        ArrayList<ObjectCostStock> vmStock = stocks.getAllStock();

        for (int i = 0; i < stocks.getAllStock().size(); i++){
            if (vmStock.get(i).getItem().equals(item.getItem())) {
                if (item.getQuantity() + vmStock.get(i).getStock() <= 10){
                    vmStock.get(i).refillStock(item.getQuantity());
                    System.out.println("You refilled " + item.getQuantity() + " of " + item.getItem());
                    System.out.println("New stock amount of " + item.getItem() + " is " + vmStock.get(i).getStock());

                    check = true;
                } else {
                    int remainingSpace = 10 - vmStock.get(i).getStock();
                    System.out.println("There's currently " + vmStock.get(i).getStock() + " units of " + item.getItem());
                    System.out.println("Please refill up to " + remainingSpace + " units.");

                }
            }
        }
        return check;

    }

    public void fillStock(){
        System.out.println("What would you like to fill?");
        System.out.println("Please input in the form <item> <quantity>");


        Scanner scanner = new Scanner(System.in);

        while (true){
          String input = scanner.nextLine();
          Pair toFillPair = inputPairForm(input);
          boolean itemExist = false;


          for (int i = 0; i < stocks.getAllStock().size(); i++){
              if (stocks.getAllStock().get(i).getItem().equals(toFillPair.getItem())){
                itemExist = true;
              }
          }

          if (itemExist == true){
              if (canFill(toFillPair) == true){
                  break;
              }
          }

          else {
              System.out.println( toFillPair.getItem() + " does not exist, please enter again");

          }
        }
    }

    public int fillMoreStock(){
        System.out.println("Would you like to refill anything else?");

        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine();
            if (input.equals("yes")){
                return 1;
            } else if (input.equals("no")) {
                return 0;
            } else {
                System.out.println("Invalid input, please enter yes or no");
            }
        }
    }


    // call this when a transaction is cancelled
    // iterates through basket and returns all stock to inventory
    public void cancel() {
      //ArrayList<Pair> customerBasket = new ArrayList<Pair>();
      if (this.customerBasket.size() == 0) {
        return;
      }

      Pair product;
      String name;
      for (int i = 0; i < customerBasket.size(); i ++ ) {
        product = customerBasket.get(i);
        name = product.getItem();
        //Stock stocks = new Stock();
        ArrayList<ObjectCostStock> inventory = stocks.getAllStock();
        for (int j = 0; j < inventory.size(); i ++) {
          if (name.equals(inventory.get(i).getItem())) {
            inventory.get(i).increaseStock(product.getQuantity());
            break;
          }
        }
      }
    }


}
