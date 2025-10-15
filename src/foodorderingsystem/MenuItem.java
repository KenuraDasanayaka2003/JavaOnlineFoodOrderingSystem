package foodorderingsystem;

public class MenuItem 
{
    private String itemID;
    private String itemName;
    private String type;
    private double itemPrice;
    
    //Constructor to the initialize the MenuItem fields
    public MenuItem(String itemID,String itemName,String type,double itemPrice)
    {
        setItemID(itemID);
        setItemName(itemName);
        setType(type);
        setItemPrice(itemPrice);
    }
    
    //Complexity with O(1)
    public void setItemID(String itemID)
    {
        this.itemID=itemID;
    }
    
    //Complexity with O(1)
    public void setItemName(String itemName)
    {
        this.itemName=itemName;
    }
    
    //Complexity with O(1)
    public void setType(String type)
    {
        this.type=type;
    }
    
    //Complexity with O(1) 
    public void setItemPrice(double itemPrice)
    {
        this.itemPrice=itemPrice;
    }

    //Complexity with O(1) 
    public String getItemID() 
    {
        return itemID;
    }

    //Complexity with O(1)
    public String getItemName() 
    {
        return itemName;
    }

    //Complexity with O(1) 
    public String getType() 
    {
        return type;
    }

    //Complexity with O(1)
    public double getItemPrice() 
    {
        return itemPrice;
    }
    
    @Override
    public String toString()
    {
        
        return String.format("%-10s |%-25s |%-15s |%.2f\n",
                         getItemID(),
                         getItemName(),
                         getType(),
                         getItemPrice());


    }
    
    
}
