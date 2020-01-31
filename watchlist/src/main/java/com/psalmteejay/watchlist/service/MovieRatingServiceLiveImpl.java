/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psalmteejay.watchlist.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Omali
 */

@ConditionalOnProperty(name = "app.environment", havingValue = "prod")
@Service
public class MovieRatingServiceLiveImpl implements MovieRatingService {

    String apiUrl = "http://www.omdbapi.com/?apikey=988a8d28&t=";
    Logger logger = LoggerFactory.getLogger(MovieRatingServiceLiveImpl.class);

    @Override
    public String getMovieRating(String title) {

        try {
            RestTemplate template = new RestTemplate();
            logger.info("Calling omdbapi with url {} and title {}", apiUrl , title);
            ResponseEntity<ObjectNode> response
                    = template.getForEntity(apiUrl + title, ObjectNode.class);

            ObjectNode jsonObject = response.getBody();
            logger.debug("imdbRating node is missing, returning empty.");

            return jsonObject.path("imdbRating").asText();
        } catch (Exception e) {
           logger.warn("Something went wront while calling OMDb API" + e.getMessage());
            return null;
        }
    }

}
