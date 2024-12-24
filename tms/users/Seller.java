package tms.users;

import java.util.ArrayList;
import java.util.List;

import tms.common.Order;

public class Seller extends User{
    private List<Order> orders;

    public Seller(int userId,String name,String address,String password){
        super(userId,name,address,password,UserRole.SELLER);
        orders = new ArrayList<Order>();
    }

    public void addOrder(Order order){
        this.orders.add(order);
    }

    public List<Order> getOrders(){
        return this.orders;
    }
}
