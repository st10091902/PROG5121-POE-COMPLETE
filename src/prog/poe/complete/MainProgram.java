package prog.poe.complete;

import javax.swing.JOptionPane;

public class MainProgram {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        AddTask.taskManager = taskManager; 

        while (true) {
            Login loginObj = new Login();
            loginObj.runLoginProcess();

            if (loginObj.loginUser()) {
                JOptionPane.showMessageDialog(null, "Welcome to EasyKanban", "Welcome", JOptionPane.PLAIN_MESSAGE);
                AddTask.showMenu(); 
                break;
            } else {
                int choice = JOptionPane.showConfirmDialog(null, "Incorrect login credentials. Do you want to try again?", "Login Failed", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "Exiting Application", "Alert", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
            }
        }
    }

}
