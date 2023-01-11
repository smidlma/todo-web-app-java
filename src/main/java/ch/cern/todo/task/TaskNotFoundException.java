package ch.cern.todo.task;

public class TaskNotFoundException extends RuntimeException {
    TaskNotFoundException(Long id) {
        super("Could not find task: " + id);
    }
}
