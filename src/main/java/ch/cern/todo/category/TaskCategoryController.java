package ch.cern.todo.category;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/categories")
public class TaskCategoryController {
    private final TaskCategoryRepository repository;

    public TaskCategoryController(TaskCategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<TaskCategory> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public TaskCategory newTask(@RequestBody TaskCategory newCategory) {
        return repository.save(newCategory);
    }

    @GetMapping("/{id}")
    public TaskCategory getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new TaskCategoryNotFoundException(id));
    }

    @PutMapping("/{id}")
    public TaskCategory update(@RequestBody TaskCategory newCategory, @PathVariable Long id) {

        return repository.findById(id)
                .map(category -> {
                    category.setCategoryName(newCategory.getCategoryName());
                    category.setCategoryDescription(newCategory.getCategoryDescription());
                    return repository.save(category);
                }).orElseThrow(() -> new TaskCategoryNotFoundException(id));

    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        repository.findById(id).orElseThrow(() -> new TaskCategoryNotFoundException(id));
        repository.deleteById(id);
    }
}
