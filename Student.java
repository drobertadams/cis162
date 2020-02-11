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

    // Return true of other and this are the same student (same lastname and firstname).
    public boolean equals(Student other) 
    {
        return 
            this.lastName.equals(other.lastName) && 
            this.firstName.equals(other.firstName);
    }

    public double getGPA()
    {
        return gpa;
    }

    public void setGPA(double gpa) 
    {
        if (gpa >= 0.0 && gpa <= 4.0)
        {
            this.gpa = gpa;
        }
    }

    // === TESTS ==============================================================
    // Code that tests the methods above.

    private void testGPA()
    {
        // Build a brand new student to test on.
        Student s = new Student("LAST", "FIRST");

        // Make sure the default gpa is 0.
        assert (s.getGPA() == 0.0) : "Initial GPA is not 0!";

        // Make sure getter and setter are working.
        s.setGPA(3.0);
        assert (s.getGPA() == 3.0) : "Initial GPA is not 3!";

        // Try to set a negative GPA. Should remain unchanged.
        s.setGPA(-1);
        assert (s.getGPA() == 3.0) : "Initial GPA should remain 3.0 (-1)!";

        // Try to set a GPA > 4. Should remain unchanged.
        s.setGPA(10.0);
        assert (s.getGPA() == 3.0) : "Initial GPA should remain 3.0 (10)!";
        
        // Make sure equals works.
        Student t1 = new Student("LAST", "first");
        assert ( s.equals(t1) == false ) : "equals() should return false";
        
        Student t2 = new Student("LAST", "FIRST");
        assert ( s.equals(t2) == true ) : "equals() should return true";
        
        System.out.println("END OF TESTS");
        
    }

    // === MAIN ===============================================================
    // Program starts here.
    // main is not a method of Student!
    public static void main(String[] a) 
    {
        Student s = new Student();  // need to build a Student
        s.setGPA(3.2);              // before calling methods on it

        // Let's test some stuff. No news is good news. If our program doesn't
        // print anything, then everything worked.
        s.testGPA();
    }

}
