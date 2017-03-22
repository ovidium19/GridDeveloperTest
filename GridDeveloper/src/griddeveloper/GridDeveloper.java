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
    //contains the main method
    private static EventController eController;
    private static PriorityQueue<Event> events;
      
    public static void main(String[] args) {
        double x;
        double y;
        
        //Read the customer location from the keyboard
        try (Scanner c = new Scanner(System.in)) {
            System.out.println("(x,y): ");
            String[] input=c.nextLine().split(",");
            x = Double.parseDouble(input[0]);
            y = Double.parseDouble(input[1]);
            eController=new EventController(x, y);
            /*
            Generate random events
            Edit 1st parameter to set up a constant seed
            Edit 2nd parameter to alter the nr of Events created
            */
            eController.generateEvents(System.currentTimeMillis(), 20);
            
            //print all events created -- comment this out if not needed
            eController.printEvents();
            //----------------------------------------------
            //call the method to find closest events and print how many events were checked
            System.out.println("Nr of Events checked: "+eController.findClosestEvents());
            
            
            //print the found events
            events=new PriorityQueue<>(eController.getClosestEvents());
            System.out.println(MessageFormat.format("Closest Events to location {0},{1}",x,y));
            System.out.println("--------------------------------------------------------");
            while (!(events.isEmpty())){
                Event temp=events.poll();
                System.out.println(temp);
                System.out.println(temp.calcManDistance(x, y));
                System.out.println("--------------------------------");
            
            }
            System.out.println("nr of tickets generated: "+Ticket.getCount());
        }
        catch (Exception e){
            System.out.println(e);
            System.exit(0);
        }
        }
        
    }

