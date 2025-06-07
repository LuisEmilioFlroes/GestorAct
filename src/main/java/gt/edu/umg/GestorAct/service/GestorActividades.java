package gt.edu.umg.taskmanager.service;

import gt.edu.umg.database.model.Task;
import gt.edu.umg.database.repository.TaskRepository;
import gt.edu.umg.rabbit.messaging.TaskEventPublisher;
import gt.edu.umg.taskmanager.util.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GestorActividades {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PilaOperaciones actionStack;

    @Autowired
    private TaskEventPublisher taskEventPublisher;


    public List<Task> obtenerTodas() {
        return taskRepository.findAll();
    }


    public Task crearTarea(Task tarea) {
        tarea.setCreatedAt(LocalDateTime.now());
        Task tareaGuardada = taskRepository.save(tarea);
        taskEventPublisher.publicarCreacionTarea(tareaGuardada);


        actionStack.pushAction(new Action(Action.ActionType.CREATE, "Tarea creada: " + tareaGuardada.getTitulo()));
        return tareaGuardada;
    }


    public Task crearSubtarea(UUID parentId, Task subtarea) {
        Optional<Task> tareaPadre = taskRepository.findById(parentId);
        if (tareaPadre.isPresent()) {
            subtarea.setParent(tareaPadre.get());
            Task subtareaGuardada = taskRepository.save(subtarea);
            taskEventPublisher.publicarCreacionTarea(subtareaGuardada);

            // Registrar acción
            actionStack.pushAction(new Action(Action.ActionType.CREATE, "Subtarea creada: " + subtareaGuardada.getTitulo()));
            return subtareaGuardada;
        }
        return null;
    }

    // Obtener solo tareas raíz (sin padre)
    public List<Task> obtenerTareasRaiz() {
        return taskRepository.findByParentIsNull();
    }

    // Obtener subtareas de una tarea específica
    public List<Task> obtenerSubtareas(UUID parentId) {
        return taskRepository.findByParentId(parentId);
    }

    // Actualizar tarea
    public Task actualizarTarea(UUID id, Task tareaActualizada) {
        Optional<Task> optionalTarea = taskRepository.findById(id);
        if (optionalTarea.isPresent()) {
            Task tareaExistente = optionalTarea.get();
            tareaExistente.setTitulo(tareaActualizada.getTitulo());
            tareaExistente.setDescripcion(tareaActualizada.getDescripcion());
            tareaExistente.setEstado(tareaActualizada.getEstado());


            actionStack.pushAction(new Action(Action.ActionType.UPDATE, "Tarea actualizada: " + tareaExistente.getTitulo()));
            return taskRepository.save(tareaExistente);
        }
        return null;
    }


    public boolean eliminarTarea(UUID id) {
        Optional<Task> optionalTarea = taskRepository.findById(id);
        if (optionalTarea.isPresent()) {
            Task tarea = optionalTarea.get();
            taskRepository.deleteById(id);

            // Registrar acción
            actionStack.pushAction(new Action(Action.ActionType.DELETE, "Tarea eliminada: " + tarea.getTitulo()));
            return true;
        }
        return false;
    }


    public List<Task>(String estado) {
        return taskRepository.findByEstado(estado);
    }

    // Deshacer la última acción
    public void deshacerUltimaAccion() {
        Action ultimaAccion = actionStack.popAction();
        if (ultimaAccion == null) {
            System.out.println("No hay acciones por deshacer.");
            return;
        }

        switch (ultimaAccion.getType()) {
            case CREATE:
                System.out.println("Se desharía la creación: " + ultimaAccion.getDescription());
                break;
            case DELETE:
                System.out.println("Se desharía la eliminación: " + ultimaAccion.getDescription());
                break;
            case UPDATE:
                System.out.println("Se desharía la actualización: " + ultimaAccion.getDescription());
                break;
        }
    }
}
