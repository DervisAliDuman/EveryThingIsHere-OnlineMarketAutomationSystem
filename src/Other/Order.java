package Other;

import Interfaces.OrderInterface;
import Users.Customer;

public class Order implements OrderInterface {
    private Product item; //ordered item
    private Customer customer; //customer that ordered item

    private String itemID ; //ordered item ID
    private String customerID; //customer ID that ordered item

    /**
     *
     * @param item ordered item
     * @param customer customer that ordered item
     */
    public Order(Product item, Customer customer) {
        this.item = item;
        this.customer = customer;
        this.itemID = item.getID();
        this.customerID = ""+customer.getID();
    }

    /**
     *
     * @param itemID ordered item ID
     * @param customerID customer ID that ordered item
     */
    public Order(String itemID, String customerID){
        this.itemID = itemID;
        this.customerID = customerID;
    }

    /**
     * return ordered item ID
     * @return ordered item ID
     */
    @Override
    public String getItemID() {
        return itemID;
    }

    /**
     * sets ordered item ID
     * @param itemID ordered item ID
     */
    @Override
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    /**
     * gets customer ID that ordered item
     * @return customer ID that ordered item
     */
    @Override
    public String getCustomerID() {
        return customerID;
    }

    /**
     * sets customer ID that ordered item
     * @param customerID customer ID that ordered item
     */
    @Override
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    /**
     * return ordered item
     * @return ordered item
     */
    @Override
    public Product getItem() {
        return item;
    }

    /**
     * sets ordered item
     * @param item ordered item
     */
    @Override
    public void setItem(Product item) {
        this.item = item;
    }

    /**
     * return customer that ordered item
     * @return customer that ordered item
     */
    @Override
    public Customer getCustomer() {
        return customer;
    }

    /**
     *  sets customer ID that ordered item
     * @param customer customer ID that ordered item
     */
    @Override
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
