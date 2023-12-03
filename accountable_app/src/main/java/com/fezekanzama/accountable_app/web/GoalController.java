package com.fezekanzama.accountable_app.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fezekanzama.accountable_app.entity.DraftGoal;
import com.fezekanzama.accountable_app.entity.Goal;
import com.fezekanzama.accountable_app.service.GoalService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/goal")
public class GoalController {

    GoalService goalService;

    @GetMapping("/{id}")
    public ResponseEntity<Goal> getGoal(@PathVariable Long id){
        return new ResponseEntity<>(goalService.getGoal(id), HttpStatus.OK);
    }

    @PostMapping("/goalSetter/{goalSetterId}/accountabilityPartner/{accountabilityPartnerId}")
    public ResponseEntity<Goal> saveGoal(@Valid @RequestBody Goal goal, @PathVariable Long goalSetterId, @PathVariable Long accountabilityPartnerId){
        return new ResponseEntity<>(goalService.saveGoal(goal, goalSetterId, accountabilityPartnerId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/accountabilityPartner/{accountabilityPartnerId}")
    public ResponseEntity<Goal> updateGoal(@Valid @RequestBody Goal goal, @PathVariable Long id, @PathVariable Long accountabilityPartnerId){
        return new ResponseEntity<>(goalService.updateGoal(id, goal, accountabilityPartnerId), HttpStatus.OK);
    }

    //paid put request
    @PutMapping("/{id}/{penalty}")
    public ResponseEntity<Goal> updateGoal(@Valid @RequestBody Goal goal, @PathVariable Long id, @PathVariable int penalty){
        return new ResponseEntity<>(goalService.updateGoal(id, goal, penalty), HttpStatus.OK);
    }

    @PostMapping("/requestGoalUpdate/{id}")
    public ResponseEntity<DraftGoal> requestGoalUpdate(@Valid @RequestBody Goal goal, @PathVariable Long id){
        return new ResponseEntity<>(goalService.requestGoalUpdate(id, goal), HttpStatus.OK);
    }    

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteGoals (@PathVariable Long id){
        goalService.deleteGoal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/goalSetter/{goalSetterId}")
    public ResponseEntity<List<Goal>> getGoalSetterGoals(@PathVariable Long goalSetterId){
        return new ResponseEntity<>(goalService.getGoalSetterGoals(goalSetterId), HttpStatus.OK);
    }

    @GetMapping("/accountabilityPartner/{accountabilityPartnerId}")
    public ResponseEntity<List<Goal>> getAccountabilityPartnerGoals(@PathVariable Long accountabilityPartnerId){
        return new ResponseEntity<>(goalService.getAccountabilityPartnerGoals(accountabilityPartnerId), HttpStatus.OK);
    }

    @GetMapping("/goalSetter/{goalSetterId}/accountabilityPartner/{accountabilityPartnerId}")
    public ResponseEntity<List<Goal>> getGoalSetterAndAccountabilityPartnerGoals(@PathVariable Long goalSetterId, @PathVariable Long accountabilityPartnerId){
        return new ResponseEntity<>(goalService.getGoalSetterAndAccountabilityPartnerGoals(goalSetterId, accountabilityPartnerId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Goal>> getGoals(){
        return new ResponseEntity<>(goalService.getAllGoals(), HttpStatus.OK);
    }
    
}
