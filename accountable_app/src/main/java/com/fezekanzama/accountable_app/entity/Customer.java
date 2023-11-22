package com.fezekanzama.accountable_app.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Customer")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
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
}
