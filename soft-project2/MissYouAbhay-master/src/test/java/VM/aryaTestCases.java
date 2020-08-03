package VM;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;
import java.util.ArrayList;

public class aryaTestCases {


  // ===============================
  // Currency Conversion Test Cases
  // ===============================

  @Test
  public void testCurrencyConversionAUD() {
    VendingMachine VMachine = new VendingMachine();
    String userCurrency = "AUD";
    double totalCost = 4;
    ExchangeRate er = new ExchangeRate();
    double conversion = er.AUD.getRate();

    double expected = conversion*totalCost;

    assertEquals(VMachine.currencyConversion(userCurrency, totalCost), expected, 0.001);

  }

  @Test
  public void testCurrencyConversionUSD() {
    VendingMachine VMachine = new VendingMachine();
    String userCurrency = "USD";
    double totalCost = 4;
    ExchangeRate er = new ExchangeRate();
    double conversion = er.USD.getRate();

    double expected = conversion*totalCost;

    assertEquals(VMachine.currencyConversion(userCurrency, totalCost), expected, 0.001);

  }

  @Test
  public void testCurrencyConversionSGD() {
    VendingMachine VMachine = new VendingMachine();
    String userCurrency = "SGD";
    double totalCost = 4;
    ExchangeRate er = new ExchangeRate();
    double conversion = er.SGD.getRate();

    double expected = conversion*totalCost;

    assertEquals(VMachine.currencyConversion(userCurrency, totalCost), expected, 0.001);

  }

  @Test
  public void testCurrencyConversionEUR() {
    VendingMachine VMachine = new VendingMachine();
    String userCurrency = "EUR";
    double totalCost = 4;
    ExchangeRate er = new ExchangeRate();
    double conversion = er.EUR.getRate();

    double expected = conversion*totalCost;

    assertEquals(VMachine.currencyConversion(userCurrency, totalCost), expected, 0.001);

  }

  @Test
  public void testCurrencyConversionJPY() {
    VendingMachine VMachine = new VendingMachine();
    String userCurrency = "JPY";
    double totalCost = 4;
    ExchangeRate er = new ExchangeRate();
    double conversion = er.JPY.getRate();

    double expected = conversion*totalCost;

    assertEquals(VMachine.currencyConversion(userCurrency, totalCost), expected, 0.001);

  }

  @Test
  public void testCurrencyConversionInvalid() {
    VendingMachine VMachine = new VendingMachine();
    String userCurrency = "XYZ";

    assertEquals(VMachine.currencyConversion(userCurrency, 4), -1, 0.001);

  }

  //================================
  // Admin Input Test Cases
  //================================

  @Test
  public void testAdminInputOne() {
    VendingMachine VMachine = new VendingMachine();
    String input = "1\n";

    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    VMachine.adminInput();

  }

  @Test
  public void testAdminInputTwo() {
    VendingMachine VMachine = new VendingMachine();
    String input = "2\n";

    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    VMachine.adminInput();

  }

  @Test
  public void testAdminInputThree() {
    VendingMachine VMachine = new VendingMachine();
    String input = "ak\n6\n3";

    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    VMachine.adminInput();

  }

  //================================
  // Input Currency Test Cases
  //================================

 @Test
 public void testInputCurrencyChoiceSimple1() {
   VendingMachine VMachine = new VendingMachine();

   String input = "XYZ\nUSD";
   InputStream in = new ByteArrayInputStream(input.getBytes());
   System.setIn(in);

   assertEquals(VMachine.inputCurrencyChoice(), "USD");
 }

 @Test
 public void testInputCurrencyChoiceSimple2() {
   VendingMachine VMachine = new VendingMachine();

   String input = "XYZ\nSGD";
   InputStream in = new ByteArrayInputStream(input.getBytes());
   System.setIn(in);

   assertEquals(VMachine.inputCurrencyChoice(), "SGD");
 }

 @Test
 public void testInputCurrencyChoiceSimple3() {
   VendingMachine VMachine = new VendingMachine();

   String input = "XYZ\nAUD";
   InputStream in = new ByteArrayInputStream(input.getBytes());
   System.setIn(in);

   assertEquals(VMachine.inputCurrencyChoice(), "AUD");
 }

 @Test
 public void testInputCurrencyChoiceSimple4() {
   VendingMachine VMachine = new VendingMachine();

   String input = "XYZ\nEUR";
   InputStream in = new ByteArrayInputStream(input.getBytes());
   System.setIn(in);

   assertEquals(VMachine.inputCurrencyChoice(), "EUR");
 }

 @Test
 public void testInputCurrencyChoiceSimple5() {
   VendingMachine VMachine = new VendingMachine();

   String input = "XYZ\nJPY";
   InputStream in = new ByteArrayInputStream(input.getBytes());
   System.setIn(in);

   assertEquals(VMachine.inputCurrencyChoice(), "JPY");
 }

 //================================
 // Purchase Stock Test Cases
 //================================
 @Test
 public void testPurchaseStockWater() {
   VendingMachine VMach = new VendingMachine();
   // setting up customer basket
   VMach.customerBasket.add(new Pair("water", 1));
   VMach.stocks.water.stock = 10;
   VMach.purchaseStock();

   assertEquals(VMach.stocks.water.stock, 9, 0.0001);

 }

 @Test
 public void testPurchaseStockPartyMix() {
   VendingMachine VMach = new VendingMachine();
   // setting up customer basket
   VMach.customerBasket.add(new Pair("partymix", 1));
   VMach.stocks.partMix.stock = 10;
   VMach.purchaseStock();

   assertEquals(VMach.stocks.partMix.stock, 9, 0.0001);

 }

 //================================
 // Check ValidCard Test Cases
 //================================

 @Test
 public void testCheckValidCardValid() {
   VendingMachine vmach = new VendingMachine();

   assertTrue(vmach.checkValidCard("12345678 0420"));
 }

 @Test
 public void testCheckValidCardInvalidExpiry() {
   VendingMachine vmach = new VendingMachine();

   assertFalse(vmach.checkValidCard("12345678 0419"));
 }

 @Test
 public void testCheckValidCardInvalidCardNumber() {
   VendingMachine vmach = new VendingMachine();

   assertFalse(vmach.checkValidCard("1234567 0420"));
 }

 @Test
 public void testCheckValidCardIntegerException() {
   VendingMachine vmach = new VendingMachine();

   assertFalse(vmach.checkValidCard("1234567 akha"));
 }

 //================================
 // Check cardPayment Test Cases
 //================================
 @Test
 public void testCardPaymentValid() {
   VendingMachine vmach = new VendingMachine();
   String input = "12345678 0420";
   InputStream in = new ByteArrayInputStream(input.getBytes());
   System.setIn(in);

   assertTrue(vmach.cardPayment("AUD", 2.70));

 }

 @Test
 public void testCardPaymentCancel() {
   VendingMachine vmach = new VendingMachine();
   String input = "123456\ncancel";
   InputStream in = new ByteArrayInputStream(input.getBytes());
   System.setIn(in);

   assertFalse(vmach.cardPayment("AUD", 2.70));
 }

 //================================
 //  AdminFunction Test Cases
 //================================

 @Test
 public void testAdminFunctionYes() {
   VendingMachine vmach = new VendingMachine();
   String input = "cancel\nyes";
   InputStream in = new ByteArrayInputStream(input.getBytes());
   System.setIn(in);

   assertEquals(vmach.adminFunction(), 1);
 }

 @Test
 public void testAdminFunctionNo() {
   VendingMachine vmach = new VendingMachine();
   String input = "no";
   InputStream in = new ByteArrayInputStream(input.getBytes());
   System.setIn(in);

   assertEquals(vmach.adminFunction(), 0);
 }

 //================================
 //  printBasket() and printTransaction Test Cases
 //================================

 @Test
 public void testPrintBasket() {
   VendingMachine vmach = new VendingMachine();
   vmach.customerBasket.add(new Pair("water", 1));
   vmach.printBasket();
 }

 @Test
 public void testPrintDailyTransactions() {
   VendingMachine vmach = new VendingMachine();
   vmach.customerBasket.add(new Pair("water", 1));
   vmach.dailyTransactions.add(
    new Transaction(0, vmach.date, vmach.customerBasket, "card", 2.70, "USD", 0, 0));
   vmach.dailyTransactions.add(
    new Transaction(1, vmach.date , vmach.customerBasket, "cash", 2.70, "n/a", 0, 0));
   vmach.printDailyTransactions();
 }

 @Test
 public void testPrintCancelledTransactions() {
   VendingMachine vmach = new VendingMachine();
   vmach.customerBasket.add(new Pair("water", 1));
   vmach.cancelledTransactions.add(
    new Transaction(0, vmach.date, vmach.customerBasket, "card", 2.70, "USD", 0, 0));
   vmach.cancelledTransactions.add(
    new Transaction(1, vmach.date , vmach.customerBasket, "cash", 2.70, "n/a", 0, 0));
   vmach.cancelledTransactions.add(
    new Transaction(1, vmach.date , vmach.customerBasket, "cash", 2.70, "n/a", 0, 0));
   vmach.cancelledTransactions.add(
    new Transaction(1, vmach.date , vmach.customerBasket, "cash", 2.70, "n/a", 0, 0));
   vmach.printCancelledTransactions();
 }

 //================================
 //  obtainInput() Test Cases
 //================================

 // @Test
 // public void testObtainInput() {
 //   VendingMachine vmach = new VendingMachine();
 //   String input = "waser 1\nwater\nwater 11\nwater 5";
 //   InputStream in = new ByteArrayInputStream(input.getBytes());
 //   System.setIn(in);
 //
 //   assertTrue(vmach.obtainInput());
 // }

 //================================
 //  totalCost() Test Cases
 //================================

 @Test
 public void testTotalCostSimple() {
   VendingMachine VMach = new VendingMachine();
   // setting up customer basket
   VMach.customerBasket.add(new Pair("water", 1));
   VMach.stocks.water.stock = 10;
   assertEquals(VMach.totalCost(VMach.customerBasket), VMach.stocks.water.cost, 0.001);
 }

 //====================================
 //  printDailyTransactions() Test Cases
 //=====================================
 @Test
 public void testprintDailyTransactions() {
   VendingMachine VMach = new VendingMachine();
   // setting up customer basket
   VMach.customerBasket.add(new Pair("water", 1));

   VMach.dailyTransactions.add(
      new Transaction(0, VMach.date, VMach.customerBasket, "card", 2.70, "USD", 0, 0));

   VMach.dailyTransactions.add(
      new Transaction(0, VMach.date, VMach.customerBasket, "cash", 2.70, "n/a", 0, 0));

   VMach.printDailyTransactions();
 }

 //====================================
 //  cancel() Test Cases
 //=====================================
 @Test
 public void testCancelFunctionEmptyBasket() {
   VendingMachine vmach = new VendingMachine();
   vmach.cancel();
 }

 @Test
 public void testCancelFunctionEmptySimple() {
   VendingMachine vmach = new VendingMachine();
   int quant = vmach.stocks.water.getStock();
   vmach.customerBasket.add(new Pair("water", 1));
   vmach.cancel();
   assertEquals(vmach.stocks.water.getStock(), quant + 1);
 }

 @Test
 public void testCancelFunctionEmptyComplex() {
   VendingMachine vmach = new VendingMachine();
   int quant = vmach.stocks.water.getStock();
   int quant2 = vmach.stocks.partMix.getStock();
   vmach.customerBasket.add(new Pair("water", 1));
   vmach.customerBasket.add(new Pair("partymix", 5));
   vmach.cancel();
   assertEquals(vmach.stocks.water.getStock(), quant + 1);
   assertEquals(vmach.stocks.partMix.getStock(), quant + 5);
 }

 //====================================
 //  canFill() Test Cases
 //=====================================
 @Test
 public void testCanFillOverFill() {
   VendingMachine vmach = new VendingMachine();
   Pair input = new Pair("water", 11);
   assertFalse(vmach.canFill(input));
 }

 @Test
 public void testCanFillGoodFill() {
   VendingMachine vmach = new VendingMachine();
   vmach.stocks.water.decreaseStock(3);
   Pair input = new Pair("water", 1);
   assertTrue(vmach.canFill(input));
 }

 //====================================
 //  fillStock() Test Cases
 //=====================================
 @Test
 public void testFillStock() {
   VendingMachine vmach = new VendingMachine();
   int quant = vmach.stocks.water.getStock();
   vmach.stocks.water.decreaseStock(3);

   String input = "waser 1\nwater 1\n";
   InputStream in = new ByteArrayInputStream(input.getBytes());
   System.setIn(in);

   vmach.fillStock();

   assertEquals(vmach.stocks.water.getStock(), quant-2);
 }


 //====================================
 //  fillMoreStock() Test Cases
 //=====================================

 @Test
 public void testFillMoreStockYes() {
   VendingMachine vmach = new VendingMachine();
   String input = "yes";
   InputStream in = new ByteArrayInputStream(input.getBytes());
   System.setIn(in);

   assertEquals(vmach.fillMoreStock(), 1);
 }

 @Test
 public void testFillMoreStockNo() {
   VendingMachine vmach = new VendingMachine();
   String input = "invalid\nno";
   InputStream in = new ByteArrayInputStream(input.getBytes());
   System.setIn(in);

   assertEquals(vmach.fillMoreStock(), 0);
 }


 //====================================
 //  ObtainInput() Test Cases
 //=====================================
 @Test
 public void testObtainInputSuccessful() {
   VendingMachine vmach = new VendingMachine();
   String input = "water 1";
   InputStream in = new ByteArrayInputStream(input.getBytes());
   System.setIn(in);
   assertTrue(vmach.obtainInput());
 }

 //====================================
 //  paymentMethod() Test Cases
 //=====================================
 @Test
 public void testPaymentMethodCancel() {
   VendingMachine vmach = new VendingMachine();
   String input = "cancel";
   InputStream in = new ByteArrayInputStream(input.getBytes());
   System.setIn(in);

   assertEquals(vmach.paymentMethod(), "none");
 }

 @Test
 public void testPaymentMethodCard() {
   VendingMachine vmach = new VendingMachine();
   String input = "card";
   InputStream in = new ByteArrayInputStream(input.getBytes());
   System.setIn(in);

   assertEquals(vmach.paymentMethod(), "card");
 }

 @Test
 public void testPaymentMethodCash() {
   VendingMachine vmach = new VendingMachine();
   String input = "invalid\ncash";
   InputStream in = new ByteArrayInputStream(input.getBytes());
   System.setIn(in);

   assertEquals(vmach.paymentMethod(), "cash");
 }





}
