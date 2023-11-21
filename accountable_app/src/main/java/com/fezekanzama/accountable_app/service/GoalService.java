package com.fezekanzama.accountable_app.service;

import java.util.List;

import com.fezekanzama.accountable_app.entity.Goal;

public interface GoalService {
    Goal getGoal(Long id);
    Goal saveGoal(Goal goal, Long goalSetterId, Long accountabilityPartnerId);
    Goal updateGoal(Long id, Goal goal, Long accountabilityPartnerId);
    void deleteGoal(Long id);
    List<Goal> getGoalSetterGoals(Long goalSetterId);
    List<Goal> getAccountabilityPartnerGoals(Long accountabilityPartnerId);
    List<Goal> getGoalSetterAndAccountabilityPartnerGoals(Long goalSetterId, Long accountabilityPartnerId);
    List<Goal> getAllGoals();
}
