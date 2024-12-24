package tms.transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import tms.users.Buyer;
import tms.users.DeliveryAgent;
import tms.users.Seller;

public class TrackingSystem implements AgentInterface,SellerInterface,BuyerInterface{
    private List<Transaction> transactions;

    public TrackingSystem(){
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
        return filterTransaction(SellerTransaction.class, t -> t.getSeller().equals(seller));

    }

    @Override
    public List<BuyerTransaction> getTransactions(Buyer buyer){
        return filterTransaction(BuyerTransaction.class,t -> t.getBuyer().equals(buyer) && t.getUserStatus() != StatusForUser.CANCELLED);
    }

    @Override
    public List<AgentTransaction> getTransactions(List<String> serviceLocations){
        return filterTransaction(AgentTransaction.class,t-> t.getStatus() == TransactionStatus.INITIAL && serviceLocations.contains(t.getCurrentLocation().toLowerCase()));
    }

    @Override
    public List<AgentTransaction> getTransactions(DeliveryAgent agent){
        
        return filterTransaction(AgentTransaction.class,t -> (t.getStatus() == TransactionStatus.ONGOING && t.isCurrentAgent(agent)));
    }
    
}