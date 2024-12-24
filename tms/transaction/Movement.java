package tms.transaction;
import tms.users.DeliveryAgent;

public class Movement {
    private String startLocation;
    private String endLocation;
    private DeliveryAgent agent;

    Movement(String startLocation,String endLocation,DeliveryAgent agent){
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.agent = agent;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public DeliveryAgent getAgent() {
        return agent;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }
}