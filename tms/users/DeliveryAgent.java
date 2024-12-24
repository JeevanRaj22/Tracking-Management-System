package tms.users;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class DeliveryAgent extends User{
    private List<String> serviceLocations;

    public DeliveryAgent(int userId,String name,String address,String password){
        super(userId,name,address,password,UserRole.AGENT);
        serviceLocations = new ArrayList<String>();
    }

    public DeliveryAgent(int userId,BufferedReader br)throws Exception{
        super(userId,UserRole.AGENT);
        serviceLocations = new ArrayList<String>();

        this.getDetails(br);
    }

    public boolean setServiceLocation(String location){
        if(location == null || location.trim().isEmpty()){
            System.out.println("\nLocation must not be empty:Try again...");
            return false;
        }
        return serviceLocations.add(location);
    }

    public void getDetails(BufferedReader br)throws Exception{
        super.getDetails(br);
        System.out.print("\nEnter the number of service Locations:");
        int n = Integer.parseInt(br.readLine());

        System.out.println("\nEnter service location one by one...");
        int i = 0;
        while(i<n){
            while(!this.setServiceLocation(br.readLine())){
                System.out.println("\nTry again...");
            }
            i++;
        }
    }

    public List<String> getServiceLocations(){
        return this.serviceLocations;
    }

}
