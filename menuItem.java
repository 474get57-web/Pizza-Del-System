// base class for all menu items
class menuItem {
    protected String name;
    protected double price;

    menuItem(String n, double p) {
        name = n;
        price = p;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}