package tms.transaction;

import tms.users.Buyer;
import tms.users.Seller;
import tms.common.Product;

public interface TransactionInterface {
    String getStartLocation();
    String getDeliveryLocation();
    String getCurrentLocation();
    Buyer getBuyer();
    Seller getSeller();
    Product getProduct();
}
