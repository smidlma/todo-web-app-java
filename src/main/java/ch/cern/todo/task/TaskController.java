package ch.cern.todo.task;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/tasks")
    public List<Task> all() {
        return repository.findAll();
    }

    @PostMapping("/tasks")
    public Task newTask(@RequestBody Task newTask) {
        return repository.save(newTask);
    }

    @GetMapping("/tasks/{id}")
    public Task one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    @PutMapping("/tasks/{id}")
    Task update(@RequestBody Task newTask, @PathVariable Long id) {

        return repository.findById(id)
                .map(task -> {
                    task.setTaskName(newTask.getTaskName());
                    task.setTaskDescription(newTask.getTaskDescription());
                    task.setDeadline(newTask.getDeadline());
                    return repository.save(task);
                }).orElseThrow(() -> new TaskNotFoundException(id));

    }

    @DeleteMapping("/tasks/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
