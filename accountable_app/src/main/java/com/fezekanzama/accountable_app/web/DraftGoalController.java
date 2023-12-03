package com.fezekanzama.accountable_app.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fezekanzama.accountable_app.entity.DraftGoal;
import com.fezekanzama.accountable_app.service.DraftGoalService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/draftGoal")
public class DraftGoalController {

    DraftGoalService draftGoalService;

    @GetMapping("/all")
    public ResponseEntity<List<DraftGoal>> getDraftGoals(){
        return new ResponseEntity<>(draftGoalService.getDraftGoals(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DraftGoal> getDraftGoal(@PathVariable Long id){
        return new ResponseEntity<>(draftGoalService.getDraftGoal(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDraftGoal(@PathVariable Long id){
        draftGoalService.deleteDraftGoal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/approveDraft/{id}")
    public ResponseEntity<HttpStatus> approveDraftGoal(@PathVariable Long id){
        draftGoalService.approveDraftGoal(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
