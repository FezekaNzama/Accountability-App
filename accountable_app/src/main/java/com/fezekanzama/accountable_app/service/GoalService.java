package com.fezekanzama.accountable_app.service;

import java.util.List;

import com.fezekanzama.accountable_app.entity.DraftGoal;
import com.fezekanzama.accountable_app.entity.Goal;

public interface GoalService {
    Goal getGoal(Long id);
    Goal saveGoal(Goal goal, Long goalSetterId, Long accountabilityPartnerId);
    //Update goal Accountabiility Partner
    Goal updateGoal(Long id, Goal goal, Long accountabilityPartnerId);
    //Update goal with accountability partner approval
    DraftGoal requestGoalUpdate(Long id, Goal goal);
    Goal updateGoal(Long id, Goal goal);
    //Update goal with a financial penalty
    Goal updateGoal(Long id, Goal goal, int amount);
    void deleteGoal(Long id);
    List<Goal> getGoalSetterGoals(Long goalSetterId);
    List<Goal> getAccountabilityPartnerGoals(Long accountabilityPartnerId);
    List<Goal> getGoalSetterAndAccountabilityPartnerGoals(Long goalSetterId, Long accountabilityPartnerId);
    List<Goal> getAllGoals();
}
