package com.fezekanzama.accountable_app.entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "goalSetter")
public class GoalSetter{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; 

    @NotBlank(message = "Firstname cannot be blank")
    @NonNull
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @NotBlank(message = "Lastname cannot be blank")
    @NonNull
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @NotBlank(message = "Email cannot be blank")
    @NonNull
    @Column(name = "email", nullable = false)
    private String email; 

    @Embedded
    private Wallet wallet; 

    @JsonIgnore
    @OneToMany(mappedBy = "goalSetter", cascade = CascadeType.ALL)
    private List<Goal> goals;

    /* 
    @JsonIgnore
    @OneToMany(mappedBy = "goalSetter", cascade = CascadeType.ALL)
    private List<Goal> updateRequest; 
    */

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "goalSetter_accountabilityPartner",
        joinColumns = @JoinColumn(name = "goalSetter_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "accountabilityPartner_id", referencedColumnName = "id")
    )
    private Set<AccountabilityPartner> accountabilityPartners;

}
