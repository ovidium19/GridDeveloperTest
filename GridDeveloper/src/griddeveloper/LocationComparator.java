
package griddeveloper;

import java.util.Comparator;

/**
 *
 * @author Ovidiu Mitroi
 *      Class LocationComparator
 * 
 *          Describes how Events are compared to one another inside the maxHeap that holds the top 5 closest Events.
 *          In this case, Events are compared based on their Manhattan Distance to the parameter point, and they are 
 *          arranged in reverse order.
 *
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
