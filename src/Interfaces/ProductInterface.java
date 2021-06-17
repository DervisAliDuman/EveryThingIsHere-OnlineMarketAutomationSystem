package Interfaces;

public interface ProductInterface {
    String getID();

    void setID(String ID);

    String getName();

    void setName(String name);

    String getCategory();

    void setCategory(String category);

    Integer getPrice();

    void setPrice(int price);

    Integer getDiscounted_price();

    Double getDiscountPercentage();

    void setDiscounted_price(int discounted_price);

    String getDescription();

    void setDescription(String description);

    String getTrader();

    void setTrader(String trader);
}
