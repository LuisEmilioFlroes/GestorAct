package gt.edu.umg.taskmanager.service.structure.impl;

import gt.edu.umg.database.model.Task;
import gt.edu.umg.taskmanager.service.structure.TaskListService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final Map<Long, List<Task>> taskMap = new HashMap<>();

    @Override
    public void addTask(Long userId, Task task) {
        taskMap.computeIfAbsent(userId, k -> new ArrayList<>()).add(task);
    }

    @Override
    public List<Task> getTasks(Long userId) {
        return taskMap.getOrDefault(userId, Collections.emptyList());
    }

    @Override
    public void removeTask(Long userId, Long taskId) {
        List<Task> tasks = taskMap.get(userId);
        if (tasks != null) {
            tasks.removeIf(t -> t.getId().equals(taskId));
        }
    }
}
