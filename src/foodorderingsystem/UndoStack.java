package foodorderingsystem;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

public class UndoStack 
{
    public Deque<UndoAction> stack;
    
    public UndoStack()
    {
        this.stack = new ArrayDeque<>();
    }
    
    //Complexity with O(1)
    public void pushAction(UndoAction action)
    {
        stack.push(action);
    }
    
    //Complexity with O(1)
    public UndoAction popAction()
    {
        UndoAction lastAction = null;
        if(!stack.isEmpty())
        {
            lastAction = stack.pop();
        }
        return lastAction;
    }
    
    //Complexity with O(n)
    public void menuUndo(LinkedList<MenuItem> menuList) 
    {
        if (stack.isEmpty()) 
        {
            System.out.println("No actions to undo");
            return;
        }

        UndoAction action = popAction();

        switch (action.actionType) 
        {
            case "ADD_MENU_ITEM": 
            {
                MenuItem targetItem = (MenuItem) action.target;
                MenuItem removedItem = menuList.removeItemByID(targetItem.getItemID());
                if (removedItem != null) 
                {
                    System.out.println("Undo: Item removed from Menu Successfully");
                    stack.push(new UndoAction("REMOVE_MENU_ITEM", removedItem, null));
                } 
                else 
                {
                    System.out.println("Undo failed: Item not found in menu");
                }
                break;
            }

            case "REMOVE_MENU_ITEM": 
            {
                MenuItem targetItem = (MenuItem) action.target;
                menuList.addNode(targetItem);
                System.out.println("Undo: Menu Item restored Successfully");
                stack.push(new UndoAction("ADD_MENU_ITEM", targetItem, null)); 
                break;
            }
        }
    }
    
    //Complexity with O(n)
    public void orderUndo(Queue<Order> normalOrders,PriorityQueue<Order> priorOrders)
    {
        if(stack.isEmpty())
        {
            System.out.println("No Actions to undo");
            return;
        }
        
        //Get the last action of the stack
        UndoAction action = popAction();
        Order target = (Order) action.target;
        boolean isPremium = false;
        
        if(!(target == null))
        {
            isPremium = target.isPrior;
        }
        else
        {
            System.out.println("No action to Undo");
        }
        
        switch(action.actionType)
        {                
            case "PLACE_ORDER":
            {
                if(isPremium) 
                {
                    Iterator<Order> iterator = priorOrders.iterator();
                    while(iterator.hasNext()) 
                    {
                        if(iterator.next().orderID.equals(target.orderID)) 
                        {
                            iterator.remove();
                            break;
                        }
                    }
                    stack.push(new UndoAction("REMOVE_ORDER", target, null));
                        
                } 
                else 
                {
                    Iterator<Order> iterator = normalOrders.iterator();
                    while(iterator.hasNext()) 
                    {
                        if(iterator.next().orderID.equals(target.orderID)) 
                        {
                            iterator.remove();
                            break;
                        }
                    }
                    stack.push(new UndoAction("REMOVE_ORDER", target, null));
                }
                System.out.println("Order removed successfully");
                break;
            }
            
            case "REMOVE_ORDER":
            {
                if(isPremium)
                {
                    priorOrders.add(target);
                }
                else
                {
                    normalOrders.add(target);
                }
                stack.push(new UndoAction("PLACE_ORDER",target,null));
                System.out.println("Order restored successfully");
                break;
            }
            
        }
    }

        
}
