package com.fezekanzama.accountable_app.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "draft_goal")
public class DraftGoal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @NotBlank(message = "draft goal cannot be blank")
    @NonNull
    @Column(name = "goal")
    Goal goal;

    public DraftGoal(){}

    public DraftGoal(Goal goal){
        this.goal = goal;
    }

}
