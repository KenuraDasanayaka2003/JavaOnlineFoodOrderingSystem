package foodorderingsystem;

public class Node<T> 
{
    private T value;
    public Node<T> next;
    
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
