package ch.cern.todo.category;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskCategoryController {
    private final TaskCategoryRepository repository;

    public TaskCategoryController(TaskCategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/categories")
    public List<TaskCategory> all() {
        return repository.findAll();
    }

    @PostMapping("/categories")
    public TaskCategory newTask(@RequestBody TaskCategory newCategory) {
        return repository.save(newCategory);
    }

    @GetMapping("/categories/{id}")
    public TaskCategory one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new TaskCategoryNotFoundException(id));
    }

    @PutMapping("/categories/{id}")
    public TaskCategory update(@RequestBody TaskCategory newCategory, @PathVariable Long id) {

        return repository.findById(id)
                .map(category -> {
                    category.setCategoryName(newCategory.getCategoryName());
                    category.setCategoryDescription(newCategory.getCategoryDescription());
                    return repository.save(category);
                }).orElseThrow(() -> new TaskCategoryNotFoundException(id));

    }

    @DeleteMapping("/categories/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
