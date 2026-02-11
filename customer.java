// customer class to store customer information
class customer {
    private String name;
    private String phone;
    private String address;

    // constructor to initialize customer data
    customer(String n, String p, String a) {
        name = n;
        phone = p;
        address = a;
    }

    // getter methods to access private data
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}