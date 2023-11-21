package com.fezekanzama.accountable_app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fezekanzama.accountable_app.entity.Goal;

public interface GoalRepository extends CrudRepository<Goal, Long>{
    List<Goal> findByGoalSetterIdAndAccountabilityPartnerId(Long goalSetterId, Long accountabilityPartnerId);
    List<Goal> findByGoalSetterId(Long goalSetterId);
    List<Goal> findByAccountabilityPartnerId(Long accountabilityPartnerId);
}
