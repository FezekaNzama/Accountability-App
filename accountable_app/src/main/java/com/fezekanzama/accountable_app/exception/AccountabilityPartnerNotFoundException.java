package com.fezekanzama.accountable_app.exception;

public class AccountabilityPartnerNotFoundException extends RuntimeException{
    public AccountabilityPartnerNotFoundException(Long accountabilityPartnerId){
        super("The accountability partner with id: '" + accountabilityPartnerId + "' does not exist in our records");
    }
}
