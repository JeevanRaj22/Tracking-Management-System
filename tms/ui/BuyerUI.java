package tms.ui;
import java.io.BufferedReader;
import java.util.List;

import tms.common.TrackTransactionHelper;
import tms.transaction.BuyerInterface;
import tms.transaction.BuyerTransaction;
import tms.users.Buyer;
import tms.users.User;

public class BuyerUI{
    private Buyer buyer;
    private BuyerInterface tms;
    private TrackTransactionHelper track = new TrackTransactionHelper();

    public BuyerUI(User user,BuyerInterface tms){
        this.tms = tms;
        this.buyer = (Buyer)user;
    }


    public void dashboard(BufferedReader br)throws Exception{
        System.out.println("\n\nWelcome "+buyer.getName());
        while(true){
            System.out.println("\n1.View Transaction");
            System.out.println("2.Cancel Transaction");
            System.out.println("3.logout");
            System.out.print("\nChoose and option:");
            int choice = Integer.parseInt(br.readLine());

            if(choice == 1){
                this.viewTransaction(br);
            }else if(choice == 2){
                if(this.cancelTransaction(br))
                    System.out.println("\nTransaction cancelled");
                else
                    System.out.println("\nCouldn't cancel transaction");
            }else if(choice == 3 ){
                break;
            }else{
                System.out.println("Invalid Choice...");
            }
        }
    }

    private void viewTransaction(BufferedReader br)throws Exception{
        List<BuyerTransaction> transactions = this.tms.getTransactions(this.buyer);
        if(transactions.isEmpty()){
            System.out.println("No Transactions Available...");
            return;
        }

        track.trackTransaction(transactions, br);
    }

    private boolean cancelTransaction(BufferedReader br)throws Exception{
        List<BuyerTransaction> transactions = this.tms.getTransactions(this.buyer);
        if(transactions.isEmpty()){
            System.out.println("No Transactions Available...");
            return false;
        }
        track.displayDetails(transactions, br);
        System.out.print("\nChoose a Transaction(Enter 0 to exit):");
        int n = Integer.parseInt(br.readLine());
        
        if(n==0)
            return false;
        if(n<1 || n>transactions.size()){
            System.out.println("Invlaid input...");
            return false;
        }
        transactions.get(n-1).cancelTransaction();
        return true;
    }
}
