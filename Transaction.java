import java.util.ArrayList;
import java.util.List;

enum TransactionStatus{
    INITIAL,
    ONGOING,
    FINISHED;
}
enum StatusForUser{
    TRANSIT,
    CANCELLED,
    DELVERED
}

public class Transaction implements BuyerTransaction,AgentTransaction{
    private Product product;
    private Buyer buyer;
    private Seller seller;
    private List<Movement> movementHistory;
    private String startLocation,deliveryLocation,currentLocation;
    private TransactionStatus status;
    private StatusForUser uStatus;
    private String message;
    

    Transaction(Order order){
        this.product= order.getProduct();
        this.seller = order.getSeller();
        this.buyer = order.getBuyer();

        this.startLocation = this.seller.getAddress();
        this.currentLocation = this.seller.getAddress();
        this.deliveryLocation = this.buyer.getAddress();
        
        this.status = TransactionStatus.INITIAL;
        this.uStatus = StatusForUser.TRANSIT;

        movementHistory = new ArrayList<Movement>();
        message = null;
    } 

    public String getStartLocation(){
        return this.startLocation;
    }

    public String getDeliveryLocation(){
        return this.deliveryLocation;
    }

    public String getCurrentLocation(){
        return this.currentLocation;
    }

    public Buyer getBuyer(){
        return this.buyer;
    }

    public Seller getSeller(){
        return this.seller;
    }
   
    public TransactionStatus getStatus(){
        return this.status;
    }

    public StatusForUser getUserStatus(){
        return this.uStatus;
    }

    public Product getProduct(){
        return this.product;
    }

    public void setMessage(String message){
        this.message = message;
    }
    
    public String getMessage(){
        return this.message;
    }

    public boolean setCurrentLocation(String location){
        if(location == null || location.isEmpty()){
            System.out.println("Location must not be empty...");
            return false;
        }
        if(location.equals(this.deliveryLocation)){
            this.status = TransactionStatus.FINISHED;
            if(this.uStatus != StatusForUser.CANCELLED)
                this.uStatus = StatusForUser.DELVERED;
        }else{
            this.status = TransactionStatus.INITIAL;
        }
        this.currentLocation = location;
        return true;
    }

    public boolean addMovement(DeliveryAgent agent,String location){
        Movement m = new Movement(this.currentLocation, location, agent);
        this.status = TransactionStatus.ONGOING;
        return movementHistory.add(m);
    }

    public void cancelTransaction(){
        this.uStatus = StatusForUser.CANCELLED;
        this.deliveryLocation = this.startLocation;
        // notify seller here
        if(this.status == TransactionStatus.ONGOING){
            this.movementHistory.get(movementHistory.size()-1).endLocation = this.movementHistory.get(movementHistory.size()-1).startLocation;
            //notify agent here
        }else if(this.status == TransactionStatus.INITIAL){
            if(this.movementHistory.size()==0)
                this.status = TransactionStatus.FINISHED;

        }else{
            this.status = TransactionStatus.INITIAL;
        }
    }

    public void trackLocation(){
        System.out.println("\n\nTransaction Information:");
        System.out.println("Product:"+this.getProduct().getName());
        System.out.println("Start Location:"+this.getStartLocation());
        System.out.println("Delivery Location:"+this.getDeliveryLocation());
        System.out.println("Status:"+((this.getStatus() == TransactionStatus.FINISHED)?"Delivered":"In Transit"));

        if(this.movementHistory.size() == 0){
            System.out.println("Product delivery is not initiliased...");
            return;
        }

        System.out.println("\nLocation Tracking:");
        int i = 0;
        while(i < this.movementHistory.size()){
            Movement m = this.movementHistory.get(i);
            System.out.println("Start Location:"+ m.startLocation);
            System.out.println("End Location:"+m.endLocation);
            System.out.println("Agent:"+m.agent.getName());
            
            System.out.println("\n");
            i++;
        } 
    }

    public boolean isCurrentAgent(DeliveryAgent agent){
        if(!this.movementHistory.isEmpty()){
            return this.movementHistory.get(this.movementHistory.size()-1).agent.equals(agent);
        }
        return false;
    }
    
    public String getAgentDeliveryLocation(){
        return this.movementHistory.get(this.movementHistory.size()-1).endLocation;
    }
}
