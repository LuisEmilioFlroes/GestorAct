package gt.edu.umg.taskmanager.service;

import gt.edu.umg.database.model.Task;

import java.util.LinkedList;
import java.util.Queue;

public class ColaProcesamiento {

    private Queue<Task> taskQueue;

    public ColaProcesamiento() {
        this.taskQueue = new LinkedList<>();
    }

    public void enqueueTask(Task task) {
        taskQueue.offer(task);
    }

    public Task dequeueTask() {
        return taskQueue.poll();
    }

    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }

    public int size() {
        return taskQueue.size();
    }
}
