package prog.poe.complete;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TaskManager {

    private List<String> developerNames;
    private List<String> taskNames;
    private List<String> taskIDs;
    private List<Integer> taskDurations;
    private List<String> taskStatuses;

    public TaskManager() {
        developerNames = new ArrayList<>();
        taskNames = new ArrayList<>();
        taskIDs = new ArrayList<>();
        taskDurations = new ArrayList<>();
        taskStatuses = new ArrayList<>();
    }

    public void addTask(AddTask task) {
        developerNames.add(task.developerFirstName);
        taskNames.add(task.taskName);
        taskIDs.add(task.createTaskID());
        taskDurations.add(AddTask.taskDuration[task.taskNumber]);
        taskStatuses.add(task.taskStatus);
    }

    public List<String> getTaskNames() {
        return taskNames;
    }

    public List<String> getDeveloperNames() {
        return developerNames;
    }
    

    public void displayTasksWithStatusDone() {
        StringBuilder output = new StringBuilder("Tasks with status 'Done':\n");
        for (int i = 0; i < taskStatuses.size(); i++) {
            if (taskStatuses.get(i).equals("Done")) {
                output.append("Developer: ").append(developerNames.get(i)).append("\n");
                output.append("Task Name: ").append(taskNames.get(i)).append("\n");
                output.append("Task Duration: ").append(taskDurations.get(i)).append(" Hrs\n");
                output.append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, output.toString(), "Tasks with Status 'Done'", JOptionPane.INFORMATION_MESSAGE);
    }

//    public void displayLongestTask() {
//        int longestDuration = 0;
//        int longestTaskIndex = -1;
//
//        for (int i = 0; i < taskDurations.size(); i++) {
//            if (taskDurations.get(i) > longestDuration) {
//                longestDuration = taskDurations.get(i);
//                longestTaskIndex = i;
//            }
//        }
//
//        if (longestTaskIndex != -1) {
//            StringBuilder output = new StringBuilder("Longest Task:\n");
//            output.append("Developer: ").append(developerNames.get(longestTaskIndex)).append("\n");
//            output.append("Task Duration: ").append(taskDurations.get(longestTaskIndex)).append(" Hrs\n");
//            JOptionPane.showMessageDialog(null, output.toString(), "Longest Task", JOptionPane.INFORMATION_MESSAGE);
//        } else {
//            JOptionPane.showMessageDialog(null, "No tasks found.", "Longest Task", JOptionPane.INFORMATION_MESSAGE);
//        }
//    }
    public String displayLongestTask() {
        int longestDuration = 0;
        int longestTaskIndex = -1;

        for (int i = 0; i < taskDurations.size(); i++) {
            if (taskDurations.get(i) > longestDuration) {
                longestDuration = taskDurations.get(i);
                longestTaskIndex = i;
            }
        }

        if (longestTaskIndex != -1) {
            StringBuilder output = new StringBuilder("Longest Task:\n");
            output.append("Developer: ").append(developerNames.get(longestTaskIndex)).append("\n");
            output.append("Task Duration: ").append(taskDurations.get(longestTaskIndex)).append(" Hrs\n");
            JOptionPane.showMessageDialog(null, output.toString(), "Longest Task", JOptionPane.INFORMATION_MESSAGE);
            return developerNames.get(longestTaskIndex) + ", " + taskDurations.get(longestTaskIndex);
        } else {
            JOptionPane.showMessageDialog(null, "No tasks found.", "Longest Task", JOptionPane.INFORMATION_MESSAGE);
            return "";
        }
    }

    public void searchTaskByName(String taskName) {
        int foundIndex = -1;

        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(taskName)) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex != -1) {
            StringBuilder output = new StringBuilder("Task found:\n");
            output.append("Task Name: ").append(taskNames.get(foundIndex)).append("\n");
            output.append("Developer: ").append(developerNames.get(foundIndex)).append("\n");
            output.append("Task Status: ").append(taskStatuses.get(foundIndex)).append("\n");
            JOptionPane.showMessageDialog(null, output.toString(), "Search Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Task not found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void searchTasksByDeveloper(String developerName) {
        StringBuilder output = new StringBuilder("Tasks assigned to developer '").append(developerName).append("':\n");

        for (int i = 0; i < developerNames.size(); i++) {
            if (developerNames.get(i).equalsIgnoreCase(developerName)) {
                output.append("Task Name: ").append(taskNames.get(i)).append("\n");
                output.append("Task Status: ").append(taskStatuses.get(i)).append("\n");
                output.append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, output.toString(), "Tasks Assigned to Developer", JOptionPane.INFORMATION_MESSAGE);
    }

    public void deleteTaskByName(String taskName) {
        int foundIndex = -1;

        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(taskName)) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex != -1) {
            developerNames.remove(foundIndex);
            taskNames.remove(foundIndex);
            taskIDs.remove(foundIndex);
            taskDurations.remove(foundIndex);
            taskStatuses.remove(foundIndex);
            JOptionPane.showMessageDialog(null, "Task deleted successfully.", "Delete Task", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Task not found.", "Delete Task", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void displayFullReport() {
        StringBuilder output = new StringBuilder("Full Task Report:\n");

        for (int i = 0; i < taskNames.size(); i++) {
            output.append("Developer: ").append(developerNames.get(i)).append("\n");
            output.append("Task Name: ").append(taskNames.get(i)).append("\n");
            output.append("Task ID: ").append(taskIDs.get(i)).append("\n");
            output.append("Task Duration: ").append(taskDurations.get(i)).append(" Hrs\n");
            output.append("Task Status: ").append(taskStatuses.get(i)).append("\n");
            output.append("\n");
        }

        JOptionPane.showMessageDialog(null, output.toString(), "Full Task Report", JOptionPane.INFORMATION_MESSAGE);
    }

    public void reportMenu() {
        String selectedOption;
        do {
            selectedOption = JOptionPane.showInputDialog(
                    null,
                    "Select a report option:\n"
                    + "1. Display tasks with status 'Done'\n"
                    + "2. Display the longest task\n"
                    + "3. Search task by name\n"
                    + "4. Search tasks by developer\n"
                    + "5. Delete task by name\n"
                    + "6. Display full task report\n"
                    + "7. Go back to main menu",
                    "Report Menu",
                    JOptionPane.PLAIN_MESSAGE
            );

            if (selectedOption != null) {
                switch (selectedOption) {
                    case "1":
                        displayTasksWithStatusDone();
                        break;
                    case "2":
                        displayLongestTask();
                        break;
                    case "3":
                        String taskName = JOptionPane.showInputDialog(null, "Enter task name to search:");
                        searchTaskByName(taskName);
                        break;
                    case "4":
                        String developerName = JOptionPane.showInputDialog(null, "Enter developer name to search:");
                        searchTasksByDeveloper(developerName);
                        break;
                    case "5":
                        String deleteTaskName = JOptionPane.showInputDialog(null, "Enter task name to delete:");
                        deleteTaskByName(deleteTaskName);
                        break;
                    case "6":
                        displayFullReport();
                        break;
                    case "7":
                        return;
                }
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "No option selected. Returning to main menu",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
        } while (!selectedOption.equals("7"));
    }
}
