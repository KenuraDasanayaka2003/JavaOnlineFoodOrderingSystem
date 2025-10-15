package foodorderingsystem;

public class UndoAction 
{
    public String actionType;
    public Object target;
    public Object backUp;
    
    //Constructor to initialize the fields of UndoAction
    public UndoAction(String actionType,Object target,Object backUp)
    {
        this.actionType = actionType;
        this.target = target;
        this.backUp = backUp;
    }
}
