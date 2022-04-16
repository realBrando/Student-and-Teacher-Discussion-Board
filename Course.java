import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Course
 *
 * Course class
 *
 * @author Luca Marinello, Andrew Brandon, Alexandre Moraes, Alice Dinh, Eric Yong
 *
 * @version April 11, 2022
 *
 */
public class Course implements Serializable {

    private String name;

    private List<Forum> forums;

    public Course(String name, List<Forum> forums) {
        this.name = name;
        this.forums = forums;
    }

    public Course(String name) {
        this.name = name;
        this.forums = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Forum> getForums() {
        return this.forums;
    }

    public String toString() {
        StringBuilder toReturn = new StringBuilder("Course{" + getName());
        for (Forum f : forums) {
            toReturn.append(", ").append(f.toString());
        }
        toReturn.append("}");
        return toReturn.toString();
    }



}
