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

import com.fezekanzama.accountable_app.entity.AccountabilityPartner;
import com.fezekanzama.accountable_app.entity.GoalSetter;
import com.fezekanzama.accountable_app.service.AccountabilityPartnerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/accountabilityPartner")
public class AccountabilityPartnerController {

    AccountabilityPartnerService accountabilityPartnerService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountabilityPartner> getAccountabilityPartner(@PathVariable Long id){
        return new ResponseEntity<>(accountabilityPartnerService.getAccountabilityPartner(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountabilityPartner> updateAccountabilityPartner(@Valid @RequestBody AccountabilityPartner accountabilityPartner, @PathVariable Long id){
        return new ResponseEntity<>(accountabilityPartnerService.updateAccountabilityPartner(id, accountabilityPartner), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAccountabilityPartner(@PathVariable Long id){
        accountabilityPartnerService.deleteAccountabilityPartner(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountabilityPartner>>  getAccountabilityPartners(){
        return new ResponseEntity<>(accountabilityPartnerService.getAccountabilityPartners(), HttpStatus.OK);
    }

    @GetMapping("/{id}/goalSetters")
    public ResponseEntity<Set<GoalSetter>> getAssociatedGoalSetters(@PathVariable Long id){
        return new ResponseEntity<>(accountabilityPartnerService.getAssociatedGoalSetters(id), HttpStatus.OK);
    }
}
