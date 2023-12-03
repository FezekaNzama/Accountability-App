package com.fezekanzama.accountable_app.repository;

import org.springframework.data.repository.CrudRepository;

import com.fezekanzama.accountable_app.entity.DraftGoal;

public interface DraftGoalRepository extends CrudRepository<DraftGoal, Long>{
    
}
