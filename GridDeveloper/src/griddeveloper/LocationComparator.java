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
public class LocationComparator implements Comparator<Event> {
    private double xCord,yCord;
    
    public LocationComparator(double x,double y){
        xCord=x;
        yCord=y;
    }
    @Override
    public int compare(Event x,Event y){
        Double xManDist=Math.abs(x.getxCord()-xCord)+Math.abs(x.getyCord()-yCord);
        Double yManDist=Math.abs(y.getxCord()-xCord)+Math.abs(y.getyCord()-yCord);
        return -(xManDist.compareTo(yManDist));
    }
}
