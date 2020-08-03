package VM;

public class ObjectCostStock {
    // this is for the purpose of refrence when doing string.equals(...)
    String item;
    double cost;
    int stock;

    // this is the name for display purposes
    String name;

    public ObjectCostStock(String item, double cost, int stock, String name) {
      this.item = item;
      this.cost = cost;
      this.stock = stock;
      this.name = name;
    }

    public String getItem() {
      return this.item;
    }

    public double getCost(){
      return this.cost;
    }

    public int getStock(){
      return this.stock;
    }

    public String getName() {
      return this.name;
    }

    public int decreaseStock(int purchase){
      this.stock = this.stock - purchase;
      return this.stock;
    }

    public int increaseStock(int purchase){
      this.stock = this.stock + purchase;
      return this.stock;
    }

    public void purchaseStock(int quantity){
        if (this.stock - quantity < 0){
            System.out.println("OI WE DON'T HAVE ENOUGH STOCK");
            System.out.println("THIS AINT SUPPOSE TO PRINT");
            System.out.println("YOU DIDN'T IMPLEMENT A CHECK I TOLD YOU TO!");
        } else {
            this.stock -= quantity;
        }
    }

    public void refillStock(int quantity){
        if (this.stock + quantity > 10){
            System.out.println("LOL you exceeded");
            System.out.println("need to do something about dat");
        } else {
            this.stock += quantity;
        }
    }
}
