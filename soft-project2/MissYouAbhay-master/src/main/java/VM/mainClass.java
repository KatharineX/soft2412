package VM;
import java.util.concurrent.*;
import java.lang.Thread;
import java.lang.ProcessBuilder;

public class mainClass {


   public static void main(String[] args){

     // setting timeout for 90 second
     int timeout = 90;

     ExecutorService executor = Executors.newSingleThreadExecutor();
     Future<String> future = executor.submit(new Task());

     try {
       System.out.println("Started..");
       //Process p = Runtime.getRuntime().exec("VendingMachine.exe");
       future.get(timeout, TimeUnit.SECONDS);
       System.out.println("Finished!");
       //p.destroy();
     } catch (Exception h) {
       //p.destroy();
       future.cancel(true);
       System.out.println();
       System.out.println("----------------------------------------------------------------------+");
       System.out.println("                          Time has run out!                           |");
       System.out.println("  Please use ctrl + C and restart purchase if you are a new customer  |");
       System.out.println("                                 OR                                   |");
       System.out.println("              Keep continuing with your order, customer!              |");
       System.out.println();

       return;
     }
    }

}
