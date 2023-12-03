//This Entity is meant to mirror what a bank account associated with the GoalSetter might look like 
package com.fezekanzama.accountable_app.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor 
@NoArgsConstructor
@Embeddable
public class Wallet implements Serializable{
    
    private int balance;

    public void withdraw(int withdrawalAmount){
        setBalance(getBalance() - withdrawalAmount);
    }

    public void deposit(int depositAmount){
        setBalance(getBalance()+depositAmount);
    }
}
