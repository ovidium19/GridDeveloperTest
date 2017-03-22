
package griddeveloper;

/**
 *
 * @author Ovidiu Mitroi
 *      Class Ticket ->
 *          Properties:
 *              double price
 *              int id
 *              a static count that counts the nr of instances of the class and assigns that number to each new instance
 *              as its id
 */
public class Ticket {
    private static int count=0;
    private int id;
    private double price;
    
    public Ticket(double price){
        count+=1;
        id=count;
        this.price=price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String toString(){
        return "$"+String.format("%.2f",price);
    }
    public static int getCount(){
        return count;
    }
}
