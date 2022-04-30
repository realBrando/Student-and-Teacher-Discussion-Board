/*
This Test case will test the functionality of changing the
credentials of a pre-existing account in the system
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class TestCase {
    private static List<Account> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        loadAccounts("accounts.txt");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i).getUsername() + "     " + accounts.get(i).getUsername());
        }
        changeUsername(accounts.get(0));
        changePassword(accounts.get(0));
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i).getUsername() + "     " + accounts.get(i).getUsername());
        }
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
            System.out.println("Error");
            System.out.println("Please enter a new username:");
            username = scanner.nextLine();
        }
        account.setUsername(username);
    }
    private static Account getAccount(String username) {
        for (Account a : accounts) {
            if (a.getUsername().equalsIgnoreCase(username)) {
                return a;
            }
        }
        return null;
    }
}
/*
Test passed. Username and Password can successfully be changed
 */
