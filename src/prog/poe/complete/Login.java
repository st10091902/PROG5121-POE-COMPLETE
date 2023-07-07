package prog.poe.complete;

import javax.swing.JOptionPane;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Login {

    static String userName;
    static String password;
    static String loginUserName;
    static String loginPassword;
    static String firstName;
    static String lastName;

    public static boolean runLoginProcess() {
        firstName = JOptionPane.showInputDialog(null, "Enter First Name");
        lastName = JOptionPane.showInputDialog(null, "Enter Last Name");

        if (checkUserName() && checkPasswordComplexity()) {
            registerUser();
            return true;
        }

        returnLoginStatus();
        return false;
    }

    public static boolean checkUserName() {
        userName = JOptionPane.showInputDialog(null, "Enter Username");

        boolean underScore = userName.contains("_");
        int length = userName.length();

        if (length <= 5 && underScore) {
            JOptionPane.showMessageDialog(null, "Username successfully captured");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted. Please ensure that your username\ncontains an underscore and is no more than 5 characters in length.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean checkPasswordComplexity() {
        password = JOptionPane.showInputDialog(null, "Enter Password");

        int length = password.length();

        boolean hasNumber = false;
        boolean hasCapital = false;
        boolean hasSpecialCharacter = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isDigit(ch)) {
                hasNumber = true;
            } else if (Character.isUpperCase(ch)) {
                hasCapital = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecialCharacter = true;
            }
        }

        if (length >= 8 && hasNumber && hasCapital && hasSpecialCharacter) {
            JOptionPane.showMessageDialog(null, "Password successfully captured");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Password is not correctly formatted. Please ensure that the password\ncontains at least 8 characters, a capital letter, a number, and a special character.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean registerUser() {
        JOptionPane.showMessageDialog(null, "User has been registered successfully");
        return true;
    }

    public static boolean loginUser() {
        loginUserName = JOptionPane.showInputDialog(null, "Login\n\nPlease enter username");
        loginPassword = JOptionPane.showInputDialog(null, "Please enter password");

        if (loginUserName.equals(userName) && loginPassword.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public static void returnLoginStatus() {
    if (loginUser()) {
        JOptionPane.showMessageDialog(null, "Welcome " + firstName + ", " + lastName + "! It is great to see you again.");
    } else {
        JOptionPane.showMessageDialog(null, "Username or password incorrect. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        loginUser(); // Prompt for login again if the login credentials are incorrect
    }
}

}
