package tms.transaction;

import tms.users.DeliveryAgent;

public interface AgentTransaction extends TransactionInterface{
    boolean addMovement(DeliveryAgent agent,String location);
    boolean setCurrentLocation(String location);
    boolean isCurrentAgent(DeliveryAgent agent);
    TransactionStatus getStatus();
    void setMessage(String message);
    String getAgentDeliveryLocation();
}
