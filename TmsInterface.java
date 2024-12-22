import java.util.List;

interface BuyerInterface{
    List<BuyerTransaction> getTransactions(Buyer buyer);
}

interface SellerInterface{
    boolean addTransaction(Transaction t);
    List<SellerTransaction> getTransactions(Seller seller);
}

interface AgentInterface{
    List<AgentTransaction> getTransactions(List<String> serviceLocations);
    List<AgentTransaction> getTransactions(DeliveryAgent agent);
}
