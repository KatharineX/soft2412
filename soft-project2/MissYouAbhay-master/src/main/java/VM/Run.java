package VM;
import java.util.concurrent.*;
import java.lang.Runnable;


public class Run implements Runnable {
  VendingMachine VMachine = new VendingMachine();


  public void run() {
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
            double totalCost = VMachine.totalCost(VMachine.customerBasket);
            if (VMachine.paymentMethod().equals("cash")){
                double cashInput = VMachine.cashInput(totalCost);
                VMachine.giveChange(cashInput, totalCost);
                break;

            } else if  (VMachine.paymentMethod().equals("card") ){
                String userCurrency = VMachine.inputCurrencyChoice();
                VMachine.cardPayment(userCurrency, totalCost);
                break;
            }

        }
      }
  }
}
