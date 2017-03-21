/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package griddeveloper;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author BOCU
 */
public class EventController {
    //private EventComparator xAxisCompare;
    private LocationComparator manDistCompare;
    private EventCell[][] eventGrid;
    private LinkedList<EventCell> eventQueue;
    //private PriorityQueue<Event> xClosest;
    private PriorityQueue<Event> manDistFarthest;
    private int xLoc,yLoc;
    private double xLocReal,yLocReal;
    private Random rm;

    
    public EventController(double x,double y){
        xLocReal=x;
        yLocReal=y;
        xLoc=(int)Math.floor(x)+10;
        yLoc=(int)Math.floor(y)+10;
        //eventTree=new BSTEvents();
        //xAxisCompare=new EventComparator(xLoc);
        manDistCompare=new LocationComparator(xLoc, yLoc);
        //xClosest=new PriorityQueue<>(xAxisCompare);
        manDistFarthest=new PriorityQueue<>(manDistCompare);
        rm=new Random();
        eventQueue=new LinkedList<>();
        eventGrid=new EventCell[20][20];
        for (int i=0;i<20;i++)
            for (int j=0;j<20;j++)
                eventGrid[i][j]=new EventCell(i,j);
            
        
    }
    /*
    public PriorityQueue<Event> getXClosest() {
        return xClosest;
    }
    */
    public void printEvents(){
        for (int i=0;i<20;i++)
            for (int j=0;j<20;j++)
                eventGrid[i][j].printEvents(xLocReal,yLocReal);
    }

    public void generateEvents(long seed,int numberOfEvents){
        double x,y;
        String name;
        rm.setSeed(seed);
        for (int i=0;i<numberOfEvents;i++){
            x=rm.nextDouble()*20-10;
            y=rm.nextDouble()*20-10;
            name="Event "+String.valueOf(i+1);
            eventGrid[(int)Math.floor(x)+10][(int)Math.floor(y)+10].addEvent(x, y, name);
            //xClosest.add(new Event(x,y,name));
            //eventTree.insert(new Event(x,y,name));
        }
    }
   
    public void findClosestEvents(){
        int xTemp,yTemp;
        eventQueue.add(eventGrid[xLoc][yLoc]);
        eventQueue.get(0).setVisited(true);
        manDistFarthest.add(new Event(100.0,100.0,""));
        while(!((manDistFarthest.size()==5) && 
                (eventQueue.peek().calcDistance(xLoc, yLoc))>manDistFarthest.peek().calcManDistance(xLocReal, yLocReal)+1)){
            EventCell tempCell = eventQueue.poll();
            if (tempCell==null) break;
            xTemp=tempCell.getxCord();
            yTemp=tempCell.getyCord();
            //add all the neighbouring cells to the queue
            if (xTemp>=1 && !(eventGrid[xTemp-1][yTemp].isVisited())){eventQueue.add(eventGrid[xTemp-1][yTemp]);
                                                                      eventGrid[xTemp-1][yTemp].setVisited(true);
            }
            if (xTemp<19 && !(eventGrid[xTemp+1][yTemp].isVisited())){eventQueue.add(eventGrid[xTemp+1][yTemp]);
                                                                       eventGrid[xTemp+1][yTemp].setVisited(true);
            }
            if (yTemp>=1 && !(eventGrid[xTemp][yTemp-1].isVisited())){eventQueue.add(eventGrid[xTemp][yTemp-1]);
                                                                      eventGrid[xTemp][yTemp-1].setVisited(true);
            }
            if (yTemp<19 && !(eventGrid[xTemp][yTemp+1].isVisited())){eventQueue.add(eventGrid[xTemp][yTemp+1]);
                                                                      eventGrid[xTemp][yTemp+1].setVisited(true);
            }
            //-----------------------------------------------
            //Check all events in the current cell and add the eligible ones to the PriorityQueue
            ArrayList<Event> tempList = tempCell.getEvents();
            for (int i=0;i<tempList.size();i++){
                
                if (manDistFarthest.size()<5) manDistFarthest.add(tempList.get(i));
                else
                    if (tempList.get(i).calcManDistance(xLocReal, yLocReal)<
                            manDistFarthest.peek().calcManDistance(xLocReal, yLocReal)){
                        manDistFarthest.poll();
                        manDistFarthest.add(tempList.get(i));
                    }
                
            }
        }
        
    }
    public PriorityQueue<Event> getClosestEvents(){
        return manDistFarthest;
    }
    /*
    public void printTree(){
        eventTree.inOrder(eventTree.getRoot());
    }
*/
    public EventCell getEventsAtLocation(int x,int y){
        return eventGrid[x][y];
    }
}
