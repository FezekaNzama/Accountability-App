package com.fezekanzama.accountable_app.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.fezekanzama.accountable_app.entity.AccountabilityPartner;
import com.fezekanzama.accountable_app.entity.GoalSetter;
import com.fezekanzama.accountable_app.exception.EntityNotFoundException;
import com.fezekanzama.accountable_app.repository.AccountabilityPartnerRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AccountabilityPartnerServiceImplementation implements AccountabilityPartnerService{

    private AccountabilityPartnerRepository accountabilityPartnerRepository;

    @Override
    public AccountabilityPartner getAccountabilityPartner(Long id) {
        Optional<AccountabilityPartner> accountabilityPartner = accountabilityPartnerRepository.findById(id);
        return unwrapAccountabilityPartner(accountabilityPartner, id);
    }

    @Override
    public AccountabilityPartner saveAccountabilityPartner(AccountabilityPartner accountabilityPartner) {
        return accountabilityPartnerRepository.save(accountabilityPartner);
    }

    @Override
    public AccountabilityPartner updateAccountabilityPartner(Long id, AccountabilityPartner accountabilityPartner) {
        Optional<AccountabilityPartner> originalAccountaccountabilityPartner = accountabilityPartnerRepository.findById(id);
        AccountabilityPartner unwrappedAccountabilityPartner = unwrapAccountabilityPartner(originalAccountaccountabilityPartner, id);
        unwrappedAccountabilityPartner.setEmail(accountabilityPartner.getEmail());
        unwrappedAccountabilityPartner.setFirstname(accountabilityPartner.getFirstname());
        unwrappedAccountabilityPartner.setLastname(accountabilityPartner.getLastname());
        return accountabilityPartnerRepository.save(unwrappedAccountabilityPartner);
    }

    @Override
    public void deleteAccountabilityPartner(Long id) {
        accountabilityPartnerRepository.deleteById(id);
    }

    @Override
    public List<AccountabilityPartner> getAccountabilityPartners() {
        return (List<AccountabilityPartner>)accountabilityPartnerRepository.findAll();
    }

    @Override
    public Set<GoalSetter> getAssociatedGoalSetters(Long id) {
       AccountabilityPartner accountabilityPartner = getAccountabilityPartner(id);
       return accountabilityPartner.getGoalSetters();
    }

    static AccountabilityPartner unwrapAccountabilityPartner(Optional<AccountabilityPartner> entity, Long id){
        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, AccountabilityPartner.class);
    }
    
}
