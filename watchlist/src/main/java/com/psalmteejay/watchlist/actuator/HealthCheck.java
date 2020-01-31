/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psalmteejay.watchlist.actuator;

import com.psalmteejay.watchlist.service.MovieRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 *
 * @author Omali
 */
@Component
public class HealthCheck implements HealthIndicator {

    @Autowired
    MovieRatingService movieRatingService;

    @Override
    public Health health() {

        if (movieRatingService.getMovieRating("Up")==null) {
            return Health.down().withDetail("Cause", "OMDb API is not available").build();
        }

        return Health.up().build();
    }
}
