/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package griddeveloper;

/**
 *
 * @author BOCU
 */
public class Event {
    private double xCord,yCord;
    private String name;
    
    public Event(double x,double y,String rname){
        xCord=x;
        yCord=y;
        name=rname;
    }

    public double getxCord() {
        return xCord;
    }

    public void setxCord(Float xCord) {
        this.xCord = xCord;
    }

    public double getyCord() {
        return yCord;
    }

    public void setyCord(Float yCord) {
        this.yCord = yCord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public double calcManDistance(double x,double y){
        return Math.abs(xCord-x)+Math.abs(yCord-y);
    }
    @Override
    public String toString(){
        return "name: "+name+"\nx: "+String.format("%.2f",xCord)+"\ny: "+String.format("%.2f",yCord);
    }
}
