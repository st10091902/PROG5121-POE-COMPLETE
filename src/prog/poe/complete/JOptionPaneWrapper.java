package prog.poe.complete;

public class JOptionPaneWrapper {
    private String message;
    private int messageType;

    public void showMessageDialog(Object parentComponent, String message, String title, int messageType) {
        this.message = message;
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public int getMessageType() {
        return messageType;
    }
}

