package VM;

import java.util.ArrayList;

public class Stock {

    ArrayList<ObjectCostStock> allTheStock = new ArrayList<ObjectCostStock>();
    ArrayList<ObjectCostStock> drinks = new ArrayList<ObjectCostStock>();
    ArrayList<ObjectCostStock> chocolates = new ArrayList<ObjectCostStock>();
    ArrayList<ObjectCostStock> chips = new ArrayList<ObjectCostStock>();
    ArrayList<ObjectCostStock> lollies = new ArrayList<ObjectCostStock>();

    // drinks
    ObjectCostStock water;
    ObjectCostStock softDrink;
    ObjectCostStock juice;
    //chocolates
    ObjectCostStock mnm;
    ObjectCostStock bounty;
    ObjectCostStock mars;
    ObjectCostStock snickers;
    //chips
    ObjectCostStock original;
    ObjectCostStock bbq;
    ObjectCostStock sweetChillies;
    //lollies
    ObjectCostStock sourWorms;
    ObjectCostStock jellyBeans;
    ObjectCostStock littleBears;
    ObjectCostStock partMix;

    //Initialise Stock and add to array
    public Stock(){
        // DRINKS
        water = new ObjectCostStock("water", 2.00, 10, "Waters");
        softDrink = new ObjectCostStock("softdrink", 2.50, 10, "Softdrinks");
        juice = new ObjectCostStock("juice", 2.70, 10, "Juices");
        drinks.add(water);
        drinks.add(softDrink);
        drinks.add(juice);

        // CHOCOLATES
        mnm = new ObjectCostStock("mnm", 2.70, 10, "M&Ms");
        bounty = new ObjectCostStock("bounty", 2.70, 10, "Bounty");
        mars = new ObjectCostStock("mars", 2.70, 10, "Mars Bars");
        snickers = new ObjectCostStock("snickers", 2.70, 10, "Snickers");
        chocolates.add(mnm);
        chocolates.add(bounty);
        chocolates.add(mars);
        chocolates.add(snickers);

        // CHIPS
        original = new ObjectCostStock("original", 2.70, 10, "Original Chips");
        bbq = new ObjectCostStock("bbqchips", 2.70, 10, "BBQ Chips");
        sweetChillies = new ObjectCostStock("sweetchilli", 2.70, 10, "Sweet Chilli Chips");
        chips.add(original);
        chips.add(bbq);
        chips.add(sweetChillies);

        // LOLLIES
        sourWorms = new ObjectCostStock("sourworms", 2.70, 10, "Sour Worms");
        jellyBeans = new ObjectCostStock("jellybeans", 2.70, 10, "Jelly Beans");
        littleBears = new ObjectCostStock("littlebears", 2.70, 10, "Little Bears");
        partMix = new ObjectCostStock("partymix", 2.70, 10, "Party Mix");
        lollies.add(sourWorms);
        lollies.add(jellyBeans);
        lollies.add(littleBears);
        lollies.add(partMix);

        //adding everything to all the stock
        allTheStock.add(water);
        allTheStock.add(softDrink);
        allTheStock.add(juice);

        allTheStock.add(mnm);
        allTheStock.add(bounty);
        allTheStock.add(mars);
        allTheStock.add(snickers);

        allTheStock.add(original);
        allTheStock.add(bbq);
        allTheStock.add(sweetChillies);

        allTheStock.add(sourWorms);
        allTheStock.add(jellyBeans);
        allTheStock.add(littleBears);
        allTheStock.add(partMix);
    }

    public ArrayList<ObjectCostStock> getAllStock(){
        return this.allTheStock;
    }

    public ArrayList<ObjectCostStock> getDrinks(){
        return this.drinks;
    }

    public ArrayList<ObjectCostStock> getChocolates(){
        return this.chocolates;
    }

    public ArrayList<ObjectCostStock> getChips(){
        return this.chips;
    }

    public ArrayList<ObjectCostStock> getLollies(){
        return this.lollies;
    }


    //TO BE IMPLEMENTED: print statments
    public void printDrinksStock(){
        System.out.println("---Drinks--------------------------------------------------------------");
        for (int i = 0; i < drinks.size(); i ++) {
          printStock(drinks.get(i));
        }
        System.out.println("");
    }

    public void printChocolatesStock(){
        System.out.println("---Chocolates----------------------------------------------------------");
        for (int i = 0; i < chocolates.size(); i ++) {
          printStock(chocolates.get(i));
        }
        System.out.println("");
    }

    public void printChipsStock(){
        System.out.println("---Chips---------------------------------------------------------------");
        for (int i = 0; i < chips.size(); i ++) {
          printStock(chips.get(i));
        }
        System.out.println("");
    }

    public void printLolliesStock(){
        System.out.println("---Lollies-------------------------------------------------------------");
        for (int i = 0; i < lollies.size(); i ++) {
          printStock(lollies.get(i));
        }
        System.out.println("");
    }

    public void printStock(ObjectCostStock product) {
      if (product.getStock() > 0) {
        System.out.println(product.getName() + " available at $"
        + product.getCost() + " each. Product code: \"" + product.getItem() + "\"");
      }
      else {
        System.out.println("XX Sorry we have no more "+ product.getName() + " available! ;( XX");
      }
    }

    public void printQuantity(){
        // System.out.println("--Stock On Hand----------------------------------------------------------------");

        for (int i = 0; i < allTheStock.size(); i ++) {
            System.out.println(allTheStock.get(i).getName() + " " + allTheStock.get(i).getStock());
        }

        System.out.println("");
        System.out.println("------------------------------------------------------------------");

    }
}
