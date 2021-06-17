package ShopSystem;

import Interfaces.ShopInterface;
import Other.NTree;
import Other.Product;
import Users.Customer;
import Users.Person;
import Users.Trader;

import java.io.*;
import java.util.*;

public class Shop implements ShopInterface {

    private NTree category_tree = new NTree();

    public Shop(){
        /*Empty*/
    }

    /**
     * Shows Trader menu
     * @param trader trader that will do operations
     * @throws IOException
     */
    @Override
    public void showTraderMenu(Trader trader) throws IOException{
        int operation;
        Scanner scanInfo;
        String temp;
        boolean exit =false;

        while (!exit)try {  //loop for menu
            scanInfo = new Scanner(System.in);

            System.out.println("\n\nTrader Menu:\n");
            System.out.println("1-> Add item.");
            System.out.println("2-> Remove item.");
            System.out.println("3-> Edit item.");
            System.out.println("4-> List and Poll orders.");
            System.out.println("5-> Show this traders items.");
            System.out.println("0-> Exit from trader menu");
            System.out.print("Select one of the operations :");
            operation = scanInfo.nextInt();
            switch (operation){
                case 1:trader.addItem(); //Add item
                    break;
                case 2: scanInfo = new Scanner(System.in); //Remove item
                    System.out.print("Give ID of product that you want to remove: ");
                        temp = scanInfo.next();
                        if (trader.removeItem(temp))
                            System.out.println("Sucessfull removal");
                        else
                            System.out.println("UNSucessfull removal");
                    break;
                case 3: trader.editItem(); //Edit item
                    break;
                case 4: trader.listOrders(true); //List and Poll orders
                    break;
                case 5: ArrayList<Product> products = trader.getTraderItems();
                    //Show this traders items
                    if (products == null){
                        System.out.println("This trader doesnt have item");
                    }else {
                        int i;
                        for (i = 0; i < products.size(); i++) {
                            System.out.println("\n"+"ITEM: "+(i+1));
                            System.out.println("\n" + (i + 1) + "th item : " + products.get(i).getName());
                            System.out.println("description: " + products.get(i).getDescription());
                            System.out.println("price: " + products.get(i).getPrice());
                            System.out.println("discounted price: " + products.get(i).getDiscounted_price());
                        }
                    }
                    break;
                case 0: exit = true;
                default: break;
            }
        }catch (InputMismatchException e){
            System.out.println(e);
        }
    }

    /**
     * gets category tree
     * @return category tree
     */
    @Override
    public NTree getCategory_tree() {
        return category_tree;
    }

    /**
     * customer menu
     * @param customer customer that will do operations
     * @throws IOException
     */
    @Override
    public void showCustomerMenu(Customer customer) throws IOException{
        int operation;
        Scanner scanInfo;
        boolean exit =false;

        while (!exit)try {      //menu loop until exit operation
            scanInfo = new Scanner(System.in);

            System.out.println("\n\nCustomer Menu:\n");
            System.out.println("1-> search item.");
            System.out.println("2-> query item.");
            System.out.println("3-> show items from trader.");
            System.out.println("4-> Search after that query item.");
            System.out.println("0-> Exit from customer menu");
            System.out.print("Select one of the operations :");
            operation = scanInfo.nextInt();

            switch (operation){
                case 1: //search item
                    System.out.println("\n");
                    scanInfo = new Scanner(System.in);
                    System.out.println("1-> by name (biggest to smallest)");
                    System.out.println("2-> by price (smallest to biggest)");
                    System.out.println("3-> by discount prcentage (biggest to smallest)");
                    System.out.println("4-> do not sort");
                    System.out.print("Select one of the sort :");
                    operation = scanInfo.nextInt();

                    String keyword;
                    scanInfo = new Scanner(System.in);
                    System.out.print("Enter Keyword: ");
                    keyword = scanInfo.nextLine();

                    ArrayList<Product> products = customer.search(keyword);

                    if (operation == 1){        //Sorting types
                        products = sortby_name(products); //by name
                    }else if (operation == 2){
                        LinkedList<Product> willsort = new LinkedList<Product>(products);
                        sortby_price(willsort); //by price
                        products = new ArrayList<Product>(willsort);
                    }else if (operation == 3){ //by discount percentage
                        products = sortby_discountPercentage(products);
                    }


                    if (products == null){      //Printing items
                        System.out.println("There are no such element that you are looking");
                    }else{
                        int i;
                        for ( i = 0; i< products.size() ; i++){
                            System.out.println("\nITEM: " + (i + 1));
                            System.out.println("\n"+ (i+1) +"th item : "+ products.get(i).getName());
                            System.out.println("description: " + products.get(i).getDescription());
                            System.out.println("price: " + products.get(i).getPrice());
                            System.out.println("discounted price: " + products.get(i).getDiscounted_price());
                            System.out.println("trader: " + products.get(i).getTrader() +"\n");
                        }

                        System.out.print("Which item that numbered do you want to order ? : ");
                        scanInfo = new Scanner(System.in);
                        operation = scanInfo.nextInt(); //taking input index
                        while (operation < 1 || operation-1 >= products.size()){
                            System.out.print("Index out of bound, try again with another index: ");
                            scanInfo = new Scanner(System.in);
                            operation = scanInfo.nextInt();
                        }

                        if ( customer.giveOrder(products.get(operation-1))) //giving order
                            System.out.println("\nOrder Successful");
                        else
                            System.out.println("\nOrder UnSuccessful, because item already ordered");

                    }
                    break;
                case 2:customer.query_menu(category_tree); //query item
                    break;
                case 3: customer.traderListGiveOrder(); //show items from trader
                    break;
                case 4:
                    //search item
                    System.out.println("\n");
                    scanInfo = new Scanner(System.in);
                    System.out.println("1-> by name (biggest to smallest)");
                    System.out.println("2-> by price (smallest to biggest)");
                    System.out.println("3-> by discount prcentage (biggest to smallest)");
                    System.out.println("4-> do not sort");
                    System.out.print("Select one of the sort :");
                    operation = scanInfo.nextInt();

                    keyword = null;
                    scanInfo = new Scanner(System.in);
                    System.out.print("Enter Keyword: ");
                    keyword = scanInfo.nextLine();

                    products = customer.search(keyword);

                    if (operation == 1){        //Sorting types
                        products = sortby_name(products); //by name
                    }else if (operation == 2){
                        LinkedList<Product> willsort = new LinkedList<Product>(products);
                        sortby_price(willsort); //by price
                        products = new ArrayList<Product>(willsort);
                    }else if (operation == 3){ //by discount percentage
                        products = sortby_discountPercentage(products);
                    }

                    products = customer.queryThatTakesArrayList(products);


                    if (products == null){      //Printing items
                        System.out.println("There are no such element that you are looking");
                    }else{
                        int i;
                        for ( i = 0; i< products.size() ; i++){
                            System.out.println("\nITEM: " + (i + 1));
                            System.out.println("\n"+ (i+1) +"th item : "+ products.get(i).getName());
                            System.out.println("description: " + products.get(i).getDescription());
                            System.out.println("price: " + products.get(i).getPrice());
                            System.out.println("discounted price: " + products.get(i).getDiscounted_price());
                            System.out.println("trader: " + products.get(i).getTrader() +"\n");
                        }

                        System.out.print("Which item that numbered do you want to order ? : ");
                        scanInfo = new Scanner(System.in);
                        operation = scanInfo.nextInt(); //taking input index
                        while (operation < 1 || operation-1 >= products.size()){
                            System.out.print("Index out of bound, try again with another index: ");
                            scanInfo = new Scanner(System.in);
                            operation = scanInfo.nextInt();
                        }

                        if ( customer.giveOrder(products.get(operation-1))) //giving order
                            System.out.println("\nOrder Successful");
                        else
                            System.out.println("\nOrder UnSuccessful, because item already ordered");

                    }
                    break;
                case 0: exit = true;
                default: break;
            }
        }catch (InputMismatchException e){
            System.out.println(e);
        }


    }

    /**
     * Login system
     * @throws IOException
     */
    @Override
    public void login() throws IOException{
        Integer ID = 0;
        Integer password = 0;
        Boolean isLogined = false;

        Boolean isIDTrue = false, isPasswordTrue = false;
        Boolean isTrader = true;
        String newName = null;

        while (!isLogined){
            try {
                Scanner scanInfo = new Scanner(System.in);  //taking ID and Password
                System.out.print("Enter ID: ");
                ID = scanInfo.nextInt();
                scanInfo = new Scanner(System.in);

                System.out.print("Enter Password: ");
                password = scanInfo.nextInt();

                Person person = new Customer(ID,this, "temp",password);
                Scanner sc = null;

                sc = new Scanner(new File("src/TXTFiles/users.txt"));   //reading users.txt for controll
                sc.useDelimiter(";|\\n");

                String temp = null;
                int i=0;

                while (sc.hasNext()){
                    temp = sc.next();

                    if(i%4 == 0){
                        if (temp.equals("Trader"))
                            isTrader=true;  //that means we are trader
                        else
                            isTrader = false; //that means we are customer
                    }else if((i%4) == 1){
                        newName = temp;

                    }else if((i%4) == 2){
                        if (password.compareTo(Integer.parseInt(temp)) == 0 )
                            isPasswordTrue = true;  //password true

                    }else if((i%4) == 3){
                        if (ID.compareTo(Integer.parseInt(temp)) == 0 )
                            isIDTrue = true; //ID true

                        if (isIDTrue && isPasswordTrue){
                            isLogined = true; //we can login

                            break;
                        }

                        isIDTrue = false;
                        isPasswordTrue = false;
                        isTrader = false;
                    }
                    i++;
                }

                if (isLogined){     //logining and showing menues
                    if (isTrader)
                        System.out.println("LOGIN Successful.  "+newName  +"  Welcome to System as a Trader");
                    else
                        System.out.println("LOGIN Successful.  "+newName  +"  Welcome to System as a Customer");
                }else{
                    System.out.println("LOGIN Failed Try Again...");
                    System.out.println(ID+"  "+password);
                }

                sc.close();
            } catch (InputMismatchException e){
                System.out.println(e);
            }

        }

        if (isTrader)   //show menues
            showTraderMenu(new Trader(ID,this, newName,password));
        else
            showCustomerMenu(new Customer(ID,this, newName,password));

    }

    /**
     * reads and loads CSV File
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Override
    public void readCSV() throws FileNotFoundException,IOException {
        //reads csv file and creates then split 3 txt file
        Scanner sc = new Scanner(new File("src/e-commerce-samples.csv"));
        sc.useDelimiter(";|\\n");

        Hashtable<String, Trader> traders = new Hashtable<String, Trader>();
        int trader_ID = 10000000;

        Trader temp_trader = null;

        File file = null; //file

        FileWriter fileWriter = new FileWriter("src/TXTFiles/products.txt");
        FileWriter userWriter = new FileWriter("src/TXTFiles/users.txt");
        FileWriter fileWriter2 = new FileWriter("src/TXTFiles/orders.txt");
        fileWriter2.close();

        Scanner sc2 = null;
        String temp;
        String category = null;

        StringBuilder sb = new StringBuilder();

        int i = 0;
        int j = 0;

        for (int a = 0; a<7 && sc.hasNext(); a++){
            sc.next();
        }

        while (sc.hasNext()) {      //taking inputs to string builder then writing in files

            if(i%7 == 0){
                temp = sc.next();
                sb.append(temp + ";");

            }else if((i%7) == 1){
                temp = sc.next();
                sb.append(temp + ";");

            }else if((i%7) == 2){
                category = sc.next();
                category = category.substring(4, category.length()-4);
                category_tree.add(category);    //creating category tree
                sb.append(category + ";");

            }else if((i%7) == 3){
                temp = sc.next();
                sb.append(temp + ";");
            }else if((i%7) == 4){
                temp = sc.next();
                sb.append(temp + ";");
            }else if((i%7) == 5){
                temp = sc.next();
                sb.append(temp + ";");
            }else if((i%7) == 6){
                temp = sc.next();
                temp = temp.substring(0, temp.length()-1);
                sb.append(temp);

                if (traders.get(temp) == null){
                    temp_trader = new Trader(this, temp,123456);

                    traders.put(temp_trader.getName(), temp_trader);
                    userWriter.write("Trader;"+ temp_trader.getName() +";"+ temp_trader.getPassword() +";"+ temp_trader.getID()+"\n");
                    trader_ID +=1;
                }

                fileWriter.write( sb.toString()+ "\n");
                sb = new StringBuilder();
            }

            i++;
        }
        sc.close();  //closes the scanner
        fileWriter.close();
        userWriter.close();
    }

    /**
     * BubbleSort
     * @param products pruduct list will sorted
     * @return sorted list
     */
    private ArrayList<Product> sortby_name(ArrayList<Product> products){
        if ( products == null || products.size() == 0)
            return null;

        int passed = 1;
        boolean exchanged = true;

        while (exchanged){  //if exchange not done then list is sorted

            exchanged = false;

            for (int i = 0; i < products.size() - passed; i++) {
                if (products.get(i).getName().compareTo(products.get(i+1).getName()) < 0) {
                    // Exchange pairs
                    Product temp = products.get(i);
                    products.set(i, products.get(i+1));
                    products.set(i+1, temp);
                    exchanged = true; //Exchange is occured
                }
            }
            passed++;   //last element count
        };

        return products;
    }

    /**
     * Selection sort with iterator
     * @param linkedList product list will be sorted
     */
    @Override
    public void sortby_price(LinkedList<Product> linkedList) {
        if ( linkedList == null || linkedList.size()<2) {
            return;
        }

        Iterator<Product> iter1 = linkedList.iterator();
        Iterator<Product> iter2 = linkedList.iterator();
        //iterators
        Product min;
        int minIndex = 0;
        Product element;
        int cursor = 0;

        for (int position = 0; position < linkedList.size() - 1; position++) {
            iter1 = linkedList.iterator();
            cursor = 0;
            while (position>cursor) {   //coming position that it should
                iter1.next();
                cursor++;
            }

            min = iter1.next();
            iter2 = iter1;
            //finds min element in list
            for (int compared = position + 1; compared < linkedList.size() && iter2.hasNext(); compared++) {
                element = iter2.next();

                if ( element.getPrice().compareTo(min.getPrice()) < 0) {
                    minIndex = compared;
                    min = element;
                }
            }
            //swaping operation
            Product temp = linkedList.get(position);
            linkedList.set(position, linkedList.get(minIndex));
            linkedList.set(minIndex, temp);
        }
    }

    /**
     * Shell Sort
     * @param products product list will be sorted
     * @return sorted list
     */
    private ArrayList<Product> sortby_discountPercentage(ArrayList<Product> products){
        if ( products == null || products.size() == 0)
            return null;
        //inserting one by one
        for (int nextPosition = 1; nextPosition < products.size(); nextPosition++) {
            insert(products, nextPosition);
        }

        return products;
    }

    /**
     * Insertion sort
     * @param products pruduct list will sorted
     * @param nextPosition coming position
     */
    private void insert(ArrayList<Product> products, int nextPosition) {

        Product nextVal = products.get(nextPosition);
        //insetring one by one while finding proper position
        while (nextPosition > 0 && nextVal.getDiscountPercentage().compareTo(products.get(nextPosition-1).getDiscountPercentage()) > 0) {
            products.set(nextPosition, products.get(nextPosition-1));
            nextPosition--;
        }

        products.set(nextPosition, nextVal);
    }

}
