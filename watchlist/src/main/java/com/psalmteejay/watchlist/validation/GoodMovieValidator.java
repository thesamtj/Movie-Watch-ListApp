/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psalmteejay.watchlist.validation;

import com.psalmteejay.watchlist.domain.WatchlistItem;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Omali
 */
public class GoodMovieValidator implements ConstraintValidator<GoodMovie, WatchlistItem>{

    @Override
    public boolean isValid(WatchlistItem t, ConstraintValidatorContext cvc) {
        return !(Double.valueOf(t.getRating()) >= 8 
                &&  "L".equals(t.getPriority().trim().toUpperCase()));
    }
    
}
