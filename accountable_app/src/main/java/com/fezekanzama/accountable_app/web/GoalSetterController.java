package com.fezekanzama.accountable_app.web;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fezekanzama.accountable_app.service.GoalSetterService;

import jakarta.validation.Valid;

import com.fezekanzama.accountable_app.entity.AccountabilityPartner;
import com.fezekanzama.accountable_app.entity.GoalSetter;
import com.fezekanzama.accountable_app.entity.Wallet;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/goalSetter")
public class GoalSetterController {
    
    GoalSetterService goalSetterService;

    @GetMapping("/{id}")
    public ResponseEntity<GoalSetter> getGoalSetter(@PathVariable Long id){
        return new ResponseEntity<>(goalSetterService.getGoalSetter(id), HttpStatus.OK);
    }

   @PutMapping("/{id}")
   public ResponseEntity<GoalSetter> updateGoalSetter(@Valid @RequestBody GoalSetter goalSetter, @PathVariable Long id){
        return new ResponseEntity<>(goalSetterService.updateGoalSetter(id, goalSetter), HttpStatus.OK);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<HttpStatus> deleteGoalSetter(@PathVariable Long id){
        goalSetterService.deleteGoalSetter(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }

   @GetMapping("/all")
   public ResponseEntity<List<GoalSetter>> getGoalSetters(){
        return new ResponseEntity<>(goalSetterService.getGoalSetters(), HttpStatus.OK);
   }

   @GetMapping("/{id}/accountabilityPartners")
   public ResponseEntity<Set<AccountabilityPartner>> getAssociatedAccountabilityPartners(@PathVariable Long id){
        return new ResponseEntity<>(goalSetterService.getAssociatedAccountabilityPartners(id), HttpStatus.OK);
   }

   @PutMapping("/{id}/wallet")
   public ResponseEntity<GoalSetter> saveGoalSetterWallet(@PathVariable Long id, @Valid @RequestBody Wallet wallet){
     return new ResponseEntity<>(goalSetterService.saveGoalSetterWallet(id, wallet), HttpStatus.OK);
   }

}
