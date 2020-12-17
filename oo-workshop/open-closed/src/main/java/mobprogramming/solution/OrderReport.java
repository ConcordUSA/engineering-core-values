/**
 * Open/Closed Principle
 *
 * Software elements (classes, modules, functions, etc...)
 * should be open for extension, but closed for modification
 */

/** Task: Update the Bill of Lading method to include the address
 */

package mobprogramming.solution;

public class OrderReport {
    protected String customer;
    protected int total;

    public OrderReport(String customer, int total){
        this.customer = customer;
        this.total = total;
    }
}


