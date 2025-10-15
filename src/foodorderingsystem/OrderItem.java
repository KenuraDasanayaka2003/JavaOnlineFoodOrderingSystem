package foodorderingsystem;

public class OrderItem 
{
    public MenuItem item;
    public int quantity;
    public double totalPrice;
    
    //Costructor to initialize the fields of OrderItem
    public OrderItem(LinkedList menuList,String id,int quantity)
    {
        this.item = findMenuItem(menuList,id);
        this.quantity = quantity;
        setTotalPrice(getTotalPrice());
    }
    
    //Complexity with O(n) 
    public MenuItem findMenuItem(LinkedList menuList,String id)
    {
        MenuItem item = null;
        
        MenuItem found = (MenuItem) menuList.findItemByID(id);
        
        if(found != null)
        {
            item = found;
        }
        
       return item; 
    }
    
    //complexity with O(1)
    public double getTotalPrice()
    {
        double price = 0;
        if(item != null)
        {
            price = quantity * item.getItemPrice();
        }
        return price;
    }
    
    //Complexity with O(1)
    public void setTotalPrice(double totalPrice)
    {
        this.totalPrice = totalPrice;
    }
    
    //Complexity with O(1) 
    @Override
    public String toString()
    {
        String msg;
        msg = "\nItem ID           : "+item.getItemID();
        msg += "\nItem Name        : "+item.getItemName();
        msg += "\nUnit Price       : "+item.getItemPrice();
        msg += "\nOrdered Quantity : "+quantity;
        msg += "\nTotal Price      : "+totalPrice;
        return msg;
    }
}
