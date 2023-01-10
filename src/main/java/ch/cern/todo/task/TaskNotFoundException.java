package ch.cern.todo.task;

public class TaskNotFoundException extends RuntimeException {
    TaskNotFoundException(long id) {
        super("Could not find task: " + id);
    }
}
