package com.marco.backend.service.sgt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marco.backend.enums.Status;
import com.marco.backend.model.sgt.Task;
import com.marco.backend.repository.base.GenericRepository;
import com.marco.backend.repository.sgt.TaskRepository;
import com.marco.backend.service.base.ServiceGenerico;

@Service
public class TaskService extends ServiceGenerico<Task, Long>{

    @Autowired
    TaskRepository taskRepo;

    @Override
    public GenericRepository<Task, Long> getRepository() {
        return taskRepo;
    }

    public List<Task> getTasksByStatus(Status status) {
        return taskRepo.findByStatus(status);
    }

    public Task completeTask(Long id) {
        Task t = taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        t.setStatus(Status.COMPLETED);
        return taskRepo.save(t);
    }

}
