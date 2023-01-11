package ch.cern.todo.category;

public class TaskCategoryNotFoundException extends RuntimeException {
    public TaskCategoryNotFoundException(Long id) {
        super("Could not find category: " + id);
    }

}
