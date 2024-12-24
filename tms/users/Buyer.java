package tms.users;

public class Buyer extends User{
    public Buyer(int userId,String name,String address,String password){
        super(userId,name,address,password,UserRole.BUYER);
    }
    
}