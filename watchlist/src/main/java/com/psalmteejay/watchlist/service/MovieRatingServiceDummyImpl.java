/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psalmteejay.watchlist.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 *
 * @author Omali
 */
@ConditionalOnProperty(name = "app.environment", havingValue = "dev")
@Service
public class MovieRatingServiceDummyImpl implements MovieRatingService {

    @Override
    public String getMovieRating(String title) {

        return "9.99";
    }
}
