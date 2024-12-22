public class Order {
    private Product product;
    private Buyer buyer;
    private Seller seller;

    Order(Product product,Seller seller,Buyer buyer){
        this.product = product;
        this.buyer = buyer;
        this.seller = seller;
    }

    public Product getProduct() {
        return product;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Seller getSeller() {
        return seller;
    }

    

    
}
