import java.util.ArrayList;

/**
 * Maintains a class list of students (like Blackboard).
 */
public class ClassList
{
    private ArrayList<Student> list; 
    
    // Builds an empty list.
    public ClassList() { 
        list = new ArrayList<Student>();
    }
    
    // Adds a student. Maintains the list in alphabetical order.
    public void add(Student s) { 
        list.add(s);
        System.out.println(s.getName());
    }
    
    // Removes the student.
    public void drop(int id) {
        // Find the student with id "id"
        int index = findStudent(id);
        if (index != -1) {
            list.remove(index);
        }
    }  
        
    private int findStudent(int id) {
        for (int i =0; i < list.size(); i++) {
            if ( list.get(i).getID() == id ) {
                return i;
            }
        }
        return -1;
    }
    
    // Determines if the student is enrolled.
    public boolean isEnrolled(int id) { 
        return findStudent(id) != -1; 
    }
    
    // Returns the number of enrolled students.
    public int size() { return list.size(); }
    
    // Replaces the given student (by id), with new information.
    public void update(int id, Student s) { 
        int i = findStudent(id);
        if (i != -1) {
            list.set(i, s);
        }
    }
    
    // Tester method.
    public static void main(String[] args)
    {
        ClassList list = new ClassList();
        assert list.size() == 0 : "should be 0";

        list.add( new Student("abc", "first", 111) );
        list.add( new Student("def", "first", 123) );
        assert list.size() == 2 : "should be 2";
        
        list.drop(111);
        assert list.size() == 1 : "should be 1";
        
        list.drop(999); // doesn't exist
        assert list.size() == 1 : "should still be 1";
  
        assert list.isEnrolled(123) == true : "should be true";
        
        list.update(123, new Student("xyz", "first", 999) );
        assert list.size() == 1 : "should still be 1";
        assert list.isEnrolled(999) == true : "999 should exit";
        
    }
}
