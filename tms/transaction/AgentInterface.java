package tms.transaction;

import java.util.List;

import tms.users.DeliveryAgent;

public interface AgentInterface{
    List<AgentTransaction> getTransactions(List<String> serviceLocations);
    List<AgentTransaction> getTransactions(DeliveryAgent agent);
}

