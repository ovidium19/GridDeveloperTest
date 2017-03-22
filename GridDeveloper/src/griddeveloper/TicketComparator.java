
package griddeveloper;

import java.util.Comparator;

/**
 *
 * @author Ovidiu Mitroi
 *          Class TicketComparator
 *                  Describes how Tickets are compared to one another inside the minHeap.
 *                  In this case, they are compared based on the price of the ticket.
 */
public class TicketComparator implements Comparator<Ticket> {

    @Override
    public int compare(Ticket t1, Ticket t2) {
        if (t1.getPrice()<t2.getPrice()) return -1;
        else if (t1.getPrice()>t2.getPrice()) return 1;
        return 0;
    }
    
    
}
