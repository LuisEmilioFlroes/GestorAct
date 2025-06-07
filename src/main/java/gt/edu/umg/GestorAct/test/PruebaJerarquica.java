package gt.edu.umg.taskmanager.test;

import gt.edu.umg.database.model.Task;
import gt.edu.umg.taskmanager.service.PilaOperaciones;
import gt.edu.umg.taskmanager.service.GestorJerarquico;
import gt.edu.umg.taskmanager.util.Action;

import java.util.UUID;

public class PruebaJerarquica {
    public static void main(String[] args) {
        // Crear pila de acciones
        PilaOperaciones actionStack = new PilaOperaciones();

        // Crear la tarea raíz
        Task rootTask = new Task();
        rootTask.setId(UUID.randomUUID()); // Asignar ID
        rootTask.setTitulo("Proyecto Final");

        // Servicio
        GestorJerarquico treeService = new GestorJerarquico(rootTask);
        actionStack.pushAction(new Action(Action.ActionType.CREATE, "Creada tarea raíz: " + rootTask.getTitulo()));

        // Subtareas
        Task tarea1 = new Task();
        tarea1.setId(UUID.randomUUID()); // Asignar ID
        tarea1.setTitulo("Diseño");
        treeService.addSubtask(rootTask.getId(), tarea1);
        actionStack.pushAction(new Action(Action.ActionType.CREATE, "Agregada subtarea: " + tarea1.getTitulo()));

        Task tarea2 = new Task();
        tarea2.setId(UUID.randomUUID()); // Asignar ID
        tarea2.setTitulo("Implementación");
        treeService.addSubtask(rootTask.getId(), tarea2);
        actionStack.pushAction(new Action(Action.ActionType.CREATE, "Agregada subtarea: " + tarea2.getTitulo()));

        Task tarea3 = new Task();
        tarea3.setId(UUID.randomUUID()); // Asignar ID
        tarea3.setTitulo("Pruebas");
        treeService.addSubtask(rootTask.getId(), tarea3);
        actionStack.pushAction(new Action(Action.ActionType.CREATE, "Agregada subtarea: " + tarea3.getTitulo()));

        // Sub-subtarea
        Task tarea2_1 = new Task();
        tarea2_1.setId(UUID.randomUUID()); // Asignar ID
        tarea2_1.setTitulo("Controlador");
        treeService.addSubtask(tarea2.getId(), tarea2_1);
        actionStack.pushAction(new Action(Action.ActionType.CREATE, "Agregada sub-subtarea: " + tarea2_1.getTitulo()));

        // Imprimir árbol
        System.out.println("\nÁrbol de Tareas:");
        treeService.printTree(treeService.getRoot(), "");

        // Imprimir acciones
        System.out.println("\nHistorial de Acciones:");
        actionStack.printActions();
    }
}
