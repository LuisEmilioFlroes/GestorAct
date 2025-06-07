package gt.edu.umg.taskmanager.util;

import gt.edu.umg.database.model.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskTreeNode {
    private Task task;
    private List<TaskTreeNode> subtasks;

    public TaskTreeNode(Task task) {
        this.task = task;
        this.subtasks = new ArrayList<>();
    }

    public Task getTask() {
        return task;
    }

    public List<TaskTreeNode> getSubtasks() {
        return subtasks;
    }

    public void addSubtask(TaskTreeNode subtask) {
        this.subtasks.add(subtask);
    }
}
