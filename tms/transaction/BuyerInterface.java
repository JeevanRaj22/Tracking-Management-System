package tms.transaction;
import java.util.List;

import tms.users.Buyer;

public interface BuyerInterface{
    List<BuyerTransaction> getTransactions(Buyer buyer);
}
