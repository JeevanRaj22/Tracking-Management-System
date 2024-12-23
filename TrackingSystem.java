import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TrackingSystem implements AgentInterface,SellerInterface,BuyerInterface{
    private List<Transaction> transactions;

    TrackingSystem(){
        this.transactions =  new ArrayList<Transaction>();
    }

    private<T extends TransactionInterface> List<T> filterTransaction(Class<T> c,Predicate<Transaction> tPredicate){
        return this.transactions.stream()
            .filter(tPredicate)
            // .map(t -> (T) t)
            .map(c::cast)
            .collect(Collectors.toList());
    }

    @Override
    public boolean addTransaction(SellerTransaction t){
        return this.transactions.add((Transaction)t);
    }

    @Override
    public List<SellerTransaction> getTransactions(Seller seller){
        // List<SellerTransaction> ftransactions = this.transactions.stream()
        //     .filter(t -> t.getSeller().equals(seller))
        //     .collect(Collectors.toList());
        // return ftransactions;
        return filterTransaction(SellerTransaction.class, t -> t.getSeller().equals(seller));

    }

    @Override
    public List<BuyerTransaction> getTransactions(Buyer buyer){
        // List<BuyerTransaction> btransactions = this.transactions.stream()
        //     .filter(t -> t.getBuyer().equals(buyer) && t.getUserStatus() != StatusForUser.CANCELLED)
        //     .collect(Collectors.toList());
        // return btransactions;
        return filterTransaction(BuyerTransaction.class,t -> t.getBuyer().equals(buyer) && t.getUserStatus() != StatusForUser.CANCELLED);
    }

    @Override
    public List<AgentTransaction> getTransactions(List<String> serviceLocations){
        // List<AgentTransaction> atransactions = this.transactions.stream()
        //     .filter(t-> t.getStatus() == TransactionStatus.INITIAL && serviceLocations.contains(t.getCurrentLocation().toLowerCase()))
        //     .collect(Collectors.toList());
        // return atransactions;
        return filterTransaction(AgentTransaction.class,t-> t.getStatus() == TransactionStatus.INITIAL && serviceLocations.contains(t.getCurrentLocation().toLowerCase()));
    }

    @Override
    public List<AgentTransaction> getTransactions(DeliveryAgent agent){
        // List<AgentTransaction> atransactions = this.transactions.stream()
        //     .filter(t -> (t.getStatus() == TransactionStatus.ONGOING && t.isCurrentAgent(agent)))
        //     .collect(Collectors.toList());
        // return atransactions;
        return filterTransaction(AgentTransaction.class,t -> (t.getStatus() == TransactionStatus.ONGOING && t.isCurrentAgent(agent)));
    }
    
}