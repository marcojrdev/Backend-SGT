package com.marco.backend.repository.sgt;

import java.util.List;

import com.marco.backend.enums.Status;
import com.marco.backend.model.sgt.Task;
import com.marco.backend.repository.base.GenericRepository;

public interface TaskRepository extends GenericRepository<Task, Long>{
    List<Task> findByStatus(Status status);
}
