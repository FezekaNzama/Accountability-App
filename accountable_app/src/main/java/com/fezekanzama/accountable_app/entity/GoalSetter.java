package com.fezekanzama.accountable_app.entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Goal_Setter")
public class GoalSetter extends User { 

    @Embedded
    private Wallet wallet; 


    @JsonIgnore
    @OneToMany(mappedBy = "goalSetter", cascade = CascadeType.ALL)
    private List<Goal> goals;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "goalSetter_accountabilityPartner",
        joinColumns = @JoinColumn(name = "goalSetter_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "accountabilityPartner_id", referencedColumnName = "id")
    )
    private Set<AccountabilityPartner> accountabilityPartners;

}
