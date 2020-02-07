/**
 * Represents a student.
 *
 * @author Robert Adams
 * @version 1.0
 */
public class Student
{
    // === DATA FIELDS ========================================================
    // Data that lives between method calls. "Persistent data"
    private String lastName, firstName;
    private double gpa;

    // === CONSTRUCTORS =======================================================
    // Normally listed from simple to more complex.

    public Student() // default constructor
    {
        lastName = "Blue";
        firstName = "Test";
        gpa = 0.0;
    }

    public Student(String lastName, String firstName)
    {
        // "this" is a reference to yourself. So "this.lastName" tells Java to
        // use the "lastName" field of "this" object (a Student).
        this.lastName = lastName;
        this.firstName = firstName;
        gpa = 0;
    }

    public Student(String l, String f, double g)
    {
        // If you don't use "this", make sure the parameter names are different
        // than the field names.
        lastName = l;
        firstName = f;
        gpa = g;
    }

    // === OTHER METHODS ====================================================
    // I like to list these in alphabetical order to make them easy to find.
    
    public double getGPA()
    {
        return gpa;
    }

    public void setGPA(double gpa) 
    {
        this.gpa = gpa;
    }

    // === MAIN ===============================================================
    // Program starts here.
    // main is not a method of Student!
    public static void main(String[] a) 
    {
        
        Student s = new Student();  // need to build a Student
        s.setGPA(3.2);              // before calling methods on it
    }

}
