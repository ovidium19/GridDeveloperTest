/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package griddeveloper;

import java.util.Comparator;

/**
 *
 * @author mitroio
 */
public class TicketComparator implements Comparator<Ticket> {

    @Override
    public int compare(Ticket t1, Ticket t2) {
        if (t1.getPrice()<t2.getPrice()) return -1;
        else if (t1.getPrice()>t2.getPrice()) return 1;
        return 0;
    }
    
    
}
