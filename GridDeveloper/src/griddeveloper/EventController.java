/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package griddeveloper;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 *
 * @author BOCU
 */
public class EventController {
    private EventComparator xAxisCompare;
    private LocationComparator manDistCompare;
    private PriorityQueue<Event> xClosest;
    private PriorityQueue<Event> manDistFarthest;
    private double xLoc,yLoc;
    private Random rm;

    
    public EventController(double x,double y){
        xLoc=x;
        yLoc=y;
        xAxisCompare=new EventComparator(xLoc);
        manDistCompare=new LocationComparator(xLoc, yLoc);
        xClosest=new PriorityQueue<>(xAxisCompare);
        manDistFarthest=new PriorityQueue<>(manDistCompare);
        rm=new Random();
    }
    public PriorityQueue<Event> getXClosest() {
        return xClosest;
    }

    public void generateEvents(long seed,int numberOfEvents){
        double x,y;
        String name;
        rm.setSeed(seed);
        for (int i=0;i<numberOfEvents;i++){
            x=rm.nextDouble()*20-10;
            y=rm.nextDouble()*20-10;
            name="Event "+String.valueOf(i+1);
            xClosest.add(new Event(x,y,name));
        }
    }
   
    public void findClosestEvents(){
        int steps=1;
        PriorityQueue<Event> pqTemp=new PriorityQueue<>(xClosest);
        manDistFarthest.add(pqTemp.poll());
        while (!(pqTemp.isEmpty())){
            steps+=1;
            if (manDistFarthest.size()<5)
                manDistFarthest.add(pqTemp.poll());
            
            else if (Math.abs(pqTemp.peek().getxCord())>manDistFarthest.peek().calcManDistance(xLoc, yLoc))
                break;
            else
                if (pqTemp.peek().calcManDistance(xLoc, yLoc)<=manDistFarthest.peek().calcManDistance(xLoc, yLoc)){
                    manDistFarthest.poll(); 
                manDistFarthest.add(pqTemp.poll());
            }
                else pqTemp.poll();
        }
        System.out.println(MessageFormat.format("Checked {0} events out of {1}.",steps,xClosest.size()));
        
    }
    public PriorityQueue<Event> getClosestEvents(){
        return manDistFarthest;
    }
}
