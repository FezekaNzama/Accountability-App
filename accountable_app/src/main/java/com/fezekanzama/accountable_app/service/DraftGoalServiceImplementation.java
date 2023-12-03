package com.fezekanzama.accountable_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fezekanzama.accountable_app.entity.DraftGoal;
import com.fezekanzama.accountable_app.exception.EntityNotFoundException;
import com.fezekanzama.accountable_app.repository.DraftGoalRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DraftGoalServiceImplementation implements DraftGoalService{

    DraftGoalRepository draftGoalRepository;
    GoalService goalService;

    @Override
    public DraftGoal getDraftGoal(Long id) {
        Optional<DraftGoal> draftGoal = draftGoalRepository.findById(id);
        return unwrapDraftGoal(draftGoal, id);
    }

    @Override
    public void deleteDraftGoal(Long id) {
        draftGoalRepository.deleteById(id);
    }

    static DraftGoal unwrapDraftGoal(Optional<DraftGoal> entity, Long id){
        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, DraftGoal.class);
    }

    @Override
    public void approveDraftGoal(Long id) {
        DraftGoal draftGoal = getDraftGoal(id);

        //update goal in the goal repo
        goalService.updateGoal(draftGoal.getGoal().getId(), draftGoal.getGoal());
        //delete goal from draft repo
        deleteDraftGoal(id);
    }

    @Override
    public List<DraftGoal> getDraftGoals() {
        return (List<DraftGoal>)draftGoalRepository.findAll();
    }
    
}
