package prog.poe.complete;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    public void setup() {
        taskManager = new TaskManager();
    }

    @Test
    public void testDeveloperArray_PopulatedCorrectly() {
        // Arrange
        AddTask task1 = createTask("Mike Smith");
        AddTask task2 = createTask("Edward Harrington");
        AddTask task3 = createTask("Samantha Paulson");
        AddTask task4 = createTask("Glenda Oberholzer");

        // Act
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        taskManager.addTask(task4);

        // Assert
        List<String> expectedDeveloperNames = List.of("Mike Smith", "Edward Harrington", "Samantha Paulson", "Glenda Oberholzer");
        assertEquals(expectedDeveloperNames, taskManager.getDeveloperNames());
    }

    @Test
    public void testDisplayDeveloperAndDuration_LongestDurationTask() {
        // Arrange
        AddTask task1 = createTask("Mike Smith");
        AddTask task2 = createTask("Edward Harrington");
        AddTask task3 = createTask("Samantha Paulson");
        AddTask task4 = createTask("Glenda Oberholzer");

        task1.taskDuration = new int[]{5};
        task2.taskDuration = new int[]{8};
        task3.taskDuration = new int[]{4};
        task4.taskDuration = new int[]{11};

        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        taskManager.addTask(task4);

        // Act
        String result = taskManager.displayLongestTask();

        // Assert
        assertEquals("Glenda Oberholzer, 11", result);
    }

    @Test
    public void testSearchTaskByName() {
        // Arrange
        AddTask task1 = createTask("Mike Smith");
        AddTask task2 = createTask("Edward Harrington");
        AddTask task3 = createTask("Samantha Paulson");
        AddTask task4 = createTask("Glenda Oberholzer");

        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        taskManager.addTask(task4);

        // Act
        taskManager.searchTaskByName("Create Login");
        // No assertions for dialog-based interactions

        // Assert
        assertEquals(1, JOptionPane.getFrameForComponent(null).getComponentCount());
    }

    @Test
    public void testSearchTasksByDeveloper() {
        // Arrange
        AddTask task1 = createTask("Mike Smith");
        AddTask task2 = createTask("Edward Harrington");
        AddTask task3 = createTask("Samantha Paulson");
        AddTask task4 = createTask("Glenda Oberholzer");

        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        taskManager.addTask(task4);

        // Act
        taskManager.searchTasksByDeveloper("Samantha Paulson");
        // No assertions for dialog-based interactions

        // Assert
        assertEquals(1, JOptionPane.getFrameForComponent(null).getComponentCount());
    }

    @Test
    public void testDeleteTaskFromArray() {
        // Arrange
        AddTask task1 = createTask("Mike Smith");
        AddTask task2 = createTask("Edward Harrington");
        AddTask task3 = createTask("Samantha Paulson");
        AddTask task4 = createTask("Glenda Oberholzer");

        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        taskManager.addTask(task4);

        // Act
        taskManager.deleteTaskByName("Create Reports");

        // Assert
        List<String> expectedTaskNames = List.of("Create Mike's Task", "Create Edward's Task", "Create Samantha's Task", "Create Glenda's Task");
        assertEquals(expectedTaskNames, taskManager.getTaskNames());
    }

    private AddTask createTask(String developerName) {
        AddTask task = new AddTask();
        task.developerFirstName = developerName;
        task.taskName = "Create " + developerName.split(" ")[0] + "'s Task";
        return task;
    }
}
