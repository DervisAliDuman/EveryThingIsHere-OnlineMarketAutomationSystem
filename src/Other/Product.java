package Other;

import Interfaces.ProductInterface;

public class Product implements ProductInterface {
    String ID;  //ID of product
    String name;    //name of product
    String category;    //category of product
    Integer price;  //price of product
    Integer discounted_price;   //discounted price of product
    String description;     //item decription
    String trader;  //trader of product

    /**
     *
     * @param ID ID of product
     * @param name name of product
     * @param category category of product
     * @param price price of product
     * @param discounted_price discounted price of product
     * @param description item decription
     * @param trader trader of product
     */
    public Product(String ID, String name, String category, String price, String discounted_price, String description, String trader) {
        this.ID = ID;
        this.name = name;
        this.category = category;
        this.price = Integer.parseInt(price);
        this.discounted_price = Integer.parseInt(discounted_price);
        this.description = description;
        this.trader = trader;
    }

    /**
     *
     * @return ID
     */
    @Override
    public String getID() {
        return ID;
    }

    /**
     * sets ID
     * @param ID ID will set
     */
    @Override
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     *
     * @return name of product
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     *
     * @param name name will set
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return category
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category category will set
     */
    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return price
     */
    @Override
    public Integer getPrice() {
        return price;
    }

    /**
     *
     * @param price price will set
     */
    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     *
     * @return discounted price
     */
    @Override
    public Integer getDiscounted_price() {
        return discounted_price;
    }

    /**
     *
     * @return discount percentage
     */
    @Override
    public Double getDiscountPercentage(){
        double diff = (double)getPrice()-(double)getDiscounted_price();
        double answ = diff / (double)getPrice();
        return answ*100;
    }

    /**
     *
     * @param discounted_price discounted_price will set
     */
    @Override
    public void setDiscounted_price(int discounted_price) {
        this.discounted_price = discounted_price;
    }

    /**
     *
     * @return description
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description description will set
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return trader name
     */
    @Override
    public String getTrader() {
        return trader;
    }

    /**
     *
     * @param trader trader will set
     */
    @Override
    public void setTrader(String trader) {
        this.trader = trader;
    }
}
