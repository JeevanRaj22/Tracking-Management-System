import java.io.BufferedReader;
import java.util.List;

public class BuyerUI extends TrackTransaction{
    private Buyer buyer;
    private BuyerInterface tms;
    BuyerUI(User user,BuyerInterface tms){
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

    public void viewTransaction(BufferedReader br)throws Exception{
        List<BuyerTransaction> transactions = this.tms.getTransactions(this.buyer);
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
        transactions.get(n-1).trackLocation();
    }

    public boolean cancelTransaction(BufferedReader br)throws Exception{
        List<BuyerTransaction> transactions = this.tms.getTransactions(this.buyer);
        if(transactions.isEmpty()){
            System.out.println("No Transactions Available...");
            return false;
        }
        displayDetails(transactions, br);
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
