import java.io.BufferedReader;

public class TmsApplication {
    private TrackingSystem tms;
    TmsApplication(){
        tms = new TrackingSystem();
    }
    void runInterface(int userId,BufferedReader br)throws Exception{
        UserManager umgr = UserManager.getInstance();
        User user = umgr.getUser(userId);
        if(user == null){
            System.out.println("User not found or not recognised");
            return;
        }
        if(umgr.isBuyer(user)){
            BuyerInterface tms = this.tms;
            new BuyerUI(user,tms).dashboard(br);
        }else if(umgr.isAgent(user)){
            AgentInterface tms = this.tms;
            new AgentUI(user,tms).dashboard(br);
        }else if(umgr.isSeller(user)){
            SellerInterface tms = this.tms;
            new SellerUI(user,tms).dashboard(br);
        }
    }
}
