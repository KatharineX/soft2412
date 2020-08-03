package VM;
import java.util.concurrent.Callable;
import java.lang.Thread;


public class Task implements Callable {
  VendingMachine VMachine = new VendingMachine();

  public String call() {

      while(true){
            VMachine.display();
            VMachine.newCustomer();
            while (true){

                VMachine.obtainInput();

                if ( VMachine.moreInput() == 0){
                    break;
                }
                VMachine.display2();
            }


            while (true){
                VMachine.printBasket();
                double totalCost = VMachine.totalCost(VMachine.customerBasket);
                String ret = VMachine.paymentMethod();
                if (ret.equals("cash")){
                    double cashInput = VMachine.cashInput(totalCost);
                    VMachine.giveChange(cashInput, totalCost);
                    break;

                } else if  (ret.equals("card") ){
                    String userCurrency = VMachine.inputCurrencyChoice();
                    VMachine.cardPayment(userCurrency, totalCost);
                    break;
                }
          }
          // sleep for 5 seconds before new customer
          try {
            Thread.sleep(500);
          } catch (InterruptedException e) {}
        }


    // return "Done!";
  }
}
