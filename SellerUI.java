import java.io.BufferedReader;
import java.util.List;

public class SellerUI extends TrackTransaction{
    private Seller seller;
    private SellerInterface tms;
    SellerUI(User user,SellerInterface tms){
        this.tms = tms;
        this.seller = (Seller)user;
    }  

    public void dashboard(BufferedReader br)throws Exception{
        System.out.println("\n\nSeller dashboard");
        while(true){
            System.out.println("\n1.Initiate Transaction");
            System.out.println("2.View Transaction");
            System.out.println("3.logout");
            System.out.print("\nChoose and option:");
            int choice = Integer.parseInt(br.readLine());

            if(choice == 1)
                this.initiateTransaction(br);
            else if(choice == 2 ){
                this.viewTransaction(br);
            }
            else if(choice == 3)
                break;
            else
                System.out.println("Invalid Choice...");
        }
    }

    public void viewTransaction(BufferedReader br)throws Exception{
        List<SellerTransaction> transactions = this.tms.getTransactions(this.seller);
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

    public boolean initiateTransaction(BufferedReader br)throws Exception{
        List<Order> orders = this.seller.getOrders();
        if(orders.isEmpty()){
            System.out.println("\n\nNo orders available...");
            return false;
        }
        System.out.println("\n\nYour Orders:");
        System.out.println("S.No\tProduct\tPrice\tBuyer");
        System.out.println("------------------------------------------------------");
        for(int i=0;i<orders.size();i++){
            StringBuilder str = new StringBuilder((""+(i+1)));
            str.append(".\t"+orders.get(i).getProduct().getName())
                .append("\t"+orders.get(i).getProduct().getPrice())
                .append("\t"+orders.get(i).getBuyer().getName());
            System.out.println(str.toString());
        }
        
        System.out.print("\nChoose an Transaction:");
        int n = Integer.parseInt(br.readLine());

        if(n<1 || n>orders.size()){
            System.out.println("Invalid input...");
            return false;
        }

        Order order = orders.remove(n-1);
        Transaction t = new Transaction(order);
        return this.tms.addTransaction(t);
    }
}
