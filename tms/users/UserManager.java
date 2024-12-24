package tms.users;

import tms.common.Product;
import tms.common.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserManager{
    private List<User> users;
    private static UserManager instance;
    private UserManager(){
        users =new ArrayList<User>();
        DeliveryAgent agent1 = new DeliveryAgent(511,"agent1","tirunelveli","123");
        this.users.add(agent1);
        agent1.setServiceLocation("tirunelveli");
        agent1.setServiceLocation("kovilpatti");
        agent1.setServiceLocation("madurai");
        agent1.setServiceLocation("tenkasi");
        
        DeliveryAgent agent2 = new DeliveryAgent(512,"agent2","madurai","123");
        this.users.add(agent2);
        agent2.setServiceLocation("coimbatore");
        agent2.setServiceLocation("chennai");
        agent2.setServiceLocation("madurai");

        System.out.println(agent1.getName());
        
        
        
        Seller s1 = new Seller(121,"seller1","tirunelveli","123");
        Seller s2 = new Seller(122,"seller2","kovilpatti","123");
        Seller s3 = new Seller(123,"seller3","tenkasi","123");

        Buyer b1 = new Buyer(311,"buyer1","chennai","123");
        Buyer b2 = new Buyer(312,"buyer2","coimbatore","123");
        Buyer b3 = new Buyer(313,"buyer3","madurai","123");

        Product p1 = new Product("prod1", 1000, s1);
        Order o1 = new Order(p1, s1, b1);
        s1.addOrder(o1);

        Product p2 = new Product("prod2", 500, s1);
        Order o2 = new Order(p2, s1, b2);
        s1.addOrder(o2);

        Product p3 = new Product("prod3", 5000, s2);
        Order t3 = new Order(p3, s2, b3);
        s2.addOrder(t3);
        
        Product p4 = new Product("prod4", 100, s3);
        Order t4 = new Order(p4, s3, b1);
        s3.addOrder(t4);

        this.users.add(s1);
        this.users.add(s2);
        this.users.add(s3);

        this.users.add(b1);
        this.users.add(b2);
        this.users.add(b3);
    }

    public static UserManager  getInstance(){
        if(UserManager.instance == null)
            UserManager.instance = new UserManager();
        return UserManager.instance;
    }

    public int login(BufferedReader br)throws IOException{
        System.out.print("\n Enter userId:");
        int userId = Integer.parseInt(br.readLine());

        for(int i=0;i<this.users.size();i++){
            User x = this.users.get(i);
            if(x.getUserId() == userId){
                System.out.print("\n Enter password:");
                String password = br.readLine();
                if(x.getPassword().equals(password)){
                    return i;
                }
                else{
                    System.out.println("Incorrect Password...");
                    return -1;
                }
            }
        }
        System.out.println("Invalid userId...");
        return -1;
    }

    public boolean registerAgent(BufferedReader br)throws Exception{
        System.out.print("\n\n");
        int userId;
        while(true){
            System.out.print("Enter a unique userId:");
            userId = Integer.parseInt(br.readLine());
            boolean uniqueId = true;
            for(User x: this.users){
                if(x.getUserId() == userId){
                    System.out.println("The userid is not available.Enter a unique userId...");
                    uniqueId = false;
                    break;
                }
            }
            if (uniqueId) break;
        }

        DeliveryAgent agent = new DeliveryAgent(userId, br);

        return this.users.add(agent);
    }

    public boolean isBuyer(User user){
        if(user instanceof Buyer)
            return true;
        return false;
    }

    public boolean isSeller(User user){
        if(user instanceof Seller)
            return true;
        return false;
    }

    public boolean isAgent(User user){
        if(user instanceof DeliveryAgent)
            return true;
        return false;
    }

    public User getUser(int index){
        return this.users.get(index);
    }

}
