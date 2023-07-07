
package prog.poe.complete;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LoginTest {

    @Test
    public void testLogin_Successful() {
        // Arrange
        Login.userName = "kyl_1";
        Login.password = "Ch&&sec@ke99!";

        // Act
        boolean result = Login.loginUser();

        // Assert
        assertTrue(result);
    }

    @Test
    public void testLogin_Failed() {
        // Arrange
        Login.userName = "kyl_1";
        Login.password = "incorrect_password";

        // Act
        boolean result = Login.loginUser();

        // Assert
        assertFalse(result);
    }

    @Test
    public void testCheckUserName_CorrectlyFormatted() {
        // Arrange
        Login.userName = "kyl_1";

        // Act
        boolean result = Login.checkUserName();

        // Assert
        assertTrue(result);
    }

    @Test
    public void testCheckUserName_IncorrectlyFormatted() {
        // Arrange
        Login.userName = "kyle!!!!!!!";

        // Act
        boolean result = Login.checkUserName();

        // Assert
        assertFalse(result);
    }

    @Test
    public void testCheckPasswordComplexity_MeetsRequirements() {
        // Arrange
        Login.password = "Ch&&sec@ke99!";

        // Act
        boolean result = Login.checkPasswordComplexity();

        // Assert
        assertTrue(result);
    }

    @Test
    public void testCheckPasswordComplexity_DoesNotMeetRequirements() {
        // Arrange
        Login.password = "password";

        // Act
        boolean result = Login.checkPasswordComplexity();

        // Assert
        assertFalse(result);
    }
}


