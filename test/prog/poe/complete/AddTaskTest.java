package prog.poe.complete;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.JOptionPane;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTaskTest {

    private AddTask addTask;

    @BeforeEach
    public void setup() {
        addTask = new AddTask();
    }

    @Test
    public void testCheckTaskDescription_ValidDescription() {
        // Arrange
        addTask.taskDescription = "This is a valid task description.";

        // Mock the JOptionPane class to capture the dialog being displayed
        JOptionPaneWrapper jOptionPaneWrapper = new JOptionPaneWrapper();
        addTask.setJOptionPane(jOptionPaneWrapper);

        // Act
        addTask.checkTaskDescription();

        // Assert
        assertEquals("Task successfully captured", jOptionPaneWrapper.getMessage());
        assertEquals(JOptionPane.PLAIN_MESSAGE, jOptionPaneWrapper.getMessageType());
    }

    @Test
    public void testCheckTaskDescription_InvalidDescription() {
        // Arrange
        addTask.taskDescription = "This is an invalid task description because it exceeds the maximum length of 50 characters.";

        // Mock the JOptionPane class to capture the dialog being displayed
        JOptionPaneWrapper jOptionPaneWrapper = new JOptionPaneWrapper();
        addTask.setJOptionPane(jOptionPaneWrapper);

        // Act
        addTask.checkTaskDescription();

        // Assert
        assertEquals("Please enter a task description of less than 50 characters", jOptionPaneWrapper.getMessage());
        assertEquals(JOptionPane.ERROR_MESSAGE, jOptionPaneWrapper.getMessageType());
    }

    @Test
    public void testCreateTaskID() {
        // Arrange
        addTask.taskName = "Sample Task";
        addTask.developerFirstName = "John";
        addTask.developerLastName = "Doe";
        addTask.taskNumber = 1;

        // Act
        String taskId = addTask.createTaskID();

        // Assert
        assertEquals("SA:1:DOE", taskId);
    }

    @Test
    public void testPrintTaskDetails() {
        // Arrange
        AddTask taskobj = new AddTask();
        taskobj.taskStatus = "To Do";
        taskobj.developerFirstName = "John";
        taskobj.developerLastName = "Doe";
        taskobj.taskNumber = 1;
        taskobj.taskName = "Sample Task";
        taskobj.taskDescription = "This is a sample task description.";

        // Mock the JOptionPane class to capture the dialog being displayed
        JOptionPaneWrapper jOptionPaneWrapper = new JOptionPaneWrapper();
        addTask.setJOptionPane(jOptionPaneWrapper);

        // Act
        addTask.printTaskDetails(taskobj);

        // Assert
        String expectedOutput = "Task Status: To Do\n" +
                "Developer Name: John\n" +
                "Developer Last Name: Doe\n" +
                "Task Number: 1\n" +
                "Task Name: Sample Task\n" +
                "Task Description: This is a sample task description.\n" +
                "Task ID: SA:1:DOE\n" +
                "Task Duration: 0 Hrs";
        assertEquals(expectedOutput, jOptionPaneWrapper.getMessage());
        assertEquals(JOptionPane.PLAIN_MESSAGE, jOptionPaneWrapper.getMessageType());
    }

    @Test
    public void testReturnTotalHours() {
        // Arrange
        addTask.totalHours = 10;

        // Mock the JOptionPane class to capture the dialog being displayed
        JOptionPaneWrapper jOptionPaneWrapper = new JOptionPaneWrapper();
        addTask.setJOptionPane(jOptionPaneWrapper);

        // Act
        addTask.returnTotalHours();

        // Assert
        assertEquals("The Total Hours Of All The Tasks Are: 10 Hrs", jOptionPaneWrapper.getMessage());
        assertEquals(JOptionPane.INFORMATION_MESSAGE, jOptionPaneWrapper.getMessageType());
    }
}