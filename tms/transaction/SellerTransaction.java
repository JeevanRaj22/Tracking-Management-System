package tms.transaction;

public interface SellerTransaction extends TransactionInterface{
    StatusForUser getUserStatus();
    String getMessage();
    void trackLocation();
}
