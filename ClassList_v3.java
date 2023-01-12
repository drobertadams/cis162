import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Maintains a class list of students (like Blackboard).
 * This version uses custom exceptions to handle errors.
 */
public class ClassList_v3
{
    private ArrayList<Student> list; 
    
    // Builds an empty list.
    public ClassList_v3() { 
        list = new ArrayList<Student>();
    }
    
    // Adds a student. Maintains the list in alphabetical order.
    public void add(Student s) throws InvalidStudentException { 
        if (s == null) {
            throw new InvalidStudentException("can't add null");
        }
        list.add(s);
        System.out.println(s.getName());
    }
    
    // Removes the student.
    public void drop(int id) throws InvalidStudentException {
        // Find the student with id "id"
        int index = findStudent(id);
        if (index != -1) {
            list.remove(index);
        }
        else {
            throw new InvalidStudentException("can't find " + id);
        }
    }  
        
    // This one is private, so we aren't using exceptions (we could).
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
    public void update(int id, Student s) throws InvalidStudentException { 
        int i = findStudent(id);
        if (i != -1) {
            list.set(i, s);
        }
        else {
            throw new InvalidStudentException("can't find " + id);
        }
    }
    
    // Tester method.
    public static void main(String[] args)
    {
        ClassList_v3 list = new ClassList_v3();
        assert list.size() == 0 : "should be 0";

        list.add( new Student("abc", "first", 111) );
        list.add( new Student("def", "first", 123) );
        assert list.size() == 2 : "should be 2";
        
        // Try to add a null (illegal).
        try {
            list.add(null);
            assert false : "should not get here";
        }
        catch (InvalidStudentException e) {
            assert true : "got here";
        }
        
        list.drop(111);
        assert list.size() == 1 : "should be 1";
        
        // Try to drop a student that doesn't exist.
        try { 
            list.drop(999);
            assert false : "should not get here";
        }
        catch (InvalidStudentException e) {
            assert list.size() == 1 : "should still be 1";
        }
        
        assert list.isEnrolled(123) == true : "should be true";
        
        list.update(123, new Student("xyz", "first", 999) );
        assert list.size() == 1 : "should still be 1";
        assert list.isEnrolled(999) == true : "999 should exit";
        
        // Try to update a student that doesn't exist.
        try {
            list.update(789, new Student("xyz", "first", 999) );
            assert false : "should not get here";
        }
        catch (InvalidStudentException e) {
            assert true : "got here";
        }
        
    }
}
