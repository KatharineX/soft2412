package VM;

public class Pair {
    String item;
    int quantity = 0;

    public Pair(String item, int quantity) {
      this.item = item;
      this.quantity = quantity;
    }

    public String getItem() {
      return this.item;
    }

    public int getQuantity(){
      return this.quantity;
    }

}
