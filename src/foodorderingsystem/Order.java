package foodorderingsystem;

import java.util.ArrayDeque;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.Iterator;

public class Order implements Comparable<Order>
{

    public String orderID;
    public String customerID;
    public boolean isPrior;
    public LocalDateTime dateTime;
    public ArrayDeque<OrderItem> orderedItems;
    public double totalCost = 0;

    public Order(String orderID, String customerID, boolean isPrior) 
    {
        this.orderID = orderID;
        this.customerID = customerID;
        this.isPrior = isPrior;
        this.dateTime = LocalDateTime.now();
        this.orderedItems = new ArrayDeque<>();
    }

    //Complexity with O(1)
    public void addItem(int quantity, LinkedList<MenuItem> menuList, String id) 
    {
        OrderItem newItem = new OrderItem(menuList, id, quantity);

        if (orderedItems.isEmpty()) 
        {
            orderedItems.add(newItem);
            totalCost = newItem.getTotalPrice();
            return;
        }

        for (OrderItem item : orderedItems) 
        {
            if (item.item.getItemID().equalsIgnoreCase(newItem.item.getItemID())) 
            {
                item.quantity += newItem.quantity;
                item.totalPrice += newItem.getTotalPrice();
                calculateTotal();
                return;
            }

        }

        orderedItems.add(newItem);
        calculateTotal();

    }

    //Complexity with O(1)
    public void removeOrderItem(String itemID) 
    {
        //Iterator until the matching element is found
        Iterator<OrderItem> iterator = orderedItems.iterator();
        while(iterator.hasNext())
        {
            OrderItem item = iterator.next();
            if(item.item.getItemID().equalsIgnoreCase(itemID))
            {
                iterator.remove();
                calculateTotal();
                return;
            }
        }
    }

    //Complexity with O(1)
    public void calculateTotal() 
    {
        totalCost = 0;
        for (OrderItem item : orderedItems) 
        {
            totalCost += item.getTotalPrice();
        }
    }
    
    //Complexity with O(n)
    public String printOrders()
    {
        String msg = null;
        for(OrderItem item : orderedItems)
        {
            msg += item.toString();
        }
        return msg;
    }
    
    //Complexity with O(1)
    public boolean isPremium()
    {
        return this.isPrior == true;
    }
    
    public String getTotalSummary()
    {
        String msg;
        msg  = "\nOrder ID         : "+orderID;
        msg += "\nCustomer ID    : "+customerID;
        msg += "\nDate and Time  : "+dateTime;
        msg += "\nPriority Order : "+isPrior;
        msg += "\nOrdered Items  : "+printOrders();
        msg += "\nTotal Cost     : "+totalCost;
        return msg;
    }
    
     @Override
    public int compareTo(Order other) 
    {
        // Higher priority orders first
        if (this.isPrior && !other.isPrior)
        {
            return -1;
        }
        else if (!this.isPrior && other.isPrior)
        {
            return 1;
            
        }

        // If both have same priority, serve older first (FIFO)
        return this.dateTime.compareTo(other.dateTime);
    }

}
