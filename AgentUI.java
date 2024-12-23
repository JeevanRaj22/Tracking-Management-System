import java.io.BufferedReader;
import java.util.List;

public class AgentUI {
    DeliveryAgent agent;
    AgentInterface tms;
    
    AgentUI(User user,AgentInterface tms){
        this.agent = (DeliveryAgent)user;
        this.tms = tms;
    }

    public void dashboard(BufferedReader br)throws Exception{
        System.out.println("\n\nWelcome "+agent.getName());
        while(true){
            System.out.println("\n1.Accept Transaction");
            System.out.println("2.Update Transaction");
            System.out.println("3.View Transaction");
            System.out.println("4.Logout");
            System.out.print("\nChoose and option:");
            int choice = Integer.parseInt(br.readLine());

            if(choice == 1){
                if(this.acceptTransaction(br))
                    System.out.println("\nTranscation accepted");
                    
                else
                    System.out.println("\nCouldn't accept transaction");
            }else if(choice == 2 ){
                if(this.updateTransaction(br))
                    System.out.println("\nTransaction updated");
                else
                    System.out.println("\nCouldn't update transaction");
            }
            else if(choice == 3){
                this.viewTransaction(br);
            }
            else if(choice == 4)
                break;
            else
                System.out.println("Invalid Choice...");
        }
    }
    
    public void viewTransaction(BufferedReader br)throws Exception{
        System.out.println("\n\nCurrent Transactions:");

        List<AgentTransaction> transactions = this.tms.getTransactions(this.agent);
        if(transactions.isEmpty()){
            System.out.println("No Transactions Available...");
            return;
        }

        System.out.println("S.No\tProduct\tSeller\tCurrent Location\tDelvery Location");
        System.out.println("------------------------------------------------------");
        for(int i=0;i<transactions.size();i++){
            AgentTransaction t = transactions.get(i);
            StringBuilder str = new StringBuilder((""+(i+1)));
            str.append(".\t"+t.getProduct().getName())
                .append("\t"+t.getSeller().getName())
                .append("\t"+t.getCurrentLocation())
                .append("\t"+t.getAgentDeliveryLocation());
            System.out.println(str.toString());
        }
    }

    public boolean acceptTransaction(BufferedReader br)throws Exception{
        List<AgentTransaction> transactions = this.tms.getTransactions(this.agent.serviceLocations);
        if(transactions.isEmpty()){
            System.out.println("No transactions available...");
            return false;
        }
        System.out.println("\n\nSelect a Transaction:");
        System.out.println("S.No\tProduct\tSeller\tCurrent Location\tDelvery Location");
        System.out.println("------------------------------------------------------");
        for(int i=0;i<transactions.size();i++){
            AgentTransaction t = transactions.get(i);
            StringBuilder str = new StringBuilder((""+(i+1)));
            str.append(".\t"+t.getProduct().getName())
                .append("\t"+t.getSeller().getName())
                .append("\t"+t.getCurrentLocation())
                .append("\t"+t.getDeliveryLocation());
            System.out.println(str.toString());
        }
        
        System.out.print("\nChoose a Transaction(Enter 0 to exit):");
        int n = Integer.parseInt(br.readLine());
        
        if(n==0)
            return false;
        if(n<1 || n>transactions.size()){
            System.out.println("Invlaid input...");
            return false;
        }
        AgentTransaction t =transactions.get(n-1);
        System.out.print("\nEnter your deleivery location:");
        String location = br.readLine().trim();

        return t.addMovement(agent, location);
    }

    public boolean updateTransaction(BufferedReader br)throws Exception{
        List<AgentTransaction> transactions = this.tms.getTransactions(this.agent);
        if(transactions.isEmpty()){
            System.out.println("No transactions available...");
            return false;
        }
        System.out.println("\n\nSelect a Transaction:");
        this.viewTransaction(br);
        System.out.print("\nChoose a Transaction (Enter 0 to exit):");
        int n = Integer.parseInt(br.readLine());

        if(n==0)
            return false;
        
        if(n<1 || n>transactions.size()){
            System.out.println("Invlaid input...");
            return false;
        }
        AgentTransaction t =transactions.get(n-1);

        System.out.println("\nChoose an operation:");
        System.out.println("1.Update Delivery Status");
        System.out.println("2.Update Message");
        System.out.println("3.Exit");
        System.out.print("\nChoose an option:");
        int option = Integer.parseInt(br.readLine());

        if(option == 1){
            t.setCurrentLocation(t.getAgentDeliveryLocation());
            t.setMessage(null);
        }else if(option == 2){
            System.out.print("\nEnter your message:");
            t.setMessage(br.readLine());
        }else if(option == 3){
            return false;
        }else{
            System.out.println("\nInvalid option...");
            return false;
        }
        return true;
    }
}
