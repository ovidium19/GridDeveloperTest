/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package griddeveloper;

import java.util.PriorityQueue;
import java.util.Random;

/**
 *
 * @author BOCU
 */
public class Event {
    private static int count=0;
    private static Random rm = new Random();
    private double xCord,yCord;
    private int id;
    private String name;
    private PriorityQueue<Ticket> tickets;
    
    private void generateTickets(){
        double price;
        rm.setSeed(System.currentTimeMillis());
        for (int i=0;i<10;i++){
            price=rm.nextDouble()*100;
            this.tickets.add(new Ticket(price));}
    }
    public Event(double x,double y,String rname){
        count+=1;
        id=count;
        xCord=x;
        yCord=y;
        name=rname;
        tickets=new PriorityQueue<>(5, new TicketComparator());
        generateTickets();
    }

    public double getxCord() {
        return xCord;
    }

    public void setxCord(Float xCord) {
        this.xCord = xCord;
    }

    public double getyCord() {
        return yCord;
    }

    public void setyCord(Float yCord) {
        this.yCord = yCord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PriorityQueue<Ticket> getTickets() {
        return tickets;
    }
    
    
    
    public double calcManDistance(double x,double y){
        return Math.abs(xCord-x)+Math.abs(yCord-y);
    }
    @Override
    public String toString(){
        return "id: "+id+"\nname: "+name+"\nCheapest Ticket Price: "+tickets.peek()+"\nx: "+String.format("%.2f",xCord)+"\ny: "+String.format("%.2f",yCord);
    }
}
