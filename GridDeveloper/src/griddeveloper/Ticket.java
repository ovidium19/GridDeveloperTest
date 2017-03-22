/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package griddeveloper;

/**
 *
 * @author mitroio
 */
public class Ticket {
    private static int count=0;
    private int id;
    private double price;
    
    public Ticket(double price){
        count+=1;
        id=count;
        this.price=price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String toString(){
        return "$"+String.valueOf(price);
    }
}
