package com.fezekanzama.accountable_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fezekanzama.accountable_app.entity.AccountabilityPartner;
import com.fezekanzama.accountable_app.entity.Goal;
import com.fezekanzama.accountable_app.entity.GoalSetter;
import com.fezekanzama.accountable_app.exception.EntityNotFoundException;
import com.fezekanzama.accountable_app.repository.AccountabilityPartnerRepository;
import com.fezekanzama.accountable_app.repository.GoalRepository;
import com.fezekanzama.accountable_app.repository.GoalSetterRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GoalServiceImplementation implements GoalService{

    private GoalRepository goalRepository;
    private GoalSetterRepository goalSetterRepository;
    private AccountabilityPartnerRepository accountabilityPartnerRepository;


    @Override
    public Goal getGoal(Long id) {
        Optional<Goal> goal = goalRepository.findById(id);
        return unwrapGoal(goal, id);
    }

    @Override
    public Goal saveGoal(Goal goal, Long goalSetterId, Long accountabilityPartnerId) {
       GoalSetter goalSetter = GoalSetterServiceImplementation.unwrapGoalSetter(goalSetterRepository.findById(goalSetterId), goalSetterId);
       AccountabilityPartner accountabilityPartner = AccountabilityPartnerServiceImplementation.unwrapAccountabilityPartner(accountabilityPartnerRepository.findById(accountabilityPartnerId), accountabilityPartnerId);
       goal.setGoalSetter(goalSetter);
       //Goal Reward is set when you pass in the goal 
       //just update goalsetter wallet to reflect this 
       goalSetter.getWallet().withdraw(goal.getReward());
       goal.setAccountabilityPartner(accountabilityPartner);
       return goalRepository.save(goal);
    }

    @Override
    public Goal updateGoal(Long id, Goal goal, Long accountabilityPartnerId) {
        Optional<Goal> optionalGoal = goalRepository.findById(id);
        Goal unwrappedGoal = unwrapGoal(optionalGoal, id);
        unwrappedGoal.setTitle(goal.getTitle());
        unwrappedGoal.setDescription(goal.getDescription());
        unwrappedGoal.setReward(goal.getReward());
        unwrappedGoal.setAccountabilityPartner(AccountabilityPartnerServiceImplementation.unwrapAccountabilityPartner(accountabilityPartnerRepository.findById(accountabilityPartnerId), accountabilityPartnerId));
        unwrappedGoal.setComplete(goal.isComplete());
        return goalRepository.save(unwrappedGoal);

    }

    @Override
    public void deleteGoal(Long id) {
        goalRepository.deleteById(id);
    }

    @Override
    public List<Goal> getGoalSetterGoals(Long goalSetterId) {
        return goalRepository.findByGoalSetterId(goalSetterId);
    }

    @Override
    public List<Goal> getAccountabilityPartnerGoals(Long accountabilityPartnerId) {
        return goalRepository.findByAccountabilityPartnerId(accountabilityPartnerId);
    }

    @Override
    public List<Goal> getGoalSetterAndAccountabilityPartnerGoals(Long goalSetterId, Long accountabilityPartnerId) {
        return goalRepository.findByGoalSetterIdAndAccountabilityPartnerId(goalSetterId, accountabilityPartnerId);
    }

    @Override
    public List<Goal> getAllGoals() {
        return (List<Goal>) goalRepository.findAll();
    }

    static Goal unwrapGoal(Optional<Goal> entity, Long id){
        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Goal.class);
    }
    
}
