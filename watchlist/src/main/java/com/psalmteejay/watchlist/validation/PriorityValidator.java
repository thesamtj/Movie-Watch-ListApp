/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psalmteejay.watchlist.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Omali
 */
public class PriorityValidator implements ConstraintValidator<Priority, String>{

    @Override
    public boolean isValid(String t, ConstraintValidatorContext cvc) {
        return t.trim().length()==1 && "LHM".contains(t.toUpperCase()); //To change body of generated methods, choose Tools | Templates.
    }
    
}
