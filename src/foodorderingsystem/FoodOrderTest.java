package foodorderingsystem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class FoodOrderTest 
{
    public static void main(String[] args) 
    {
        LinkedList menuList = new LinkedList();
        OrderManager newOrder = new OrderManager();
        
        Queue<Order> normalOrderList = newOrder.getNormalOrders();
        PriorityQueue<Order> priorOrderList = newOrder.getPriorityOrders();
        List<Order> completedOrderList = new ArrayList<>();
        UndoStack actionStack = new UndoStack();
        
        int decision, decisionForMenu, decisionForOrder;
        Scanner mainScan = new Scanner(System.in);
        Scanner menuDecision = new Scanner(System.in);
        Scanner orderDecision = new Scanner(System.in);
        
        do
        {
            showTasks();
            System.out.print("Enter your choice : ");
            decision = mainScan.nextInt();
            mainScan.nextLine();
            
            switch(decision)
            {
                case 1:
                {
                    showMenuRelatedTasks();
                    System.out.print("Enter your decision : ");
                    decisionForMenu = menuDecision.nextInt();
                    menuDecision.nextLine();
                    
                    switch(decisionForMenu)
                    {
                        case 1:
                        {
                            createMenu(menuList,actionStack);
                            break;
                        }
                        
                        case 2:
                        {
                            findByID(menuList);
                            break;
                        }
                        
                        case 3:
                        {
                            removeByID(menuList,actionStack);
                            break;
                        }
                        
                        case 4:
                        {
                            displayMenu(menuList);
                            break;
                        }
                        
                        case 5:
                        {
                            undoMenuTask(menuList,actionStack);
                            break;
                        }
                    }
                    break;
                }
                
                case 2:
                {
                    showOrderRelatedTasks();
                    System.out.print("Enter your decision : ");
                    decisionForOrder = orderDecision.nextInt();
                    orderDecision.nextLine();
                    
                    switch(decisionForOrder)
                    {
                        case 1:
                        {
                            makeOrder(newOrder,menuList,actionStack);
                            break;
                        }
                        
                        case 2:
                        {
                            cancelOrder(newOrder,actionStack);
                            break;
                        }
                        case 3:
                        {
                            getServingOrder(newOrder,completedOrderList);
                            break;
                        }
                        
                        case 4:
                        {
                            getNextOrder(newOrder);
                            break;
                        }
                        
                        case 5:
                        {
                            displayOrderList(newOrder,normalOrderList,priorOrderList);
                            break;
                        }
                        
                        case 6:
                        {
                            undoOrderTasks(normalOrderList,priorOrderList,actionStack);
                            break;
                        }
                            
                    }
                    break;
                }
                
                case 3:
                {
                    generateReport(completedOrderList);
                    break;
                }
                
                
                
                case 4:
                {
                    System.out.println("Exiting...");
                    System.out.println("Thanks for using the Online food Ordering platform");
                }
                
                
            }
        }
        while(decision != 4);
        
        
        
    }
    
    public static void showTasks()
    {
        System.out.println("1. Update the menu (Add item,Remove Item,Display Menu) ");
        System.out.println("2. Order Related Tasks (Place/Cancel an Order,View Current serving order, View next Order");
        System.out.println("3. Generate Report");
        System.out.println("4. Exit");
    }
    
    public static void showMenuRelatedTasks()
    {
        System.out.println("1. Add Items to the menu");
        System.out.println("2. Find Item from the menu by ID");
        System.out.println("3. Remove Item from the menu by ID");
        System.out.println("4. Display the full menu");
        System.out.println("5. Undo the last Action related to menu");
    }
    
    public static void showOrderRelatedTasks()
    {
        System.out.println("1. Place an Order");
        System.out.println("2. Cancel an Order by ID");
        System.out.println("3. Get the Serving Order");
        System.out.println("4. Get the next Order to be served");
        System.out.println("5. Display full awaiting Orders");
        System.out.println("6. Undo the last Action related to order");
    }
    
    public static void createMenu(LinkedList menuList,UndoStack menuStackList)
    {
        char choice='Y';
        do
        {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter the Item ID : ");
            String itemID = scan.nextLine();
            System.out.print("Enter the Item Name : ");
            String itemName = scan.nextLine();
            System.out.print("Enter the Item Type : ");
            String type = scan.nextLine();
            System.out.print("Enter the Item Price : ");
            double itemPrice = scan.nextDouble();
            scan.nextLine();
            
            MenuItem item = new MenuItem(itemID,itemName,type,itemPrice);
            
            menuList.addNode(item);
            menuStackList.pushAction(new UndoAction("ADD_MENU_ITEM",item,null));
            
            System.out.print("Do you need to add another? (Y/N) : ");
            choice = scan.next().charAt(0);
            scan.nextLine();
        }
        while(choice != 'N' && choice !='n');
    }
    
    public static void removeByID(LinkedList menuList,UndoStack menuStackList)
    {
        String deleted = null;
       
        Scanner scanID = new Scanner(System.in);
        System.out.println("Enter the ID needed to be deleted : ");
        String itemID = scanID.nextLine();
        
        MenuItem item = (MenuItem) menuList.removeItemByID(itemID);
        
        if(item != null)
        {
            deleted = itemID;
            System.out.println("The item with ID "+deleted+" is deleted successfully");
            menuStackList.pushAction(new UndoAction("REMOVE_MENU_ITEM",item,null));
        }
    }
    
    public static void findByID(LinkedList menuList)
    {
        Scanner idScan = new Scanner(System.in);
        System.out.println("Enter the ID needed to be found : ");
        String id = idScan.nextLine();
        
        String found = null;
        
        if(menuList.findItemByID(id) != null)
        {
            found = id;
            System.out.println("The item with ID "+found+" is found successfully");
            System.out.println(menuList.findItemByID(id));
        }
    }
    
    public static void undoMenuTask(LinkedList<MenuItem> menuList, UndoStack menuStackList)
    {
        menuStackList.menuUndo(menuList);
    }
    
    public static void displayMenu(LinkedList menuList)
    {
        System.out.printf("%-10s %-25s %-15s %s\n", "Item ID", "Item Name", "Type", "Item Price");
        System.out.println("---------------------------------------------------------------");

        menuList.displayList();
    }
    
    public static void getOrderDetails(LinkedList menuList)
    {
        Scanner orderScan = new Scanner(System.in);
        System.out.println("Enter the Item ID needed to be ordered : ");
        String id = orderScan.nextLine();
        System.out.println("Enter the quantity of the ordered item : ");
        int quantity = orderScan.nextInt();
        orderScan.nextLine();
        
        OrderItem item = new OrderItem(menuList,id,quantity);
        
        System.out.println("Item : "+item.item);
        System.out.println("Quantity : "+item.quantity);
        System.out.println("Total Price : "+item.totalPrice);
    }
    
    public static void makeOrder(OrderManager newOrder,LinkedList menuList,UndoStack menuStackList)
    {
        char decision = 'Y';
        Scanner decisionScan = new Scanner(System.in);
        
        while(decision != 'N' && decision != 'n')
        {
            System.out.print("Enter Order ID : ");
            String orderId = decisionScan.nextLine();
            System.out.print("Enter Customer ID : ");
            String customerId = decisionScan.nextLine();
            System.out.print("Is a priority order(true/false) : ");
            boolean isPrior = decisionScan.nextBoolean();
            decisionScan.nextLine();
            
            Order order = new Order(orderId,customerId,isPrior);
            
            char choice;
            do 
            {
                System.out.print("Enter Menu Item ID: ");
                String itemID = decisionScan.next();

                System.out.print("Enter Quantity: ");
                int quantity = decisionScan.nextInt();

                order.addItem(quantity, menuList, itemID);
                menuStackList.pushAction(new UndoAction("PLACE_ORDER",order,null));

                System.out.print("Do you need to add another item (Y/N): ");
                choice = decisionScan.next().charAt(0);
                decisionScan.nextLine();

            } 
            while (choice == 'Y' || choice == 'y');
            
            newOrder.placeOrder(order);
            
            System.out.println("Do you need to add another (Y/N) : ");
            
            decision = decisionScan.nextLine().charAt(0);
            
            
        }
    }
    
    public static void cancelOrder(OrderManager newOrder,UndoStack menuStackList)
    {
        Scanner orderIdScan = new Scanner(System.in);
        System.out.println("Enter the Order ID needed to be cancelled : ");
        String key = orderIdScan.nextLine();
        
        Order deleted = newOrder.cancelOrder(key);
        menuStackList.pushAction(new UndoAction("REMOVE_ORDER",deleted,null));
        
        System.out.println("Order "+deleted.orderID+" cancelled successfully");
    }
    
    public static void getServingOrder(OrderManager newOrder,List<Order> completedOrders)
    {
        Order serving = newOrder.getServingOrder();
        if(!(serving == null))
        {
            completedOrders.add(serving);
            System.out.println("Serving : "+serving.getTotalSummary());
        }
        else
        {
            System.out.println("No order is serving");
        }
    }
    
    public static void getNextOrder(OrderManager newOrder)
    {
        Order next = newOrder.getNextOrder();
        if(!(next == null))
        {
            System.out.println("Next to Serve : "+next.getTotalSummary());
        }
        else
        {
            System.out.println("No more orders to serve");
        }
    }
    
    public static void displayOrderList(OrderManager newOrder,Queue<Order> normalOrderList,PriorityQueue<Order> priorOrderList)
    {
        normalOrderList = newOrder.getNormalOrders();
        priorOrderList = newOrder.getPriorityOrders();
        
        for(Order priorOrder : priorOrderList)
        {
            System.out.println(priorOrder.getTotalSummary());
        }
        
        for(Order normalOrder : normalOrderList)
        {
            System.out.println(normalOrder.getTotalSummary());
        }
    }
    
    public static void undoOrderTasks(Queue<Order> normalOrderList,PriorityQueue<Order> priorOrderList,UndoStack menuStackList)
    {
        menuStackList.orderUndo(normalOrderList, priorOrderList);
    }
    
    public static void generateReport(List<Order> completedOrders)
    {
        ReportGenerator newReport = new ReportGenerator();
        
        newReport.mergeSort(completedOrders, 0, (completedOrders.size()-1));
        
        newReport.generateReport(completedOrders);
    }
    
    

}

