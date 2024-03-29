import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Discussion Board
 *
 * Main class
 *
 * @author Luca Marinello, Andrew Brandon, Alexandre Moraes, Alice Dinh, Eric Yong
 *
 * @version April 11, 2022
 *
 */
public class DiscussionBoard extends JComponent implements Runnable {

    private static ArrayList<Account> accounts = new ArrayList<>();

    private static ArrayList<Course> courses = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);

    private DiscussionBoard discussionBoard;

    private static final String title = "Discussion Board";
    private static final String welcome = "Welcome to the discussion board!";
//            "\n1. Create an account\n2. Login to an existing account";
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

    //current user
    Account currentUser;

    //welcome JFrame
    JFrame welcomeFrame;
    JTextField userNameText;
    JTextField passwordText;
    JButton createAccountButton;
    JButton loginButton;

    //account creation JFrame
    JFrame accountCreationFrame;
    JTextField newUserNameText;
    JTextField newPasswordText;
    JButton accountCreatedButton;
    JCheckBox studentBox;
    JCheckBox teacherBox;

    //action menu
    JFrame actionMenuFrame;
    JButton deleteAccountButton;
    JButton changePasswordButton;
    JButton changeUsernameButton;
    JButton goToDBButton;

    //teacher prompt
    JFrame teacherPromptFrame;
    JButton createCourse;
    JButton chooseCourse;

    //discussion board
    JFrame discussionBoardFrame;

    //student forum
    JFrame studentForumFrame;
    JButton replyButton;
    JButton voteButton;
    JButton exitButton;



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new DiscussionBoard());
    }

    public void run() {

        loadAccounts("accounts.txt");
        loadCourses("serial.txt");

        welcomeFrame= new JFrame(title);
        Container content = welcomeFrame.getContentPane();
        SpringLayout layout = new SpringLayout();
        content.setLayout(layout);
//        discussionBoard = new DiscussionBoard();
//        content.add(discussionBoard, BorderLayout.CENTER);

        JLabel heading = new JLabel(welcome);
        JLabel or = new JLabel("Or");
        userNameText = new JTextField("username", 15);
        passwordText = new JTextField("password", 15);
        loginButton = new JButton("Login");
        loginButton.addActionListener(actionListener);
        createAccountButton = new JButton("Create an account");
        createAccountButton.addActionListener(actionListener);

        content.add(heading);
        content.add(userNameText);
        content.add(passwordText);
        content.add(loginButton);
        content.add(or);
        content.add(createAccountButton);

        //location for the heading
        layout.putConstraint(SpringLayout.NORTH, heading, 30, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, heading, 190, SpringLayout.WEST, content);

        //location for the username and password txt fields
        layout.putConstraint(SpringLayout.NORTH, userNameText, 100, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, userNameText, 200, SpringLayout.WEST, content);
        layout.putConstraint(SpringLayout.NORTH, passwordText, 150, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, passwordText, 200, SpringLayout.WEST, content);

        //location for the login and create account buttons
        layout.putConstraint(SpringLayout.NORTH, loginButton, 200, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, loginButton, 260, SpringLayout.WEST, content);
        layout.putConstraint(SpringLayout.NORTH, or, 240, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, or, 290, SpringLayout.WEST, content);
        layout.putConstraint(SpringLayout.NORTH, createAccountButton, 270, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, createAccountButton, 220, SpringLayout.WEST, content);

        welcomeFrame.setSize(600, 500);
        welcomeFrame.setLocationRelativeTo(null);
        welcomeFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        welcomeFrame.setVisible(true);

    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //create account
            if (e.getSource() == createAccountButton) {
                createAccount();
            }
            //login
            if (e.getSource() == loginButton) {
                String username = userNameText.getText();
                String password = passwordText.getText();

                if (!checkLogin(username, password)) {
                    JOptionPane.showMessageDialog(null, "Invalid credentials!" +
                            " Please try again!", title, JOptionPane.ERROR_MESSAGE);
                } else {
                    currentUser = getAccount(username);
                    showActionMenu();
                }
            }
            //account creation
            if (e.getSource() == accountCreatedButton) {
                String username = newUserNameText.getText();
                String password = newPasswordText.getText();
                if (getAccount(username)!= null) {
                    JOptionPane.showMessageDialog(null, usernameExists, title,
                            JOptionPane.ERROR_MESSAGE);
                } else if ((studentBox.isSelected() && teacherBox.isSelected()) ||
                        (!studentBox.isSelected() && !teacherBox.isSelected())) {
                    JOptionPane.showMessageDialog(null, "Invalid role selection", title,
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    if (studentBox.isSelected()) {
                        currentUser = new Student(username, password);
                    } else if (teacherBox.isSelected()) {
                        currentUser = new Teacher(username, password);
                    }
                    accounts.add(currentUser);
                    unloadAccounts("accounts.txt");
                    JOptionPane.showMessageDialog(null, "Your account was created!",
                            title, JOptionPane.INFORMATION_MESSAGE);
                    accountCreationFrame.dispose();
                }
            }
            if (e.getSource() == deleteAccountButton) {
                accounts.remove(currentUser);
                unloadAccounts("accounts.txt");
                JOptionPane.showMessageDialog(null, "Your account has been deleted!", title,
                        JOptionPane.INFORMATION_MESSAGE);
                actionMenuFrame.dispose();
            }
            if (e.getSource() == changePasswordButton) {
                changePassword(currentUser);
            }
            if (e.getSource() == changeUsernameButton) {
                changeUsername(currentUser);
                actionMenuFrame.dispose();
                showActionMenu();
            }
            if (e.getSource() == goToDBButton) {
                showDiscussionBoard();
                discussionBoardFrame.setVisible(true);
            }
            unloadAccounts("accounts.txt");
        }
    };

    private void createAccount() {

        accountCreationFrame = new JFrame("Create a new account");
        Container content = accountCreationFrame.getContentPane();
        SpringLayout layout = new SpringLayout();
        content.setLayout(layout);

        accountCreatedButton = new JButton("Create account");
        accountCreatedButton.addActionListener(actionListener);
        newUserNameText = new JTextField("username", 15);
        newPasswordText = new JTextField("password", 15);
        studentBox = new JCheckBox("Student");
        teacherBox = new JCheckBox("Teacher");

        content.add(newUserNameText);
        content.add(newPasswordText);
        content.add(studentBox);
        content.add(teacherBox);
        content.add(accountCreatedButton);

        //location for the username txt field
        layout.putConstraint(SpringLayout.NORTH, newUserNameText, 60, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, newUserNameText, 200, SpringLayout.WEST, content);

        //location for the password txt field
        layout.putConstraint(SpringLayout.NORTH, newPasswordText, 110, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, newPasswordText, 200, SpringLayout.WEST, content);

        //location for the student and teacher text boxes
        layout.putConstraint(SpringLayout.NORTH, teacherBox, 160, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, teacherBox, 210, SpringLayout.WEST, content);
        layout.putConstraint(SpringLayout.NORTH, studentBox, 160, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, studentBox, 300, SpringLayout.WEST, content);

        //location for the create account button
        layout.putConstraint(SpringLayout.NORTH, accountCreatedButton, 200, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, accountCreatedButton, 220, SpringLayout.WEST, content);

        accountCreationFrame.setSize(600, 500);
        accountCreationFrame.setLocationRelativeTo(null);
        accountCreationFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        accountCreationFrame.setVisible(true);
    }

    private void showActionMenu() {
        actionMenuFrame = new JFrame("Menu");
        Container content = actionMenuFrame.getContentPane();
        SpringLayout layout = new SpringLayout();
        content.setLayout(layout);

        JLabel welcome = new JLabel(String.format("Welcome, %s!\n", currentUser.getUsername()));
        deleteAccountButton = new JButton("Delete account");
        deleteAccountButton.addActionListener(actionListener);
        changePasswordButton = new JButton("Change password");
        changePasswordButton.addActionListener(actionListener);
        changeUsernameButton = new JButton("Change username");
        changeUsernameButton.addActionListener(actionListener);
        goToDBButton = new JButton("Proceed to discussion board");
        goToDBButton.addActionListener(actionListener);

        content.add(welcome);
        content.add(deleteAccountButton);
        content.add(changePasswordButton);
        content.add(changeUsernameButton);
        content.add(goToDBButton);

        //location for the welcome message
        layout.putConstraint(SpringLayout.NORTH, welcome, 30, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, welcome, 240, SpringLayout.WEST, content);

        //location for the buttons
        layout.putConstraint(SpringLayout.NORTH, deleteAccountButton, 100, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, deleteAccountButton, 238, SpringLayout.WEST, content);
        layout.putConstraint(SpringLayout.NORTH, changePasswordButton, 150, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, changePasswordButton, 230, SpringLayout.WEST, content);
        layout.putConstraint(SpringLayout.NORTH, changeUsernameButton, 200, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, changeUsernameButton, 230, SpringLayout.WEST, content);
        layout.putConstraint(SpringLayout.NORTH, goToDBButton, 250, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, goToDBButton, 200, SpringLayout.WEST, content);

        actionMenuFrame.setSize(600, 500);
        actionMenuFrame.setLocationRelativeTo(null);
        actionMenuFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        actionMenuFrame.setVisible(true);
    }

    private void showDiscussionBoard() {
        if (currentUser instanceof Teacher) {
            teacherPrompt(currentUser);
        } else {
            studentPrompt();
        }
    }

    private void studentPrompt() {
        String[] coursesArr = new String[courses.size()];
        for (int i = 0; i < courses.size(); i++) {
            coursesArr[i] = courses.get(i).getName();
        }
        String courseSelected;
        courseSelected = (String) JOptionPane.showInputDialog(null,
                "Please select a course:", title, JOptionPane.QUESTION_MESSAGE, null,
                coursesArr, coursesArr[0]);

        Course c = getCourse(courseSelected);

        String[] forumArr = new String[c.getForums().size()];
        for (int i = 0; i < c.getForums().size(); i++) {
            forumArr[i] = c.getForums().get(i).getTopic();
        }
        String forumSelected;
        forumSelected = (String) JOptionPane.showInputDialog(null,
                "Please select a forum to go to:", title, JOptionPane.QUESTION_MESSAGE, null,
                forumArr, forumArr[0]);

        Forum forum = getForum(c, forumSelected);

        showStudentForum(forum);
    }

    private void teacherPrompt(Account account) {
        discussionBoardFrame = new JFrame(title);
        discussionBoardFrame.setSize(600, 500);
        discussionBoardFrame.setLocationRelativeTo(null);
        discussionBoardFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLabel welcome = new JLabel("Welcome to the Discussion Board!");
        Container content = discussionBoardFrame.getContentPane();
        SpringLayout layout = new SpringLayout();
        content.setLayout(layout);
        createCourse = new JButton("Create a Class");
        createCourse.addActionListener(actionListener);
        chooseCourse = new JButton("Choose a Class");
        chooseCourse.addActionListener(actionListener);
        content.add(welcome);
        content.add(createCourse);
        content.add(chooseCourse);

        //Layout for Welcome Message
        layout.putConstraint(SpringLayout.NORTH, welcome, 30, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, welcome, 190, SpringLayout.WEST, content);

        //Layout for Buttons
        layout.putConstraint(SpringLayout.NORTH, createCourse, 100, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, createCourse, 233, SpringLayout.WEST, content);
        layout.putConstraint(SpringLayout.NORTH, chooseCourse, 150, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, chooseCourse, 231, SpringLayout.WEST, content);
    }

    private void showStudentForum(Forum forum) {
        studentForumFrame = new JFrame(title);
        Container content = studentForumFrame.getContentPane();
        SpringLayout layout = new SpringLayout();
        content.setLayout(layout);
        JLabel forumTopic = new JLabel("Forum topic: " + forum.getTopic() + "\nComments:");

        replyButton = new JButton("Reply to a comment");
        replyButton.addActionListener(actionListener);
        exitButton = new JButton("Exit");
        exitButton.addActionListener(actionListener);
        voteButton = new JButton("Vote");
        voteButton.addActionListener(actionListener);

        content.add(replyButton);
        content.add(exitButton);
        content.add(voteButton);

        //layout for buttons
        layout.putConstraint(SpringLayout.NORTH, replyButton, 10, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, replyButton, 70, SpringLayout.WEST, content);
        layout.putConstraint(SpringLayout.NORTH, exitButton, 10, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, exitButton, 20, SpringLayout.WEST, content);
        layout.putConstraint(SpringLayout.NORTH, voteButton, 10, SpringLayout.NORTH, content);
        layout.putConstraint(SpringLayout.WEST, voteButton, 100, SpringLayout.WEST, content);

        studentForumFrame.setSize(600, 500);
        studentForumFrame.setLocationRelativeTo(null);
        studentForumFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        studentForumFrame.setVisible(true);
    }


//        loadAccounts("accounts.txt");
//        loadCourses("serial.txt");
//        Account currentUser;
//
//        int response;
//        do {
//            System.out.println(welcome);
//            response = scanner.nextInt();
//            scanner.nextLine();
//        } while (response < 1 || response > 2);
//
//        if (response == 1) {
//            System.out.println(enterUsername);
//            String username = scanner.nextLine();
//            if (getAccount(username) != null) {
//                do {
//                    System.out.println(usernameExists);
//                    username = scanner.nextLine();
//                } while (getAccount(username) != null);
//            }
//            System.out.println(enterPassword);
//            String password = scanner.nextLine();
//
//            int selection;
//            do {
//                System.out.println(teacherOrStudent);
//                selection = scanner.nextInt();
//                scanner.nextLine();
//            } while (selection < 1 || selection > 2);
//
//            if (selection == 1) {
//                currentUser = new Teacher(username, password);
//                accounts.add(currentUser);
//            } else {
//                currentUser = new Student(username, password);
//                accounts.add(currentUser);
//            }
//        } else {
//            String username;
//            String password;
//            System.out.println(enterUsername);
//            username = scanner.nextLine();
//            System.out.println(enterPassword);
//            password = scanner.nextLine();
//            currentUser = getAccount(username);
//
//            while (!checkLogin(username, password)) {
//                System.out.println("Invalid credentials! Please try again!");
//                System.out.println(enterUsername);
//                username = scanner.nextLine();
//                System.out.println(enterPassword);
//                password = scanner.nextLine();
//                currentUser = getAccount(username);
//            }
//        }
//
//        System.out.printf("Welcome, %s!\n", currentUser.getUsername());
//
//        int actionSelection;
//        do {
//            do {
//                System.out.println(actionMenu);
//                actionSelection = scanner.nextInt();
//                scanner.nextLine();
//            } while (actionSelection < 1 || actionSelection > 4);
//
//            switch (actionSelection) {
//                case 1:
//                    accounts.remove(currentUser);
//                    System.out.println("Your account has been deleted! Goodbye!");
//                    unloadAccounts("accounts.txt");
//                    unloadCourses("serial.txt");
//                    return;
//                case 2:
//                    changePassword(currentUser);
//
//                    break;
//                case 3:
//                    changeUsername(currentUser);
//                    break;
//                case 4:
//                    break;
//            }
//        } while (actionSelection != 4);
//
//        if (currentUser instanceof Teacher) {
//            teacherPrompt(currentUser);
//        } else {
//            studentForumMenu((Student) currentUser, courseSelect(currentUser));
//        }
//        unloadAccounts("accounts.txt");
//        unloadCourses("serial.txt");
//    }

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
        password = JOptionPane.showInputDialog(null, "Enter a new password:",
                title, JOptionPane.INFORMATION_MESSAGE);
        if (password != null) {
            account.setPassword(password);
            JOptionPane.showMessageDialog(null, "Password successfully changed!",
                    title, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void changeUsername(Account account) {
        String username;
        username = JOptionPane.showInputDialog(null, "Enter a new username:",
                title, JOptionPane.INFORMATION_MESSAGE);
        if (username != null) {
            while (getAccount(username) != null) {
                JOptionPane.showMessageDialog(null, usernameExists, title,
                        JOptionPane.ERROR_MESSAGE);
                username = JOptionPane.showInputDialog(null, "Enter a new username:",
                        title, JOptionPane.INFORMATION_MESSAGE);
            }
            account.setUsername(username);
            JOptionPane.showMessageDialog(null, "Username successfully changed!",
                    title, JOptionPane.INFORMATION_MESSAGE);
        }
    }

//    private static void teacherPrompt(Account account) {
//        int selection;
//        do {
//            System.out.println(teacherMenu);
//            selection = scanner.nextInt();
//            scanner.nextLine();
//        } while ((selection < 1 || selection > 2));
//
//        if (selection == 1) {
//            classCreate();
//        } else {
//            Course c = courseSelect(account);
//            String forum;
//            do {
//                System.out.println("Please type the name of a forum to continue:");
//                displayForums(c);
//                forum = scanner.nextLine();
//            } while (getForum(c, forum) == null);
//            Forum f = getForum(c, forum);
//            int sel;
//            do {
//                System.out.println(f.toString());
//                do {
//                    if (f.getComments().size() < 1) {
//                        return;
//                    }
//                    System.out.println("Please select an option below:");
//                    System.out.println("1. Reply to an existing comment\n2. Exit");
//                    sel = scanner.nextInt();
//                    scanner.nextLine();
//                } while (sel < 1 || sel > 2);
//
//                if (sel == 1) {
//                    String user;
//                    System.out.println("Type the username of the comment you wish to reply to.");
//                    user = scanner.nextLine();
//                    String content;
//                    System.out.println("What would you like to reply with?");
//                    content = scanner.nextLine();
//                    for (Comment comment : f.getComments()) {
//                        if (comment.getAuthor().equalsIgnoreCase(user)) {
//                            comment.getReplies().add(new Reply(account.getUsername(),
//                                    content, System.currentTimeMillis()));
//                        }
//                    }
//                } else {
//                    unloadAccounts("accounts.txt");
//                    unloadCourses("serial.txt");
//                    return;
//                }
//            } while (true);
//
//        }
//    }

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
