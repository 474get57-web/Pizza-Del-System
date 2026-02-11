import java.util.ArrayList;

// pizza class extends menuItem (inheritance)
class pizza extends menuItem {
    private ArrayList<String> toppings;
    private int size; // 1=small, 2=medium, 3=large

    pizza(String n, double p, int s) {
        super(n, p);
        size = s;
        toppings = new ArrayList<String>();
    }

    public void addTopping(String topping) {
        toppings.add(topping);
    }

    public ArrayList<String> getToppings() {
        return toppings;
    }

    public int getSize() {
        return size;
    }

    public String getSizeName() {
        switch(size) {
            case 1: return "Small";
            case 2: return "Medium";
            case 3: return "Large";
            default: return "Medium";
        }
    }
}