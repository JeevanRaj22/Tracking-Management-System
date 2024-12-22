public class Movement {
    String startLocation;
    String endLocation;
    DeliveryAgent agent;

    Movement(String startLocation,String endLocation,DeliveryAgent agent){
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.agent = agent;
    }

}