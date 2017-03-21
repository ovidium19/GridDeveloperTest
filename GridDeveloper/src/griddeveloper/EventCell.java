/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package griddeveloper;

import java.util.ArrayList;

/**
 *
 * @author BOCU
 */
public class EventCell {
    private int xCord,yCord;
    private ArrayList<Event> events;
    private boolean visited;
    
    public EventCell(int x,int y){
        xCord=x;
        yCord=y;
        events=new ArrayList();
        visited=false;
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
