package com.fezekanzama.accountable_app.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.fezekanzama.accountable_app.entity.AccountabilityPartner;
import com.fezekanzama.accountable_app.entity.GoalSetter;
import com.fezekanzama.accountable_app.entity.Wallet;
import com.fezekanzama.accountable_app.exception.EntityNotFoundException;
import com.fezekanzama.accountable_app.repository.GoalSetterRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GoalSetterServiceImplementation implements GoalSetterService{

    private GoalSetterRepository goalSetterRepository;

    @Override
    public GoalSetter getGoalSetter(Long id) {
        Optional<GoalSetter> goalSetter  = goalSetterRepository.findById(id);
        return unwrapGoalSetter(goalSetter, id);
    }

    @Override
    public GoalSetter saveGoalSetter(GoalSetter goalSetter) {
        return goalSetterRepository.save(goalSetter);
    }

    @Override
    public GoalSetter updateGoalSetter(Long id, GoalSetter goalSetter) {
       Optional<GoalSetter> originalGoalSetter = goalSetterRepository.findById(id);
       GoalSetter unwrappedGoalSetter = unwrapGoalSetter(originalGoalSetter, id);
       unwrappedGoalSetter.setEmail(goalSetter.getEmail());
       unwrappedGoalSetter.setFirstname(goalSetter.getFirstname());
       unwrappedGoalSetter.setLastname(goalSetter.getLastname());
       return goalSetterRepository.save(unwrappedGoalSetter);
    }

    @Override
    public void deleteGoalSetter(Long id) {
        goalSetterRepository.deleteById(id);
    }

    @Override
    public List<GoalSetter> getGoalSetters() {
        return (List<GoalSetter>)goalSetterRepository.findAll();
    }

    static GoalSetter unwrapGoalSetter(Optional<GoalSetter> entity, Long id){
        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, GoalSetter.class);
    }

    @Override
    public Set<AccountabilityPartner> getAssociatedAccountabilityPartners(Long id) {
        GoalSetter goalSetter = getGoalSetter(id);
        return goalSetter.getAccountabilityPartners();
    }

    @Override
    public GoalSetter saveGoalSetterWallet(Long id, Wallet wallet) {
        Optional<GoalSetter> originalGoalSetter = goalSetterRepository.findById(id);
        GoalSetter unwrappedGoalSetter = unwrapGoalSetter(originalGoalSetter, id);
        unwrappedGoalSetter.setWallet(wallet);
        return goalSetterRepository.save(unwrappedGoalSetter);
    }

}
