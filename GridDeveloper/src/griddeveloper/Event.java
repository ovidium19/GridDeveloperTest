
package griddeveloper;

import java.util.PriorityQueue;
import java.util.Random;

/**
 *
 * @author Ovidiu Mitroi
 * Class Event ->
 *      properties:
 *          int count - static ->  counts the nr of instances of this class and assigns the number as the object's id
 *          double xCord,yCord -> the (x,y) coordinates of this Event
 *          id, name - attributes that describe the Event
 *          PriorityQueue tickets -> holds a list of Tickets, in a min heap data structure. The element
 *                                  at the front of the queue will always be the Ticket with the smallest price
 */
public class Event {
    private static int count=0;
    private static Random rm = new Random();
    private double xCord,yCord;
    private int id;
    private String name;
    private PriorityQueue<Ticket> tickets;
    
    static {
        rm.setSeed(System.currentTimeMillis());
    }
    
    private void generateTickets(){
        /*
        Generate 10 Tickets with random price in range of [0,100). Adds all tickets to the PriorityQueue
        */
        double price;
        int nrOfTickets;
        nrOfTickets=(int) Math.floor(rm.nextDouble()*10);
       
        if(nrOfTickets>0)
            for (int i=0;i<nrOfTickets;i++){
                price=rm.nextDouble()*100+0.01;
                tickets.add(new Ticket(price));
            }
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
        /*
        A useful method to quickly calculate the Manhattan distance from this event to a parameter point
        */
        return Math.abs(xCord-x)+Math.abs(yCord-y);
    }
    @Override
    public String toString(){
        if (tickets.size()>0)
            return "id: "+id+"\nname: "+name+"\nCheapest Ticket Price: "+tickets.peek()+"\nx: "+String.format("%.2f",xCord)+"\ny: "+String.format("%.2f",yCord);
        else
            return "id: "+id+"\nname: "+name+"\nCheapest Ticket Price: No Tickets Available"+"\nx: "+String.format("%.2f",xCord)+"\ny: "+String.format("%.2f",yCord);
    }
}
