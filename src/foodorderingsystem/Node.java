package foodorderingsystem;

public class Node<T> 
{
    private T value;
    public Node<T> next;
    
    //Constructor to Initialize the value and pointer of the node
    public Node(T value)
    {
        setValue(value);
        this.next=null;
    }
    
    //Complexity with O(1) 
    public void setValue(T value)
    {
        this.value=value;
    }
    
    //Complexity with O(1)
    public T getValue()
    {
        return value;
    }
}
