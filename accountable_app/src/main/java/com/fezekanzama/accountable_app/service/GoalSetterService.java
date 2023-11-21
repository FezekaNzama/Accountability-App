package com.fezekanzama.accountable_app.service;

import java.util.List;
import java.util.Set;

import com.fezekanzama.accountable_app.entity.AccountabilityPartner;
import com.fezekanzama.accountable_app.entity.GoalSetter;
import com.fezekanzama.accountable_app.entity.Wallet;

public interface GoalSetterService {
    GoalSetter getGoalSetter(Long id);
    GoalSetter saveGoalSetter(GoalSetter goalSetter);
    GoalSetter updateGoalSetter(Long id, GoalSetter goalSetter);
    GoalSetter saveGoalSetterWallet(Long id, Wallet wallet);
    void deleteGoalSetter(Long id);
    List<GoalSetter> getGoalSetters();
    Set<AccountabilityPartner> getAssociatedAccountabilityPartners(Long id);
}
