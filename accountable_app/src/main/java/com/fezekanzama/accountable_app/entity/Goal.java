package com.fezekanzama.accountable_app.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "goal")
public class Goal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Goal cannot be blank")
    @NonNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = true)
    private String description;

    @NonNull
    @Column(name = "reward", nullable = false)
    private Long reward;

    @NonNull
    @Column(name = "isComplete", nullable = false)
    private boolean complete;

    @ManyToOne(optional = false)
    @JoinColumn(name = "goalSetter_id", referencedColumnName = "id")
    private GoalSetter goalSetter;

    @ManyToOne(optional = false)
    @JoinColumn(name = "accountabilityPartner_id", referencedColumnName = "id")
    private AccountabilityPartner accountabilityPartner;

    public void addToReward(Long amount){
        setReward(getReward()+amount);
    }   
}
