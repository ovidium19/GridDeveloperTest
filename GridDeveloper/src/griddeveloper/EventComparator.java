/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package griddeveloper;

import java.util.Comparator;

/**
 *
 * @author BOCU
 */
public class EventComparator implements Comparator<Event> {
    private double xCord;
    public EventComparator(double x){
        xCord=x;
    }
    @Override
    public int compare(Event x, Event y){
        if (Math.abs(x.getxCord()-xCord)>Math.abs(y.getxCord()-xCord))
            return 1;
        else return -1;
    }
    
}
