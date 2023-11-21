package com.fezekanzama.accountable_app.exception;

public class GoalSetterNotFoundException extends RuntimeException{
    public GoalSetterNotFoundException(Long goalSetterId){
        super("The goal setter with id: '" + goalSetterId + "' does not exist in our records");
    }
}
