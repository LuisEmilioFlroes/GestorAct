package gt.edu.umg.taskmanager.service;

import gt.edu.umg.database.model.Task;
import gt.edu.umg.taskmanager.util.TaskTreeNode;

import java.util.Optional;
import java.util.UUID;

public class GestorJerarquico {

    private TaskTreeNode root;

    public GestorJerarquico(Task rootTask) {
        this.root = new TaskTreeNode(rootTask);
    }

    public TaskTreeNode getRoot() {
        return root;
    }

    /**
     * Agrega una sub-tarea a una tarea padre identificada por su ID.
     *
     * @param parentId UUID de la tarea padre
     * @param subtask tarea a agregar como subtarea
     * @return true si se agregó correctamente, false si no se encontró el padre
     */
    public boolean addSubtask(UUID parentId, Task subtask) {
        Optional<TaskTreeNode> parentNode = findNode(root, parentId);

        if (parentNode.isPresent()) {
            TaskTreeNode parent = parentNode.get();

            // Evitar NullPointer incluso si se daña el estado
            if (parent.getTask().getSubtasks() == null) {
                parent.getTask().setSubtasks(new java.util.ArrayList<>());
            }

            parent.getTask().getSubtasks().add(subtask);
            subtask.setParent(parent.getTask()); // establecer el padre

            parent.addSubtask(new TaskTreeNode(subtask)); // en el árbol
            return true;
        }

        return false;
    }

    /**
     * Busca un nodo por su ID en el árbol de tareas
     */
    private Optional<TaskTreeNode> findNode(TaskTreeNode current, UUID taskId) {
        if (current.getTask().getId().equals(taskId)) {
            return Optional.of(current);
        }

        for (TaskTreeNode child : current.getSubtasks()) {
            Optional<TaskTreeNode> found = findNode(child, taskId);
            if (found.isPresent()) {
                return found;
            }
        }

        return Optional.empty();
    }

    /**
     * Imprime el árbol de tareas jerárquicamente
     */
    public void printTree() {
        printTree(root, "");
    }

    public void printTree(TaskTreeNode node, String indent) {
        System.out.println(indent + "- " + node.getTask().getTitulo());
        for (TaskTreeNode child : node.getSubtasks()) {
            printTree(child, indent + "  ");
        }
    }
}
