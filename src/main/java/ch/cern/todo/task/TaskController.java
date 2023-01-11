package ch.cern.todo.task;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.cern.todo.category.TaskCategory;
import ch.cern.todo.category.TaskCategoryNotFoundException;
import ch.cern.todo.category.TaskCategoryRepository;

@RestController
@RequestMapping(path = "/api/tasks")
public class TaskController {
    private final TaskRepository repository;
    private final TaskCategoryRepository categoryRepository;

    public TaskController(TaskRepository repository, TaskCategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Task> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Task newTask(@RequestBody Task newTask) {
        long categoryId = newTask.getCategory().getCategoryId();
        TaskCategory c = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new TaskCategoryNotFoundException(categoryId));
        newTask.setCategory(c);
        return repository.save(newTask);
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    @PutMapping("/{id}")
    Task update(@RequestBody Task newTask, @PathVariable Long id) {

        return repository.findById(id)
                .map(task -> {
                    task.setTaskName(newTask.getTaskName());
                    task.setTaskDescription(newTask.getTaskDescription());
                    task.setDeadline(newTask.getDeadline());

                    long categoryId = newTask.getCategory().getCategoryId();
                    TaskCategory c = categoryRepository.findById(categoryId)
                            .orElseThrow(() -> new TaskCategoryNotFoundException(categoryId));
                    task.setCategory(c);
                    return repository.save(task);
                }).orElseThrow(() -> new TaskNotFoundException(id));

    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        repository.deleteById(id);
    }
}
