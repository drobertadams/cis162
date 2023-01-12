
import java.util.ArrayList;

/**
 * Write a description of class PlantList here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PlantList
{
    private ArrayList<Plant> list;
    
    public PlantList() { list = new ArrayList<Plant>(); }
    
    public void add(Plant p) { list.add(p); }
    
    public void print() {
        for (Plant p : list) {
            System.out.println(p.getName()); // polymorphism
        }
    }
    
    public static void main(String[] a) {
        PlantList p = new PlantList();
        //p.print();
        
        Plant hosta = new Plant("hosta");
        p.add(hosta);
        //p.print();
        
        Plant tree = new Plant("oak");
        p.add(tree);
        p.print();
        
        Flower rose = new Flower("rose", "red");
        p.add(rose);
        p.print();
    }
}
