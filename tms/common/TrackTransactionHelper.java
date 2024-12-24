package tms.common;
import java.io.BufferedReader;
import java.util.List;

import tms.transaction.SellerTransaction;
import tms.transaction.StatusForUser;
import tms.transaction.TransactionInterface;

public class TrackTransactionHelper {
    public void trackTransaction(List<? extends TransactionInterface> transactions,BufferedReader br)throws Exception{
        System.out.println("\nChoose a transaction to Track:");
        if(transactions.isEmpty()){
            System.out.println("No Transactions Available...");
            return;
        }

        displayDetails(transactions, br);
        System.out.print("\nChoose a Transaction(Enter 0 to exit):");
        int n = Integer.parseInt(br.readLine());
        
        if(n==0)
            return;
        if(n<1 || n>transactions.size()){
            System.out.println("Invlaid input...");
            return;
        }
        SellerTransaction t = (SellerTransaction)transactions.get(n-1);
        t.trackLocation();
    }
    public void displayDetails(List<? extends TransactionInterface> transactions,BufferedReader br)throws Exception{
        if(transactions.isEmpty()){
            System.out.println("No Transactions Available...");
            return;
        }
        System.out.println("S.No\tProduct\tSeller\tBuyer\tstartLocation\tcurrentLocation\tfinalLocation\tStatus\tMessage");
        System.out.println("------------------------------------------------------");
        for(int i=0;i<transactions.size();i++){
            SellerTransaction t = (SellerTransaction)transactions.get(i);
            StringBuilder str = new StringBuilder((""+(i+1)));
            str.append(".\t"+t.getProduct().getName())
                .append("\t"+t.getSeller().getName())
                .append("\t"+t.getBuyer().getName())
                .append("\t"+t.getStartLocation())
                .append("\t"+t.getCurrentLocation())
                .append("\t"+t.getDeliveryLocation()) 
                .append("\t"+(t.getUserStatus() == StatusForUser.DELVERED ? "Delivered" : (t.getUserStatus() == StatusForUser.TRANSIT ? "In Transit" : "Cancelled will br delivered to seller" )))
                .append("\t"+((t.getMessage() != null)?t.getMessage():"-"));
            System.out.println(str.toString());   
        }  
    }
}
