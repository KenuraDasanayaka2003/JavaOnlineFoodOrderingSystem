package foodorderingsystem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class OrderManager 
{
    private Queue<Order> normalOrders;
    private PriorityQueue<Order> priorityOrders;
    private List<Order> completedOrders;
    
    //Constructor to initialize the Lists with Orders
    public OrderManager()
    {
        this.normalOrders = new ArrayDeque<>();
        this.priorityOrders = new PriorityQueue<>();
        this.completedOrders = new ArrayList<>();
    }
    
    //Complexity with O(1)
    public void placeOrder(Order order)
    {
        if(order.isPremium())
        {
            priorityOrders.add(order);
        }
        else
        {
            normalOrders.add(order);
        }
    }
    
    //Complexity with O(1)
    public Order cancelOrder(String key) 
    {
        Order deleted = null;

        // Cancel from priority orders
        Iterator<Order> priorityIterator = priorityOrders.iterator();
        while(priorityIterator.hasNext()) 
        {
            Order order = priorityIterator.next();
            if(order.orderID.equalsIgnoreCase(key)) 
            {
                priorityIterator.remove();
                deleted = order;
                return deleted; // stop after first match
            }
        }

        // Cancel from normal orders
        Iterator<Order> normalIterator = normalOrders.iterator();
        while(normalIterator.hasNext()) 
        {
            Order order = normalIterator.next();
            if(order.orderID.equalsIgnoreCase(key)) 
            {
                normalIterator.remove();
                deleted = order;
                return deleted;
            }
        }

        return deleted; // returns null if order not found
    }

    //Complexity with O(1)
    public Order getServingOrder()
    {
        Order servingOrder = null;
        if(!priorityOrders.isEmpty())
        {
            servingOrder = priorityOrders.poll();
        }
        else if(!normalOrders.isEmpty())
        {
            servingOrder = normalOrders.poll();
        }
        return servingOrder;
    }
    
    //Complexity with O(1)
    public Order getNextOrder()
    {
        Order nextOrder = null;
        if(!priorityOrders.isEmpty())
        {
            nextOrder = priorityOrders.peek();
        }
        else if(!normalOrders.isEmpty())
        {
            nextOrder = normalOrders.peek();
        }
        return nextOrder;
    }
   
    //Getters to get the two lists with orders
    //Complexity with O(1)
    public Queue<Order> getNormalOrders()
    {
        return normalOrders;
    }
    
    //Complexity with O(1)
    public PriorityQueue<Order> getPriorityOrders()
    {
        return priorityOrders;
    }
}
