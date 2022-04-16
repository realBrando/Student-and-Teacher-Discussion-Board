/**
 * Account
 *
 * This class is the baseline Account class that student and teacher
 * build off of. It is a generic account for the discussion board
 *
 * @author Andrew Brandon, Luca Marinello, Alex Moraes
 *
 * @version April 11, 2022
 *
 */
public class Account {

    private String username;

    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
