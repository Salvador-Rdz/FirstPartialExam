/*

 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates

 * and open the template in the editor.

 */

package firstpartialexam;
import java.util.ArrayList;

/**

 *

 * @author Alumno

 */

public class Invoice {

    private String id;

    private Customer customer;

    private double amount;

    private InvoiceItem[] items;

    //private ArrayList itemListX;

    //Constructor

    public Invoice(String id, int customerID, String name, double discount)

    {

        this.id=id;

        customer=new Customer(customerID, name, discount); 

    }

    public Invoice(String id, Customer customer)

    {

        this.id=id;

        this.customer=customer;

        this.items=new InvoiceItem[10];

    }

    //Getters (Not much to explain here)

    public String getID()

    {

        return this.id;

    }

    public Customer getCustomer()

    {
        return this.customer;
    }

    public double getAmount()
    {
        return this.amount;
    }
    public double getAmountAfterDiscount()
    {
        return this.amount-(this.amount*customer.getDiscount()/100); //Changes the % to decimal values by dividing by 100
    }
    public String getCustomerName()

    {
        return this.customer.getName();
    }

    //Setters

    public void setCustomer(Customer customer)

    {

        this.customer=customer;

    }

    //Methods

    private void recalculateAmount() //Checks for the current values of the array of items

    {

        double sale=0;

        for(int i=0;i<items.length;i++)

        {

            if(items[i]!=null)      //Checks if its empty, to evade errors

            {

                sale+=items[i].getTotal();  //Adds to a variable the current sale so amount doesn't get modified until the end.

            }

        }

        amount=sale;                        //Then, it equals to the amount. 

    }

    public boolean addItem(InvoiceItem item) //Adds an item to an empty space in the array

    {

        for(int i=0;i<items.length;i++)     

        {

            if(items[i]==null)              //If the current array is empty, it proceeds to fill it. 

            {

                items[i]=item;

                recalculateAmount();

                return true;

            }

        }

        return false;

    }

    public boolean removeItem(String id)    //Searches for an item id within the array, then changes it to null

    {

        for(int i=0;i<items.length;i++)

        {

            if(items[i]!=null && id.equals(this.items[i].getID())); //Checks for nulls in case of errors, and then tries to equal the given id to the current position's id

            {

                this.items[i]=null;                                 //"Empties" the current level

                recalculateAmount();

                return true;

            }

        }

        return false;

    }

    public boolean removeItem(InvoiceItem item) //Searches for an item id in the item to compare it to the array, then changes it to null

    {

        for(int i=0;i<items.length;i++)

        {

            if(items[i]!=null && item.getID().equals(this.items[i].getID()));//Checks for nulls in case of errors, and then tries to equal the given id to the current position's id

            {

                this.items[i]=null;                                 //"Empties" the current level

                recalculateAmount();

                return true;

            }

        }

        return false;

    }   

    public void updateItem(String id, String description) //Searches for an item id in the item to compare it to the array, then changes its description

    {

        for(int i=0;i<items.length;i++)

        {

            if(items[i]!=null && id.equals(this.items[i].getID()));

            {

                this.items[i].setDescription(description);

            }

        }

    }

    public boolean updateItem(String id, int quantity)//Searches for an item id in the item to compare it to the array, then changes its stock

    {   

        for(int i=0;i<items.length;i++)

        {

            if(items[i]!=null && id.equals(this.items[i].getID()));

            {

                this.items[i].setQuantity(quantity);

                recalculateAmount();

                return true;

            }

        }

        return false;

    }

    public boolean updateItem(String id, double unitPrice)

    {

        for(int i=0;i<items.length;i++)

        {

            if(items[i]!=null && id.equals(this.items[i].getID()));

            {

                this.items[i].setUnitPrice(unitPrice);

                recalculateAmount();

                return true;

            }

        }

        return false;

    }

    public boolean updateItem(InvoiceItem item)

    {

        for(int i=0;i<items.length;i++)

        {

            if(items[i]!=null && item.getID().equals(this.items[i].getID()));

            {

                this.items[i].setID(item.getID());

                this.items[i].setDescription(item.getDescription());

                this.items[i].setQuantity(item.getQuantity());

                this.items[i].setUnitPrice(item.getUnitPrice());

                recalculateAmount();

                return true;

            }

        }

        return false;

    }

    @Override 

    public String toString()

    {

        String itemString="";

        for(int i=0;i<items.length;i++)

        {

            if(items[i]!=null)

            {

                itemString+=items[i].toString()+",";

            }

        }

        return "Invoice[id="+this.id+", "+this.customer.toString()+", Items{"+itemString+"}"

                +"line total:"+items.length+"]"+"gross amount="+this.amount+", discount amount"+

                (this.getAmount()*this.customer.getDiscount()/100)+", amount after discount="+this.getAmountAfterDiscount()+"]";

    }

    public boolean findItem(String id)

    {

        for(int i=0; i<items.length;i++)

        {

            if(items[i]!=null && items[i].getID().equals(id))

            {

                System.out.println("Item found");

                return true;

            }

        }

        System.out.println("Item not found");

        return false;

    }

} 
