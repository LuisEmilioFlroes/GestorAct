package gt.edu.umg.taskmanager.service.structure;

import gt.edu.umg.database.model.Task;
import java.util.List;

public interface TaskListService {
    void addTask(Long userId, Task task);
    List<Task> getTasks(Long userId);
    void removeTask(Long userId, Long taskId);
}
