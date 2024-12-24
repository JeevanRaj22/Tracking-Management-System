package tms.users;
import java.io.BufferedReader;

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

    public User(int userId,String name,String address,String password,UserRole role){
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.password = password;
        this.role = role;
    }

    public User(int userId,UserRole role){
        this.userId = userId;
        this.role=role;
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

    public void getDetails(BufferedReader br)throws Exception{
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





