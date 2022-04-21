package net.casheh;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Comment> comments = new ArrayList<>();
        List<Reply> replies = new ArrayList<>();

        Reply reply = new Reply("Bob", "Hey Joe", System.currentTimeMillis());
        replies.add(reply);
        Reply rep = new Reply("Steve", "Sup chumps", System.currentTimeMillis());
        replies.add(rep);
        Reply newrep = new Reply("Bob", "Nobody likes you steve", System.currentTimeMillis());
        replies.add(newrep);
        Comment comment = new Comment(replies, System.currentTimeMillis(), "Joe", "Hey there");
        comments.add(comment);

        Forum f = new Forum("Topic", comments);
        System.out.print(f.toString());
    }
}
