package Interfaces;

import Other.Product;
import Users.Customer;

public interface OrderInterface {
    String getItemID();

    void setItemID(String itemID);

    String getCustomerID();

    void setCustomerID(String customerID);

    Product getItem();

    void setItem(Product item);

    Customer getCustomer();

    void setCustomer(Customer customer);
}
