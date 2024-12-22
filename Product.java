public class Product {
    private String name;
    private float price;
    private Seller seller;

    Product(String name,float price,Seller seller){
        this.name = name;
        this.price = price;
        this.seller = seller;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public Seller getSeller() {
        return seller;
    }
}
