package gt.edu.umg.taskmanager.controller;

import gt.edu.umg.taskmanager.service.GestorActividades;
import gt.edu.umg.database.model.Task;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")  // <- CAMBIADO a /api/tasks
public class ControladorActividades {

    @Autowired
    private GestorActividades taskService;

    @GetMapping
    public List<Task> obtenerTareas() {
        return taskService.obtenerTodas();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> actualizarTarea(@PathVariable UUID id, @RequestBody Task tareaActualizada) {
        Task tarea = taskService.actualizarTarea(id, tareaActualizada);
        return tarea != null ? ResponseEntity.ok(tarea) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable UUID id) {
        boolean eliminada = taskService.eliminarTarea(id);
        return eliminada ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
    @PostMapping
    public Task crearTarea(@RequestBody @Valid Task tarea) {
        return taskService.crearTarea(tarea);
    }
}
