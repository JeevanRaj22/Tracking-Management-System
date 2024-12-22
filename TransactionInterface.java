public interface TransactionInterface {
    String getStartLocation();
    String getDeliveryLocation();
    String getCurrentLocation();
    Buyer getBuyer();
    Seller getSeller();
    Product getProduct();
}

interface SellerTransaction extends TransactionInterface{
    StatusForUser getUserStatus();
    String getMessage();
    void trackLocation();
}

interface BuyerTransaction extends SellerTransaction{
    void cancelTransaction();
}

interface AgentTransaction extends TransactionInterface{
    boolean addMovement(DeliveryAgent agent,String location);
    boolean setCurrentLocation(String location);
    boolean isCurrentAgent(DeliveryAgent agent);
    TransactionStatus getStatus();
    void setMessage(String message);
    String getAgentDeliveryLocation();
}
