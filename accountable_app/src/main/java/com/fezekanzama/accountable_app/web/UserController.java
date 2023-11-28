package com.fezekanzama.accountable_app.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fezekanzama.accountable_app.entity.AccountabilityPartner;
import com.fezekanzama.accountable_app.entity.GoalSetter;
import com.fezekanzama.accountable_app.entity.Role;
import com.fezekanzama.accountable_app.entity.User;
import com.fezekanzama.accountable_app.service.AccountabilityPartnerService;
import com.fezekanzama.accountable_app.service.GoalSetterService;
import com.fezekanzama.accountable_app.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;
    GoalSetterService goalSetterService;
    AccountabilityPartnerService accountabilityPartnerService;

    @GetMapping("/{id}")
    public ResponseEntity<String> findUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUser(id).getEmail(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> createUser(@Valid @RequestBody User user){
        user.setRole(Role.ADMIN);
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/register/goalSetter")
    public ResponseEntity<HttpStatus> createUserGoalSetter(@Valid @RequestBody GoalSetter user){
        user.setRole(Role.GOAL_SETTER);
        userService.saveUser(user);
        goalSetterService.saveGoalSetter(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/register/accountabilityPartner")
    public ResponseEntity<HttpStatus> createUserAccountabilityPartner(@Valid @RequestBody AccountabilityPartner user){
        user.setRole(Role.ACCOUNTABILITY_PARTNER);
        userService.saveUser(user);
        accountabilityPartnerService.saveAccountabilityPartner(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
