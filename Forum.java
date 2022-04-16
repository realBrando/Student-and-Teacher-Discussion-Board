import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Forum
 *
 * Forum class
 *
 * @author Luca Marinello, Andrew Brandon, Alexandre Moraes, Alice Dinh, Eric Yong
 *
 * @version April 11, 2022
 *
 */
public class Forum implements Serializable {

    private String topic;

    private List<Comment> comments;

    public Forum(String topic, List<Comment> comments) {
        this.topic = topic;
        this.comments = comments;
    }

    public Forum(String topic) {
        this.topic = topic;
        this.comments = new ArrayList<>();
    }

    public String getTopic() {
        return this.topic;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Forum Topic: " + getTopic());
        stringBuilder.append("\n\nComments:\n\n");
        for (Comment c : comments) {
            stringBuilder.append(c.toString());
        }
        return stringBuilder.toString();
    }


}
