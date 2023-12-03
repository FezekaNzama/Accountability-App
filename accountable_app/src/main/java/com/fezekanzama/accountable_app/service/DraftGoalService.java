package com.fezekanzama.accountable_app.service;

import com.fezekanzama.accountable_app.entity.DraftGoal;

public interface DraftGoalService {
    DraftGoal getDraftGoal(Long id);
    void approveDraftGoal(Long id);
    void deleteDraftGoal(Long id);

}
