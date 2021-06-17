import Other.NTree;
import Other.Product;
import ShopSystem.Shop;
import Users.Customer;
import Users.Trader;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop();
        int i;

        try {
            shop.readCSV();
            LinkedList<Customer> customers = new LinkedList<Customer>();
            customers.add(new Customer(shop, "Ahmet", 123456));
            customers.add(new Customer(shop, "Mehmet", 123456));
            customers.add(new Customer(shop, "Ali", 123456));
            customers.add(new Customer(shop, "Berk", 123456));
            customers.add(new Customer(shop, "Can", 123456));
            customers.add(new Customer(shop, "Usain", 123456));
            customers.add(new Customer(shop, "Deniz", 123456));
            customers.add(new Customer(shop, "Derya", 123456));
            customers.add(new Customer(shop, "DAD", 123456));
            customers.add(new Customer(shop, "Faruk", 123456));
            Customer temp= null;

            FileWriter userWriter = new FileWriter("src/TXTFiles/users.txt",true);

            Iterator iter = customers.iterator();

            while (iter.hasNext()){
                temp = (Customer) iter.next();
                userWriter.write("USER;"+ temp.getName() +";"+ temp.getPassword() +";"+ temp.getID()+"\n");
            }

            userWriter.close();

            Customer customer = new Customer(70000007,shop, "Derya",123456);
            Trader trader = new Trader(10000000,shop,"Alisha",123456);


            customer.showTraderList();
            ArrayList<Product> products = customer.search("Boot");

            if (products == null){
                System.out.println("\n\nThere is no item with this keyword\n");
            }else {
                for (int j= 0 ; j < products.size(); j++){
                    System.out.println("\n"+"ITEM: "+(j+1));
                    System.out.println("\n"+ (j+1) +"th item : "+ products.get(j).getName());
                    System.out.println("description: " + products.get(j).getDescription());
                    System.out.println("price: " + products.get(j).getPrice());
                    System.out.println("discounted price: " + products.get(j).getDiscounted_price());
                    System.out.println("trader: " + products.get(j).getTrader() +"\n");
                }
            }


            customer.showTraderList();
            products = customer.searchByTraderName("Fancy Store 999");

            if (products == null){
                System.out.println("\n\nThis trader doesnt exist\n");
            }else {
                for (int j= 0 ; j < products.size(); j++){
                    System.out.println("\n"+"ITEM: "+(j+1));
                    System.out.println("\n"+ (j+1) +"th item : "+ products.get(j).getName());
                    System.out.println("description: " + products.get(j).getDescription());
                    System.out.println("price: " + products.get(j).getPrice());
                    System.out.println("discounted price: " + products.get(j).getDiscounted_price());
                    System.out.println("trader: " + products.get(j).getTrader() +"\n");
                }
            }


            products = customer.searchByTraderName("Alisha");

            if (products == null){
                System.out.println("\n\nThis trader doesnt exist\n");
            }else {
                for (int j= 0 ; j < products.size(); j++){
                    System.out.println("\n"+"ITEM: "+(j+1));
                    System.out.println("\n"+ (j+1) +"th item : "+ products.get(j).getName());
                    System.out.println("description: " + products.get(j).getDescription());
                    System.out.println("price: " + products.get(j).getPrice());
                    System.out.println("discounted price: " + products.get(j).getDiscounted_price());
                    System.out.println("trader: " + products.get(j).getTrader() +"\n");
                }
            }

            products = customer.query("Clothing >> Men's Clothing",1000,200);

            if (products == null){
                System.out.println("There are no such element that you are looking");
            }else {
                System.out.println("\n\nQueried Items: \n");
                for (i = 0; i < products.size(); i++) {
                    System.out.println("\nITEM: " + (i + 1));
                    System.out.println("\n" + (i + 1) + "th item : " + products.get(i).getName());
                    System.out.println("description: " + products.get(i).getDescription());
                    System.out.println("price: " + products.get(i).getPrice());
                    System.out.println("discounted price: " + products.get(i).getDiscounted_price());
                    System.out.println("trader: " + products.get(i).getTrader() + "\n");
                }
            }

            Product product = new Product("SRTEH2FF9KEDEFGF", "Alisha Solid Women's Cycling Shorts", "Clothing >> Women's Clothing >> Lingerie, Sleep & Swimwear >> Shorts >> Alisha Shorts >> Alisha Solid Women's Cycling Shorts", "999", "379", "Key Features of Alisha Solid Women's Cycling Shorts Cotton Lycra Navy, Red, Navy,Specifications of Alisha Solid Women's Cycling Shorts Shorts Details Number of Contents in Sales Package Pack of 3 Fabric Cotton Lycra Type Cycling Shorts General Details Pattern Solid Ideal For Women's Fabric Care Gentle Machine Wash in Lukewarm Water, Do Not Bleach Additional Details Style Code ALTHT_3P_21 In the Box 3 shorts", "Alisha");
            if (customer.giveOrder(product)){
                System.out.println("Order is Successful");
            }else {
                System.out.println("Order is UNSuccessful, Already ordered");
            }
            System.out.println("Second time same item ordering");
            if (customer.giveOrder(product)){
                System.out.println("Order is Successful");
            }else {
                System.out.println("Order is UNSuccessful, Already ordered");
            }


            if (trader.removeItem("SRTEH2FVVKRBAXHB")){
                System.out.println("Remove is Successful");
            }else {
                System.out.println("Remove is UNSuccessful, Already ordered or does not exist");
            }
            System.out.println("Trying to Remove same item");
            if (trader.removeItem("SRTEH2FVVKRBAXHB")){
                System.out.println("Remove is Successful");
            }else {
                System.out.println("Remove is UNSuccessful, Already ordered or does not exist");
            }
            System.out.println("Trying to Remove another does not exist item");
            if (trader.removeItem("AAAAAAAAAAAAAAAA")){
                System.out.println("Remove is Successful");
            }else {
                System.out.println("Remove is UNSuccessful, Already ordered or does not exist");
            }



            Product willadd = new Product("ABCDEFH46ZS","ADDED ITEM","UNIQUE CATEGORY >> UNIQUE SUB CATEGORY","9999","4667","ADDED  ITEM DESCRIPTION","Alisha");

            if (trader.addItem(willadd)){
                System.out.println("Successful adding product");
            }else{
                System.out.println("UNSuccessful adding, try unique ID");
            }
            System.out.println("Trying to Add same item");
            if (trader.addItem(willadd)){
                System.out.println("Successful adding product");
            }else{
                System.out.println("UNSuccessful adding, try unique ID");
            }

            //Editing ITEM
            product.setPrice(999);
            product.setDescription("EDITED DESCRIPTION");
            product.setDiscounted_price(14);
            product.setName("EDITED NAME");

            if (trader.edit(product)){
                System.out.println("Successful editing product");
            }else{
                System.out.println("UNSuccessful editing product, product ID does not exist");
            }
            System.out.println("Trying to edit not existed ID");
            product.setID("SGFGJDGHJGKBGFGHFG");
            if (trader.edit(product)){
                System.out.println("Successful editing product");
            }else{
                System.out.println("UNSuccessful editing product, product ID does not exist");
            }

            if (trader.listOrders(false)){
                if (trader.popOrder(true)){
                    System.out.println("Ordered Item successfully sold");
                }else{
                    System.out.println("Ordered Item does not exist");
                }
            }
            System.out.println("Trying to poll while there are no orders");
            if (trader.popOrder(true)){
                System.out.println("Ordered Item successfully sold");
            }else{
                System.out.println("Ordered Item does not exist or there are no order left");
            }

            boolean go_on = true;
            while (go_on) {
                try {

                    System.out.println("\n\n\nWelcome to Everything Is Here \n");
                    System.out.println("press 1 for login.");
                    System.out.println("press 2 for exit whole program.");
                    System.out.print("Option: ");
                    Scanner sc = new Scanner(System.in);
                    int option = sc.nextInt();
                    if (option == 1)
                        shop.login();
                    else if (option == 2)
                        go_on = false;
                    else
                        System.out.println("Please type valid input.");

                } catch (InputMismatchException a) {
                    System.out.println(a);
                    System.out.println("Please type valid input:");
                }
            }
            /*shop.showCustomerMenu(customer);;
            shop.showTraderMenu(trader);*/

        }catch (FileNotFoundException e){
            System.out.println(e);
        }catch (IOException ee){
            System.out.println(ee);
        }catch (InputMismatchException a){
            System.out.println(a);
        }


    }
}
