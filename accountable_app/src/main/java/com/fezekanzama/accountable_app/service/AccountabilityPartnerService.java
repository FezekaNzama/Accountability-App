package com.fezekanzama.accountable_app.service;

import java.util.List;
import java.util.Set;

import com.fezekanzama.accountable_app.entity.AccountabilityPartner;
import com.fezekanzama.accountable_app.entity.GoalSetter;

public interface AccountabilityPartnerService {
    AccountabilityPartner getAccountabilityPartner(Long id);
    AccountabilityPartner saveAccountabilityPartner(AccountabilityPartner accountabilityPartner);
    AccountabilityPartner updateAccountabilityPartner(Long id, AccountabilityPartner accountabilityPartner);
    void deleteAccountabilityPartner(Long id);
    List<AccountabilityPartner> getAccountabilityPartners();
    Set<GoalSetter> getAssociatedGoalSetters(Long id);
}
