/**
 * Open/Closed Principle
 *
 * Software elements (classes, modules, functions, etc...)
 * should be open for extension, but closed for modification
 */

/**
 * Scenario: You have an order report class that prints an invoice and shipping label
 * Task: Update the shippingLabel method to include the address
 * Parameters: Ensure that OrderReport's parameter signature does not need to be adjusted to handle this modification
 */

package mobprogramming.problem;

public class OrderReport {
    private String customer;
    private int total;

    public OrderReport(String customer, int total){
        this.customer = customer;
        this.total = total;
    }

    public String invoice(){
        var message = "Invoice " + this.customer + " " + this.total;
        return message;
    }

    public String shippingLabel(){
        var message = "Shipping Label " + this.customer;
        return message;
    }
}


