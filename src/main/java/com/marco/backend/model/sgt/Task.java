package com.marco.backend.model.sgt;

import java.time.LocalDate;

import org.hibernate.annotations.SQLRestriction;

import com.marco.backend.enums.Priority;
import com.marco.backend.enums.Status;
import com.marco.backend.model.base.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("is_ativo <> false")
public class Task extends AbstractEntity{

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String responsible;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDate deadline;

    @Enumerated(EnumType.STRING)
    private Status status = Status.IN_PROGRESS;

    @Override
    protected AbstractEntity map(AbstractEntity a) {
        Task task = (Task) a;
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.responsible = task.getResponsible();
        this.priority = task.getPriority();
        this.deadline = task.getDeadline();
        this.status = task.getStatus();
        return this;
    }

    
}
