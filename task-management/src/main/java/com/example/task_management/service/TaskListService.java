package com.example.task_management.service;

import com.example.task_management.model.TaskList;
import com.example.task_management.repository.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskListService {

    private final TaskListRepository taskListRepository;

    @Autowired
    public TaskListService(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    public List<TaskList> getAllTaskLists() {
        return taskListRepository.findAll();
    }

    public Optional<TaskList> getTaskListById(Long id) {
        return taskListRepository.findById(id);
    }

    public TaskList saveTaskList(TaskList taskList) {
        return taskListRepository.save(taskList);
    }

    public void deleteTaskList(Long id) {
        taskListRepository.deleteById(id);
    }

    // Adaugă metode suplimentare pentru logica de afaceri specifică listelor de sarcini.
}
