/**
 * Reply
 *
 * Reply class extends message
 *
 * @author Luca Marinello, Andrew Brandon, Alexandre Moraes, Alice Dinh, Eric Yong
 *
 * @version April 11, 2022
 *
 */
public class Reply extends Message implements Serializable {

    private int votes;

    private double grade;

    public Reply(String author, String content, long time, int votes, double grade) {
        super(author, content, time);
        this.votes = votes;
        this.grade = grade;
    }

    public Reply(String author, String content, long time) {
        super(author, content, time);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(getAuthor() + ": ");
        stringBuilder.append(getContent() + " ");
        stringBuilder.append("(").append(getTimestamp(getTime())).append(")");
        return stringBuilder.toString();
    }

    private String getTimestamp(long time) {
        Date date = new Date(time);
        return date.toString();
    }

    public int votes() {
        return this.votes;
    }

    public double grade() {
        return this.grade;
    }

    public void vote() {
        this.votes++;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

}
