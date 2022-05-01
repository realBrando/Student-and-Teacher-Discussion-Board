/**
 * Student
 *
 * This program is a subclass of Account for people who log in as students.
 *
 * @author Luca Marinello, Andrew Brandon, Alexandre Moraes, Alice Dinh, Eric Yong
 *
 * @version April 11, 2022
 *
 */
public class Student extends Account {
    private double grade;

    public Student(String username, String password) {
        super(username, password);
        grade = -1;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
