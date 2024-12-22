import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

enum UserRole{
    BUYER,
    SELLER,
    AGENT;
}
public abstract class User{
    private int userId;
    private String name,address;
    private String password;
    private UserRole role;

    User(int userId,String name,String address,String password,UserRole role){
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.password = password;
        this.role = role;
    }

    public User(int userId){
        this.userId = userId;
    }

    public int getUserId(){
        return userId;
    }

    public boolean setPassword(String password){
        if(password == null || password.trim().isEmpty()){
            System.out.println("\nPassword must not be empty...");
            return false;
        }
        this.password = password;
        return true;
    }

    public String getPassword(){
        return this.password;
    }

    public boolean setName(String name){
        if(name == null || name.trim().isEmpty()){
            System.out.println("\nName must not be empty...");
            return false;
        }
        this.name = name;
        return true;
    }

    public String getName(){
        return this.name;
    }

    public boolean setAddress(String address){
        if(address == null || address.trim().isEmpty()){
            System.out.println("\nAddress must not be empty...");
            return false;
        }
        this.address = address;
        return true;
    }

    public String getAddress(){
        return this.address;
    }

    public UserRole getRole(){
        return this.role;
    }

    public void getDetails(UserRole role,BufferedReader br)throws Exception{
        this.role = role;
        String name;
        do{
            System.out.print("\nEnter name:");
            name = br.readLine();
        }while(!setName(name));
       
        String address;
        do{
            System.out.print("\nEnter address:");
            address = br.readLine();
        }while(!setAddress(address));

        String password;
        do{
            System.out.print("\nEnter password:");
            password = br.readLine();
        }while(!setPassword(password));
    }
}

class Buyer extends User{
    Buyer(int userId,String name,String address,String password){
        super(userId,name,address,password,UserRole.BUYER);
    }
    
}

class Seller extends User{
    private List<Order> orders;

    Seller(int userId,String name,String address,String password){
        super(userId,name,address,password,UserRole.SELLER);
        orders = new ArrayList<Order>();
    }

    void addOrder(Order order){
        this.orders.add(order);
    }

    List<Order> getOrders(){
        return this.orders;
    }
}

class DeliveryAgent extends User{
    List<String> serviceLocations;

    DeliveryAgent(int userId,String name,String address,String password){
        super(userId,name,address,password,UserRole.AGENT);
        serviceLocations = new ArrayList<String>();
    }

    DeliveryAgent(int userId,BufferedReader br)throws Exception{
        super(userId);
        serviceLocations = new ArrayList<String>();

        this.getDetails(br);
    }

    boolean setServiceLocation(String location){
        if(location == null || location.trim().isEmpty()){
            System.out.println("\nLocation must not be empty:Try again...");
            return false;
        }
        return serviceLocations.add(location);
    }

    void getDetails(BufferedReader br)throws Exception{
        super.getDetails(UserRole.AGENT,br);
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

}