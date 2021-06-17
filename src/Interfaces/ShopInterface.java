package Interfaces;

import Other.NTree;
import Other.Product;
import Users.Customer;
import Users.Trader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public interface ShopInterface {
    void showTraderMenu(Trader trader) throws IOException;

    NTree getCategory_tree();

    void showCustomerMenu(Customer customer) throws IOException;

    void login() throws IOException;

    void readCSV() throws FileNotFoundException, IOException;

    void sortby_price(LinkedList<Product> linkedList);
}
