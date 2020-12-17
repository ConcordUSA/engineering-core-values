package mobprogramming.solution;

public class ShippingLabel extends OrderReport {
    private String address;

    public ShippingLabel(String customer, int total, String address) {
        super(customer, total);
        this.address = address;
    }

    public String print(){
        var message = "Shipping Label: " + this.customer + " " + this.address;
        return message;
    }
}
