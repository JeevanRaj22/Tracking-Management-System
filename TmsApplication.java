import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class TmsApplication {
    private List<Transaction> transactions;
    TmsApplication(){
        this.transactions = new ArrayList<Transaction>();
    }
    void runInterface(int userId,BufferedReader br)throws Exception{
        UserManager umgr = UserManager.getInstance();
        User user = umgr.getUser(userId);
        if(user == null){
            return;
        }
        if(umgr.isBuyer(user)){
            BuyerInterface tms = new TrackingSystem(this.transactions);
            new BuyerUI(user,tms).dashboard(br);
        }else if(umgr.isAgent(user)){
            AgentInterface tms = new TrackingSystem(this.transactions);
            new AgentUI(user,tms).dashboard(br);
        }else if(umgr.isSeller(user)){
            SellerInterface tms = new TrackingSystem(this.transactions);
            new SellerUI(user,tms).dashboard(br);
        }
    }
}
