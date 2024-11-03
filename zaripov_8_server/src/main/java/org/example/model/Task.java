package org.example.model;

import java.util.ArrayList;
import java.util.List;

public record Task(String id, String name, String description) {

    private static List<Task> tasks = new ArrayList<>(List.of(
            new Task("1", "Home", "Clean board"),
            new Task("2", "Work", "make task number 2"),
            new Task("3", "School", "Make homework")
    ));

    public static Task findById(String id) {
        return tasks.stream().filter(task -> task.id.equals(id)).findFirst().orElse(null);
    }

    public static void addTask(Task task) {
        tasks.add(task);
    }

    public static Task deleteTask(String id) {
        Task task = findById(id);
        tasks.remove(task);
        return task;
    }

}
