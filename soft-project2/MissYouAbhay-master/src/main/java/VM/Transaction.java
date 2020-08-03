package VM;
import java.util.Random;
import java.util.Date;
import java.util.ArrayList;

// NEWTASK: need currency attribute for card
public class Transaction {

    int ID;
    Date dateTime;
    ArrayList<Pair> items;
    String paymentType;
    double totalCost;
    String currency;
    double cash;
    double change;
    Random rand = new Random();

    public Transaction(int ID, Date dateTime, ArrayList<Pair> items, String paymentType, double totalCost, String currency, double cash, double change){
        this.ID = ID;
        this.dateTime = dateTime;
        this.items = items;
        this.paymentType = paymentType;
        this.totalCost = totalCost;
        this.currency = currency;
        this.cash = cash;
        this.change = change;
    }

    public String getpaymentType(){
        return this.paymentType;
    }

    public void printCardTransaction(){
      int numID = rand.nextInt(1000);
      System.out.println("-----------------------------------------------------------------");
      System.out.println("Time of Purchase: " + dateTime.toString());
      System.out.println("Transaction ID: " + numID);
      System.out.println("Items: ");
      for (Pair order: items){
        System.out.println("  " + order.getQuantity() + "x " + order.getItem());
      }
      System.out.println("Payment Method: " + paymentType);
      System.out.println("Total Cost: " + totalCost + " in " + currency);
      System.out.println("Paid: " + totalCost + " " + currency);
      System.out.println("-----------------------------------------------------------------");


    }


    public void printCashTransaction(){
      int numID = rand.nextInt(1000);
      System.out.println("-----------------------------------------------------------------");
      System.out.println("Time of Purchase: " + dateTime.toString());
      System.out.println("Transaction ID: " + numID);
      System.out.println("Items: ");
      for (Pair order: items){
        System.out.println(order.getQuantity() + "x " + order.getItem());
      }
      System.out.println("Payment Method: " + paymentType);
      System.out.println("Total Cost $: " + totalCost);
      System.out.println("Paid: $" + cash);
      System.out.println("Returned: $" + change);
      System.out.println("-----------------------------------------------------------------");


    }

}
