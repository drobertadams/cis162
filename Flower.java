
/**
 * Write a description of class Flower here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Flower extends Plant
{
    private String color; // in addition "name"
    
   public Flower(String name, String color) {
       super(name); // call Plant's constructor
       this.color = color;
   }
   
   public String getName() { 
       return "a " + color + " " + super.getName(); // call Plant's getName()
    }
}
