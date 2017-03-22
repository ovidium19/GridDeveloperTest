/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package griddeveloper;

import java.text.MessageFormat;
import java.util.PriorityQueue;
import java.util.Scanner;


public class GridDeveloper {
    private static EventController eController;
    private static PriorityQueue<Event> events;
      
    public static void main(String[] args) {
        double x;
        double y;
        
        
        try (Scanner c = new Scanner(System.in)) {
            System.out.println("(x,y): ");
            String[] input=c.nextLine().split(",");
            x = Double.parseDouble(input[0]);
            y = Double.parseDouble(input[1]);
            eController=new EventController(x, y);
            eController.generateEvents(System.currentTimeMillis(), 500);
            /*events=new PriorityQueue<>(eController.getXClosest());
            while (!(events.isEmpty())){
                Event temp=events.poll();
                System.out.println(temp);
                System.out.println(temp.calcManDistance(x, y));
            }
            
            */
            eController.printEvents();
            System.out.println("Nr of Events checked: "+eController.findClosestEvents());
            events=new PriorityQueue<>(eController.getClosestEvents());
            System.out.println(MessageFormat.format("Closest Events to location {0},{1}",x,y));
            while (!(events.isEmpty())){
                Event temp=events.poll();
                System.out.println(temp);
                System.out.println(temp.calcManDistance(x, y));
            
            }
        }
        catch (Exception e){
            System.out.println(e);
            System.exit(0);
        }
        }
        
    }

