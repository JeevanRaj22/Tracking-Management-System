package tms.transaction;

public interface BuyerTransaction extends SellerTransaction{
    void cancelTransaction();
}
