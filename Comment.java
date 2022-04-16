
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Comment
 *
 * Comment class extends message
 *
 * @author Luca Marinello, Andrew Brandon, Alexandre Moraes, Alice Dinh, Eric Yong
 *
 * @version April 11, 2022
 *
 */
public class Comment extends Message implements Serializable {

    private List<Reply> replies;

    public Comment(List<Reply> replies, long time, String author, String content) {
        super(author, content, time);
        this.replies = replies;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(getAuthor() + ": ");
        stringBuilder.append(getContent() + " ");
        stringBuilder.append("(").append(getTimestamp(getTime())).append(")");
        for (Reply r : replies) {
            stringBuilder.append("\n\t" + r.toString());
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    private String getTimestamp(long time) {
        Date date = new Date(time);
        return date.toString();
    }

}
