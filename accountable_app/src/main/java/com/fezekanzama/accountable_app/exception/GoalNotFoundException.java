package com.fezekanzama.accountable_app.exception;

public class GoalNotFoundException extends RuntimeException{

    public GoalNotFoundException(Long goalSetterId, Long accountabilityPartnerId ){
        super("The goal with goal setter id: '" + goalSetterId + "' and accountability partner id: '" + accountabilityPartnerId + "' doesnot exiist in our records");
    }
    
}
