package tms.transaction;

import java.util.List;

import tms.users.Seller;

public interface SellerInterface{
    boolean addTransaction(SellerTransaction t);
    List<SellerTransaction> getTransactions(Seller seller);
}
