/**
 * Represents a student.
 */
public class Student implements Comparable
{
    private String lastName, firstName;
    private double gpa;
    private int id;

    public Student() // default constructor
    {
        lastName = "Blue";
        firstName = "Test";
        gpa = 0.0;
        id = 0;
    }

    public Student(String lastName, String firstName, int id)
    {
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = id;
        gpa = 0;
    }
    
    public int compareTo(Object other) {
        Student otherStudent = (Student) other;
        String otherName = otherStudent.getName();
        String myName = this.getName();
        return otherName.compareTo(myName);
    }

    public boolean equals(Student other) 
    {
        return this.id == other.id;
    }

    public double getGPA() { return gpa; }

    public int getID() { return id; }

    public String getName() { return lastName + ", " + firstName; }

    public void setGPA(double gpa) 
    {
        if (gpa >= 0.0 && gpa <= 4.0)
        {
            this.gpa = gpa;
        }
    }
}
