package foodorderingsystem;

import java.util.ArrayList;
import java.util.List;

public class ReportGenerator 
{
    //Complexity with O(n log n)
    public void mergeSort(List<Order> orders,int left,int right)
    {
        if(left<right)
        {
            int mid = left + (right - left)/2;
            
            mergeSort(orders,left,mid); //Recurrence calling of the mergeSort method
            mergeSort(orders,mid+1,right);
            
            merge(orders,left,mid,right);
            
        }
    }
    
    //Merging part of the list (Divide and Conquer)
    public static void merge(List<Order> orders,int left,int mid,int right)
    {
        //Initialize the sizes of the left and right arrays
        int leftNum = mid - left + 1;
        int rightNum = right - mid;
        
        //Initialize the left and right lists 
        List<Order> leftList = new ArrayList<>();
        List<Order> rightList = new ArrayList<>();
        
        //Add the existing elements for left and right lists separately
        for (int i = 0; i < leftNum; i++)
        {
            leftList.add(orders.get(left + i));
        }

        for (int j = 0; j < rightNum; j++)
        {
            rightList.add(orders.get(mid + 1 + j));
        }
        
        int i=0,j=0,k=left;
        
        //Sorting part according to the total cost of the orders
        while (i < leftNum && j < rightNum) 
        {
            if (leftList.get(i).totalCost <= rightList.get(j).totalCost) 
            {
                orders.set(k, leftList.get(i));
                i++;
            } 
            else 
            {
                orders.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        //Insert the remaining elements into the corresponding lists
        while (i < leftNum) 
        {
            orders.set(k, leftList.get(i));
            i++;
            k++;
        }

        while (j < rightNum) 
        {
            orders.set(k, rightList.get(j));
            j++;
            k++;
        }
    }
    
    //Complexity with O(n) for traverse through the list and O(1) for return the details
    public void generateReport(List<Order> orders)
    {
        for(Order order : orders)
        {
            System.out.println(order.getTotalSummary());
        }
    }
    
}


