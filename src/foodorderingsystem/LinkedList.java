package foodorderingsystem;

public class LinkedList<T> 
{
    public Node<T> first;
    public Node<T> rear;
    int count=0;
    
    //Constructor to initialize the first and rear elements in the LinkedList
    public LinkedList()
    {
        this.first=null;
        this.rear=null;
    }
    
    //Complexity with O(1) 
    public void addNode(T value)
    {
        Node newNode = new Node(value);
        
        //Check whether the existing linkedList is empty or not
        if(isEmpty())
        {
            first=newNode;
        }
        else
        {
            rear.next=newNode;
        }
        rear=newNode;
        newNode.next=null;
        count++;
        System.out.println("Item Added Successfully");
    }
    
    //Complexity with O(n) when n is the number of elements in the list
    public void displayList()
    {
        Node<T> current = first;
        while(current != null)
        {
            System.out.print(current.getValue());
            current = current.next;
        }
    }
    
    //Complexity with O(1) but since we have find method there it takes O(n) as time complexity 
    public T removeItemByID(String key)
    {
        T found = findItemByID(key);
        
        if(found == null)
        {
            return null;
        }
        
        T deleted = null;
        Node<T> current = first;
        Node<T> previous = null;
        
        //Traverse through the list until the list is end
        while(current != null)
        {
            if(current.getValue().equals(found))
            {
                deleted = current.getValue();
                if(current == first)
                {
                    first = first.next;
                }
                else if(current == rear)
                {
                    rear = previous;
                    rear.next = null;
                }
                
                else
                {
                    previous.next = current.next;
                }
                count--;
                break;
            }
            previous = current;
            current = current.next;
        }
        return deleted;
        
    }
    
    //Complexity with O(n) as same as the 
    public T findItemByID(String key)
    {
        Node<T> current = first;
        T found = null;
        
        while(current != null)
        {
            if(current.getValue() instanceof MenuItem)
            {
                MenuItem item = (MenuItem) current.getValue(); //Cast the object to MenuItem Object
                //Check whether given key is equals to the existing item in the list
                if(item.getItemID().equalsIgnoreCase(key))
                {
                    found = current.getValue();
                }
            }
            current = current.next;
        }
        return found;
    }
    
    //Complexity with O(1)
    public boolean isEmpty()
    {
        return first==null;
    }
    
    
}
