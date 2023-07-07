package prog.poe.complete;

import javax.swing.JOptionPane;

import javax.swing.JOptionPane;

public class AddTask {

    static String taskName, developerFirstName, developerLastName, taskStatus, taskID;
    static String taskDescription;
    static int taskNumber;
    static int size;
    static int index;
    static AddTask taskArray[];
    static int[] taskDuration;
    static int totalHours = 0;
    static TaskManager taskManager;

    public static void createTask(TaskManager taskManager) {
        int size = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            String input = JOptionPane.showInputDialog(null, "Enter the number of tasks to create:");
            try {
                size = Integer.parseInt(input);
                if (size > 0) {
                    isValidInput = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a positive value.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        taskArray = new AddTask[size];
        taskDuration = new int[size];

        AddTask.taskManager = taskManager;

        for (int index = 0; index < size; index++) {
            AddTask task = new AddTask();
            task.taskNumber = index;
            inputDetails(task, index);

            taskManager.addTask(task);
        }
        returnTotalHours();
        showMenu();
    }

    public static void inputDetails(AddTask taskobj, int index) {
        JOptionPane.showMessageDialog(null, "Enter Task Name:");
        taskobj.taskName = JOptionPane.showInputDialog(null);

        JOptionPane.showMessageDialog(null, "Enter Task Description:");
        taskobj.taskDescription = JOptionPane.showInputDialog(null);

        JOptionPane.showMessageDialog(null, "Enter Developer First Name:");
        taskobj.developerFirstName = JOptionPane.showInputDialog(null);

        JOptionPane.showMessageDialog(null, "Enter Developer Last Name:");
        taskobj.developerLastName = JOptionPane.showInputDialog(null);

        taskDuration[index] = getTaskDuration();

        String[] options = {"To Do", "Done", "Doing"};
        int option = JOptionPane.showOptionDialog(
                null,
                "Select Task Status:",
                "Task Status",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        if (option == 0) {
            taskobj.taskStatus = "To Do";
        } else if (option == 1) {
            taskobj.taskStatus = "Done";
        } else if (option == 2) {
            taskobj.taskStatus = "Doing";
        }

        printTaskDetails(taskobj);
    }

    public static void checkTaskDescription() {
        if (taskDescription.length() < 50) {
            JOptionPane.showMessageDialog(null, "Task successfully captured", "", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters", "Error", JOptionPane.ERROR_MESSAGE);
            checkTaskDescription();
        }
    }

    public static String createTaskID() {
        String taskNamePrefix = taskName.substring(0, Math.min(taskName.length(), 2)).toUpperCase();
        String developerNameSuffix = developerFirstName.substring(Math.max(developerFirstName.length() - 3, 0)).toUpperCase();
        taskID = taskNamePrefix + ":" + taskNumber + ":" + developerNameSuffix;
        return taskID;
    }

    public static void printTaskDetails(AddTask taskobj) {
        String output = "Task Status: " + taskobj.taskStatus
                + "\nDeveloper Name: " + taskobj.developerFirstName
                + "\nDeveloper Last Name: " + taskobj.developerLastName
                + "\nTask Number: " + taskobj.taskNumber
                + "\nTask Name: " + taskobj.taskName
                + "\nTask Description: " + taskobj.taskDescription
                + "\nTask ID: " + taskobj.createTaskID()
                + "\nTask Duration: " + taskDuration[taskobj.taskNumber] + " Hrs";

        JOptionPane.showMessageDialog(null, output, "Task Details", JOptionPane.PLAIN_MESSAGE);
    }

    public static void showReport() {
        taskManager.reportMenu();
        showMenu();
    }

    public static void exitApp() {
        JOptionPane.showMessageDialog(null, "Exiting Application", "Alert", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public static void showMenu() {
        String selectedOption;
        do {
            selectedOption = JOptionPane.showInputDialog(
                    null,
                    "Select an option:\n"
                    + "1. Add Task\n"
                    + "2. Display Tasks Report Menu\n"
                    + "3. Exit Application\n",
                    "Menu",
                    JOptionPane.PLAIN_MESSAGE
            );

            if (selectedOption != null) {
                switch (selectedOption) {
                    case "1":
                        createTask(taskManager);
                        break;
                    case "2":
                        showReport();
                        break;
                    case "3":
                        exitApp();
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "No option selected. Application exiting",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                System.exit(0);
            }
        } while (!selectedOption.equals("3"));
    }

    public static int getTaskDuration() {
        int duration = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            String input = JOptionPane.showInputDialog(null, "Enter Task Duration in hours:");
            try {
                duration = Integer.parseInt(input);
                if (duration > 0) {
                    isValidInput = true;
                    totalHours += duration;  // Update total hours
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid duration. Please enter a positive value.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return duration;
    }

    public static void returnTotalHours() {
        JOptionPane.showMessageDialog(null, "The Total Hours Of All The Tasks Are: " + totalHours + " Hrs", "Total Hours For Tasks", JOptionPane.INFORMATION_MESSAGE);
    }
    private static JOptionPaneWrapper jOptionPane;

    public static void setJOptionPane(JOptionPaneWrapper jOptionPane) {
        AddTask.jOptionPane = jOptionPane;
    }
}
