package com.example.task_management.api;

import com.example.task_management.model.TaskList;
import com.example.task_management.model.User;
import com.example.task_management.service.TaskListService;
import com.example.task_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasklists")
public class TaskListController {

    private final TaskListService taskListService;
    private final UserService userService;

    @Autowired
    public TaskListController(TaskListService taskListService, UserService userService) {
        this.taskListService = taskListService;
        this.userService = userService;
    }

    @GetMapping
    public List<TaskList> getAllTaskLists() {
        return taskListService.getAllTaskLists();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskList> getTaskListById(@PathVariable Long id) {
        Optional<TaskList> taskList = taskListService.getTaskListById(id);
        return taskList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TaskList> createTaskList(@RequestBody TaskList taskList) {
        // Verifică dacă user-ul asociat există
        Optional<User> user = userService.getUserById(taskList.getUser().getId());
        if (user.isPresent()) {
            taskList.setUser(user.get()); // Setează user-ul existent la taskList
            TaskList savedTaskList = taskListService.saveTaskList(taskList);
            return ResponseEntity.ok(savedTaskList);
        } else {
            return ResponseEntity.badRequest().body(null); // Returnează un răspuns Bad Request dacă user-ul nu există
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskList> updateTaskList(@PathVariable Long id, @RequestBody TaskList taskList) {
        Optional<TaskList> existingTaskList = taskListService.getTaskListById(id);
        if (existingTaskList.isPresent()) {
            taskList.setId(id);

            // Verifică dacă user-ul asociat există
            Optional<User> user = userService.getUserById(taskList.getUser().getId());
            if (user.isPresent()) {
                taskList.setUser(user.get()); // Setează user-ul existent la taskList
                return ResponseEntity.ok(taskListService.saveTaskList(taskList));
            } else {
                return ResponseEntity.badRequest().body(null); // Returnează un răspuns Bad Request dacă user-ul nu există
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskList(@PathVariable Long id) {
        if (taskListService.getTaskListById(id).isPresent()) {
            taskListService.deleteTaskList(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
