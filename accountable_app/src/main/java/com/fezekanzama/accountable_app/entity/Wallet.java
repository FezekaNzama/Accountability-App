//This Entity is meant to mirror what a bank account associated with the GoalSetter might look like 
package com.fezekanzama.accountable_app.entity;

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
public class Wallet {
    
    private Long balance;

    public void withdraw(Long withdrawalAmount){
        setBalance(getBalance() - withdrawalAmount);
    }

    public void deposit(Long depositAmount){
        setBalance(getBalance()+depositAmount);
    }
}
