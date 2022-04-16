
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Disuccion Board
 *
 * Main class
 *
 * @author Luca Marinello, Andrew Brandon, Alexandre Moraes, Alice Dinh, Eric Yong
 *
 * @version April 11, 2022
 *
 */
public class DiscussionBoard {

    private static ArrayList<Account> accounts = new ArrayList<>();

    private static ArrayList<Course> courses = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);

    private static final String welcome = "Welcome to the discussion board!\n" +
            "Please select an option below:" +
            "\n1. Create an account\n2. Login to an existing account";
    private static final String enterUsername = "Enter a username:";
    private static final String enterPassword = "Enter a password:";
    private static final String usernameExists = "Error! An account with that username already exists! " +
            "Please enter another username!";
    private static final String teacherOrStudent = "Please select which category applies to you:\n" +
            "1. Teacher\n2. Student";
    private static final String actionMenu = "Please select an option to continue:\n" +
            "1. Delete Account\n2. Change Password\n3. Change Username\n4. Proceed to discussion board";
    private static final String teacherMenu = "Please select an option to continue:\n" +
            "1. Create a new class\n2. Select existing class";
    private static final String courseMenu = "Please select an option to continue:\n" +
            "1. Create a forum\n2. Delete a forum\n3. Go to forum";

    public static void main(String[] args) {
        loadAccounts("accounts.txt");
        loadCourses("serial.txt");
        Account currentUser;

        int response;
        do {
            System.out.println(welcome);
            response = scanner.nextInt();
            scanner.nextLine();
        } while (response < 1 || response > 2);

        if (response == 1) {
            System.out.println(enterUsername);
            String username = scanner.nextLine();
            if (getAccount(username) != null) {
                do {
                    System.out.println(usernameExists);
                    username = scanner.nextLine();
                } while (getAccount(username) != null);
            }
            System.out.println(enterPassword);
            String password = scanner.nextLine();

            int selection;
            do {
                System.out.println(teacherOrStudent);
                selection = scanner.nextInt();
                scanner.nextLine();
            } while (selection < 1 || selection > 2);

            if (selection == 1) {
                currentUser = new Teacher(username, password);
                accounts.add(currentUser);
            } else {
                currentUser = new Student(username, password);
                accounts.add(currentUser);
            }
        } else {
            String username;
            String password;
            System.out.println(enterUsername);
            username = scanner.nextLine();
            System.out.println(enterPassword);
            password = scanner.nextLine();
            currentUser = getAccount(username);

            while (!checkLogin(username, password)) {
                System.out.println("Invalid credentials! Please try again!");
                System.out.println(enterUsername);
                username = scanner.nextLine();
                System.out.println(enterPassword);
                password = scanner.nextLine();
                currentUser = getAccount(username);
            }
        }

        System.out.printf("Welcome, %s!\n", currentUser.getUsername());

        int actionSelection;
        do {
            do {
                System.out.println(actionMenu);
                actionSelection = scanner.nextInt();
                scanner.nextLine();
            } while (actionSelection < 1 || actionSelection > 4);

            switch (actionSelection) {
                case 1:
                    accounts.remove(currentUser);
                    System.out.println("Your account has been deleted! Goodbye!");
                    unloadAccounts("accounts.txt");
                    unloadCourses("serial.txt");
                    return;
                case 2:
                    changePassword(currentUser);

                    break;
                case 3:
                    changeUsername(currentUser);
                    break;
                case 4:
                    break;
            }
        } while (actionSelection != 4);

        if (currentUser instanceof Teacher) {
            teacherPrompt(currentUser);
        } else {
            studentForumMenu((Student) currentUser, courseSelect(currentUser));
        }
        unloadAccounts("accounts.txt");
        unloadCourses("serial.txt");
    }

    private static void loadAccounts(String inputFile) {
        File f = new File(inputFile);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));

            String line = reader.readLine();
            while (line != null) {
                String[] arr = line.split(",");
                Account account;
                if (arr[2].equals("s")) {
                    account = new Student(arr[0], arr[1]);
                } else {
                    account = new Teacher(arr[0], arr[1]);
                }
                accounts.add(account);
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void unloadAccounts(String outputFile) {
        File f = new File(outputFile);
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(f));
            for (Account account : accounts) {
                writer.println(account.getUsername() + "," + account.getPassword() + "," +
                        (account instanceof Teacher ? "t" : "s"));
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void unloadCourses(String outputFile) {
        try {
            FileOutputStream fos = new FileOutputStream(outputFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(courses);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadCourses(String inputFile) {
        File f = new File(inputFile);
        if (!f.exists()) {
            return;
        }
        try {
            FileInputStream fis = new FileInputStream(inputFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            courses = (ArrayList<Course>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkLogin(String username, String password) {
        for (Account a : accounts) {
            if (a.getUsername().equalsIgnoreCase(username)) {
                if (a.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Account getAccount(String username) {
        for (Account a : accounts) {
            if (a.getUsername().equalsIgnoreCase(username)) {
                return a;
            }
        }
        return null;
    }

    private static void changePassword(Account account) {
        String password;
        System.out.println("Please enter a new password:");
        password = scanner.nextLine();
        account.setPassword(password);
        System.out.println("Password successfully changed!");
    }

    private static void changeUsername(Account account) {
        String username;
        System.out.println("Please enter a new username:");
        username = scanner.nextLine();
        while (getAccount(username) != null) {
            System.out.println(usernameExists);
            System.out.println("Please enter a new username:");
            username = scanner.nextLine();
        }
        account.setUsername(username);
    }

    private static void teacherPrompt(Account account) {
        int selection;
        do {
            System.out.println(teacherMenu);
            selection = scanner.nextInt();
            scanner.nextLine();
        } while ((selection < 1 || selection > 2));

        if (selection == 1) {
            classCreate();
        } else {
            Course c = courseSelect(account);
            String forum;
            do {
                System.out.println("Please type the name of a forum to continue:");
                displayForums(c);
                forum = scanner.nextLine();
            } while (getForum(c, forum) == null);
            Forum f = getForum(c, forum);
            int sel;
            do {
                System.out.println(f.toString());
                do {
                    if (f.getComments().size() < 1) {
                        return;
                    }
                    System.out.println("Please select an option below:");
                    System.out.println("1. Reply to an existing comment\n2. Exit");
                    sel = scanner.nextInt();
                    scanner.nextLine();
                } while (sel < 1 || sel > 2);

                if (sel == 1) {
                    String user;
                    System.out.println("Type the username of the comment you wish to reply to.");
                    user = scanner.nextLine();
                    String content;
                    System.out.println("What would you like to reply with?");
                    content = scanner.nextLine();
                    for (Comment comment : f.getComments()) {
                        if (comment.getAuthor().equalsIgnoreCase(user)) {
                            comment.getReplies().add(new Reply(account.getUsername(),
                                    content, System.currentTimeMillis()));
                        }
                    }
                } else {
                    unloadAccounts("accounts.txt");
                    unloadCourses("serial.txt");
                    return;
                }
            } while (true);

        }
    }

    private static Course courseSelect(Account account) {
        if (courses.size() < 1 && (account instanceof Teacher)) {
            System.out.println("Error! There aren't any courses available! " +
                    "Please create a class.");
            classCreate();
            return null;
        }
        String name;
        do {
            System.out.println("Please type a course name to select:");
            displayCourses();
            name = scanner.nextLine();
        } while (getCourse(name) == null);
        return getCourse(name);
    }

    private static void classCreate() {
        String name;
        System.out.println("Please enter a name for the course:");
        name = scanner.nextLine();
        Course c = new Course(name);
        courses.add(c);
        System.out.println("Success! Your course has been created!");
        forumMenu(c);
    }

    private static void forumMenu(Course c) {
        int selection;
        do {
            do {
                System.out.println(courseMenu);
                selection = scanner.nextInt();
                scanner.nextLine();
            } while (selection < 1 || selection > 3);

            switch (selection) {
                case 1 -> {
                    int sel;
                    do {
                        System.out.println("Please select an option below:");
                        System.out.println("1. Type a topic name\n2. " +
                                "Import a text file containing topic name");
                        sel = scanner.nextInt();
                        scanner.nextLine();
                    } while (sel < 1 || sel > 2);

                    String forumName;
                    switch (sel) {
                        case 1 -> {
                            System.out.println("Please enter the topic of the forum:");
                            forumName = scanner.nextLine();
                        }
                        case 2 -> {
                            System.out.println("Please enter the name of a text file:");
                            String textFile = scanner.nextLine();
                            forumName = teacherReadFile(textFile);
                        }
                        default -> forumName = "";
                    }
                    Forum f = new Forum(forumName);
                    c.getForums().add(f);
                    System.out.println("Success! Your forum has been created.");
                }
                case 2 -> {
                    if (c.getForums().size() < 1) {
                        System.out.println("Error! There aren't any forums in this class!");
                        break;
                    }
                    String topic;
                    do {
                        System.out.println("Please type the topic of a forum to delete:");
                        displayForums(c);
                        topic = scanner.nextLine();
                    } while (getForum(c, topic) == null);
                    removeForum(c, topic);
                }
                case 3 -> {
                    if (c.getForums().size() < 1) {
                        System.out.println("Error! There aren't any forums in this class!");
                        break;
                    }
                    String topic;
                    do {
                        System.out.println("Select a forum to go to:");
                        displayForums(c);
                        topic = scanner.nextLine();
                    } while (getForum(c, topic) == null);
                    System.out.println(getForum(c, topic).toString());
                }
            }
        } while (selection != 3 || c.getForums().size() < 1);
    }

    private static void studentForumMenu(Student student, Course c) {
        String topic;
        do {
            System.out.println("Please select a forum to go to:");
            displayForums(c);
            topic = scanner.nextLine();
        } while (getForum(c, topic) == null);
        Forum f = getForum(c, topic);
        System.out.println(f.toString());
        int selection;
        do {
            System.out.println("\nPlease select an option below:");
            do {
                System.out.println("1. Start a new comment\n2. " +
                        "Reply to a previously created comment\n3. Exit");
                selection = scanner.nextInt();
                scanner.nextLine();
            } while (selection < 1 || selection > 3);

            if (selection == 1) {
                int sel;
                do {
                    System.out.println("Please select an option below:");
                    System.out.println("1. Type a comment\n2. Import a file containing your comment");
                    sel = scanner.nextInt();
                    scanner.nextLine();
                } while (sel < 1 || sel > 2);
                String content;
                switch (sel) {
                    case 1 -> {
                        System.out.println("Please type a comment:");
                        content = scanner.nextLine();
                    }
                    case 2 -> {
                        String file;
                        System.out.println("Please enter a filename to read from:");
                        file = scanner.nextLine();
                        content = studentReadFile(file);
                    }
                    default -> content = "";
                }

                Comment comment = new Comment(new ArrayList<>(),
                        System.currentTimeMillis(), student.getUsername(), content);
                f.getComments().add(comment);
            } else if (selection == 2) {
                // todo MAYBE add a case that protects against if they put a name that's not there
                String user;
                System.out.println("Type the username of the comment you wish to reply to.");
                user = scanner.nextLine();
                String content;
                System.out.println("What would you like to reply with?");
                content = scanner.nextLine();
                for (Comment comment : f.getComments()) {
                    if (comment.getAuthor().equalsIgnoreCase(user)) {
                        comment.getReplies().add(new Reply(student.getUsername(),
                                content, System.currentTimeMillis()));
                    }
                }
            } else {
                System.out.println("Goodbye!");
                unloadAccounts("accounts.txt");
                unloadCourses("serial.txt");
            }
            System.out.println(f.toString());
        } while (selection != 3);
    }

    private static void displayCourses() {
        for (Course course : courses) {
            System.out.println("- " + course.getName());
        }
    }

    private static void displayForums(Course c) {
        for (Forum f : c.getForums()) {
            System.out.println("- " + f.getTopic());
        }
    }

    private static void removeForum(Course c, String name) {
        if (getForum(c, name) == null) {
            return;
        }
        c.getForums().remove(getForum(c, name));
    }

    private static Course getCourse(String name) {
        for (Course c : courses) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    private static Forum getForum(Course course, String topic) {
        for (Forum f : course.getForums()) {
            if (f.getTopic().equals(topic)) {
                return f;
            }
        }
        return null;
    }

    private static String teacherReadFile(String filename) {
        File file = new File(filename);
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bfr = new BufferedReader(fr);
        String s = "";
        String topic = "";
        while (true) {
            try {
                s = bfr.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (s == null) {
                break;
            }
            topic += s;
        }

        return topic;
    }

    private static String studentReadFile(String filename) {
        File file = new File(filename);
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bfr = new BufferedReader(fr);
        String s = "";
        String reply = "";
        while (true) {
            try {
                s = bfr.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (s == null) {
                break;
            }
            reply += s;
        }
        return reply;
    }




}
