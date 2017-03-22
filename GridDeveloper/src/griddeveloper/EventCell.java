
package griddeveloper;

import java.util.ArrayList;

/**
 *
 * @author Ovidiu Mitroi
 * 
 * Class EventCell ->
 *      Provides a description of what a cell inside our grid contains.
 *      
 *      Properties:
 *         int xCord,yCord -> the coordinates of this cell on the grid
 *         ArrayList<Event> events -> a list containing all the Events in this cell. 
 *                      The Events contained in a cell have coordinates of x in range [xCord,xCord+1)
 *                                                                         y in range [yCord,yCord+1)
 *         boolean visited -> a handy boolean through which we track if we have visited this cell when finding
 *                            the closest events.
 * 
 *         int layer -> an integer which tracks of which layer this cell belongs to.
 *                      The layer of the cell describes the minimum manhattan distance between this cell and 
 *                      the cell containing our person location. 
 *                      By default, the cell containing the person location has layer -1, its neighbouring cells
 *                      have layer 0, the neighbouring cells of cells with layer 0 have layer 1 and so on. 
 */
public class EventCell {
    private int xCord,yCord;
    private ArrayList<Event> events;
    private boolean visited;
    private int layer;
    
    public EventCell(int x,int y){
        xCord=x;
        yCord=y;
        events=new ArrayList();
        visited=false;
        layer=-2; // -2 is a non value in our case since we only care about values from -1 onwards.
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }
    
    public void addEvent(double x,double y, String rName){
        events.add(new Event(x,y,rName));
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    public int calcDistance(int x,int y){
        return Math.abs(xCord-x)+Math.abs(yCord-y);
    }

    public int getxCord() {
        return xCord;
    }

    public void setxCord(int rxCord) {
        xCord = rxCord;
    }

    public int getyCord() {
        return yCord;
    }

    public void setyCord(int ryCord) {
        yCord = ryCord;
    }
    
    public void printEvents(double x,double y){
        for (int i=0;i<events.size();i++){
            System.out.println(events.get(i));
            System.out.println(events.get(i).calcManDistance(x, y));
        }
    }
    
}
