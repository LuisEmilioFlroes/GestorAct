package gt.edu.umg.taskmanager.util;

import java.time.LocalDateTime;

public class Action {

    public enum ActionType {
        CREATE, UPDATE, DELETE
    }

    private ActionType type;
    private String description;
    private LocalDateTime timestamp;

    public Action(ActionType type, String description) {
        this.type = type;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public ActionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + type + ": " + description;
    }
}
