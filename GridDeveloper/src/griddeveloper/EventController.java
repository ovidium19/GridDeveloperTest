
package griddeveloper;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;

/**
 *
 * @author Ovidiu Mitroi
 *          Class EventController
 *              To instantiate this class, you need to provide the coordinates of a location
 * 
 *              Properties:
 *              1.    manDistFarthest -> A maxHeap with a LocationComparator. This structure will hold the closest 5 Events.
 *              2.    eventQueue -> A regular queue that holds the sequence of EventCells in the order that we are going
 *                                to visit them when searching for the closest Events.
 *                  **NOTE** : it is a LinkedList, however in Java, the LinkedList class implements the Queue class and 
 *                             therefore can be used as a regular queue
 *             3.     eventGrid -> a 20x20 bidimensional array of EventCells.
 *             4.     xLocReal,yLocReal -> the (double) coordinates of the customer location
 *             5.     xLoc,yLoc         -> the coordinates of the EventCell where the customer starts from.
 */
public class EventController {
    
    private LocationComparator manDistCompare;
    private EventCell[][] eventGrid;
    private LinkedList<EventCell> eventQueue;
    private PriorityQueue<Event> manDistFarthest;
    private int xLoc,yLoc;
    private double xLocReal,yLocReal;
    private Random rm;

    
    public EventController(double x,double y){
        xLocReal=x;
        yLocReal=y;
        /*
        the [-10,10) plane is represented as a grid from 0 to 20. the point (0,0) will be in cell (10,10)
        */
        xLoc=(int)Math.floor(x)+10; 
        yLoc=(int)Math.floor(y)+10;
        //-------------------------------------------------------------------------
        manDistCompare=new LocationComparator(xLocReal, yLocReal);
        manDistFarthest=new PriorityQueue<>(manDistCompare);
        rm=new Random();
        eventQueue=new LinkedList<>();
        eventGrid=new EventCell[20][20];
        for (int i=0;i<20;i++)
            for (int j=0;j<20;j++)
                eventGrid[i][j]=new EventCell(i,j);
    }
    
    public void printEvents(){
        for (int i=0;i<20;i++)
            for (int j=0;j<20;j++)
                eventGrid[i][j].printEvents(xLocReal,yLocReal);
    }

    public void generateEvents(long seed,int numberOfEvents){
        /*
        Generate [numberOfEvents] random Events and store them in the grid. 
        */
        double x,y;
        String name;
        rm.setSeed(seed);
        for (int i=0;i<numberOfEvents;i++){
            x=rm.nextDouble()*20-10;
            y=rm.nextDouble()*20-10;
            name="Event "+String.valueOf(i+1);
            eventGrid[(int)Math.floor(x)+10][(int)Math.floor(y)+10].addEvent(x, y, name);
        }
    }
   
    public int findClosestEvents(){
        /*
        Finds the closest Events to the customer location for this instance. Returns the number of Events checked.
        
        Steps:
            1. Start from the cell where the customer's location fits.
            2. Add it to the eventQueue
            3. At each iteration, we pop the queue, add all the neighbouring cells to the queue, search all the events
               in the current cell and compare them to our maxHeap of closest events.
            4. We stop when we have 5 Events in the maxHeap and the layer of the current cell is higher than the peek 
               value of our maxHeap
        */
        int count=0;
        int xTemp,yTemp;
        //STEP 1+2
        eventQueue.add(eventGrid[xLoc][yLoc]);
        eventQueue.get(0).setVisited(true);
        eventQueue.get(0).setLayer(-1);
        manDistFarthest.add(new Event(100.0,100.0,"")); //add a random Event with max distance to avoid NullPointerException in the condition below
        //--------------------------------------------------------
        //STEP 3
        //Stop Condition explained above
        while(!((manDistFarthest.size()==5) && 
                (eventQueue.peek().getLayer()>manDistFarthest.peek().calcManDistance(xLocReal, yLocReal)))){
            
            //Pop the queue
            EventCell tempCell = eventQueue.poll();
            
            if (tempCell==null) break;
            xTemp=tempCell.getxCord();
            yTemp=tempCell.getyCord();
            //add all the neighbouring cells to the queue, set them to visited and calculate their layer value
            if (xTemp>=1 && !(eventGrid[xTemp-1][yTemp].isVisited())){eventQueue.add(eventGrid[xTemp-1][yTemp]);
                                                                      eventGrid[xTemp-1][yTemp].setVisited(true);
                                                                      eventGrid[xTemp-1][yTemp].setLayer(tempCell.getLayer()+1);
            }
            if (xTemp<19 && !(eventGrid[xTemp+1][yTemp].isVisited())){eventQueue.add(eventGrid[xTemp+1][yTemp]);
                                                                       eventGrid[xTemp+1][yTemp].setVisited(true);
                                                                       eventGrid[xTemp+1][yTemp].setLayer(tempCell.getLayer()+1);
            }
            if (yTemp>=1 && !(eventGrid[xTemp][yTemp-1].isVisited())){eventQueue.add(eventGrid[xTemp][yTemp-1]);
                                                                      eventGrid[xTemp][yTemp-1].setVisited(true);
                                                                      eventGrid[xTemp][yTemp-1].setLayer(tempCell.getLayer()+1);
            }
            if (yTemp<19 && !(eventGrid[xTemp][yTemp+1].isVisited())){eventQueue.add(eventGrid[xTemp][yTemp+1]);
                                                                      eventGrid[xTemp][yTemp+1].setVisited(true);
                                                                      eventGrid[xTemp][yTemp+1].setLayer(tempCell.getLayer()+1);
            }
            if(xTemp>=1 && yTemp>=1 && !(eventGrid[xTemp-1][yTemp-1].isVisited())){eventQueue.add(eventGrid[xTemp-1][yTemp-1]);
                                                                                   eventGrid[xTemp-1][yTemp-1].setVisited(true);
                                                                                   eventGrid[xTemp-1][yTemp-1].setLayer(tempCell.getLayer()+1);
            }
            if(xTemp<19 && yTemp<19 && !(eventGrid[xTemp+1][yTemp+1].isVisited())){eventQueue.add(eventGrid[xTemp+1][yTemp+1]);
                                                                                   eventGrid[xTemp+1][yTemp+1].setVisited(true);
                                                                                   eventGrid[xTemp+1][yTemp+1].setLayer(tempCell.getLayer()+1);
            }
            if(xTemp>=1 && yTemp<19 && !(eventGrid[xTemp-1][yTemp+1].isVisited())){eventQueue.add(eventGrid[xTemp-1][yTemp+1]);
                                                                                   eventGrid[xTemp-1][yTemp-1].setVisited(true);
                                                                                   eventGrid[xTemp-1][yTemp-1].setLayer(tempCell.getLayer()+1);
            }
            if(xTemp<19 && yTemp>=1 && !(eventGrid[xTemp+1][yTemp-1].isVisited())){eventQueue.add(eventGrid[xTemp+1][yTemp-1]);
                                                                                   eventGrid[xTemp+1][yTemp-1].setVisited(true);
                                                                                   eventGrid[xTemp+1][yTemp-1].setLayer(tempCell.getLayer()+1);
            }
            //-----------------------------------------------
            //Check all events in the current cell and add the eligible ones to the PriorityQueue
            ArrayList<Event> tempList = tempCell.getEvents();
            for (int i=0;i<tempList.size();i++){
                count+=1;
                if (manDistFarthest.size()<5) manDistFarthest.add(tempList.get(i));
                else
                    if (tempList.get(i).calcManDistance(xLocReal, yLocReal)<
                            manDistFarthest.peek().calcManDistance(xLocReal, yLocReal)){
                        manDistFarthest.poll();
                        manDistFarthest.add(tempList.get(i));
                    }
                
            }
        }
        return count; //number of Events checked
    }
    public PriorityQueue<Event> getClosestEvents(){
        return manDistFarthest;
    }
   
    public EventCell getEventsAtLocation(int x,int y){
        return eventGrid[x][y];
    }
}
