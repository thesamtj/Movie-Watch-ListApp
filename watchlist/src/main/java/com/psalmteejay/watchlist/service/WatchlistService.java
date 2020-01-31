/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psalmteejay.watchlist.service;

import com.psalmteejay.watchlist.domain.WatchlistItem;
import com.psalmteejay.watchlist.exception.DuplicateTitleException;
import com.psalmteejay.watchlist.repository.WatchlistRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Omali
 */
@Service
public class WatchlistService {

    WatchlistRepository watchlistRepository;
    private final MovieRatingService movieRatingService;
    
    @Autowired
    public WatchlistService(WatchlistRepository watchlistRepository, MovieRatingService movieRatingService) {
        this.watchlistRepository = watchlistRepository;
        this.movieRatingService = movieRatingService;
    }
    
    
    public List<WatchlistItem> getWatchlistItems() {
        List<WatchlistItem> watchlistItems = watchlistRepository.getList();
        for (WatchlistItem watchlistItem : watchlistItems) {

            String rating = movieRatingService.getMovieRating(watchlistItem.getTitle());

            if (rating != null) {
                watchlistItem.setRating(rating);
            }
        }
        return watchlistItems;
    }

    public int getWatchlistItemsSize() {
        return watchlistRepository.getList().size();
    }

    public WatchlistItem findWatchlistItemById(Integer id) {
        return watchlistRepository.findById(id);
    }

    public void addOrUpdateWatchlistItem(WatchlistItem watchlistItem) throws DuplicateTitleException {

        WatchlistItem existingItem = findWatchlistItemById(watchlistItem.getId());

        if (existingItem == null) {
            if (watchlistRepository.findByTitle(watchlistItem.getTitle()) != null) {
                throw new DuplicateTitleException();
            }
            watchlistRepository.addItem(watchlistItem);
        } else {
            existingItem.setComment(watchlistItem.getComment());
            existingItem.setPriority(watchlistItem.getPriority());
            existingItem.setRating(watchlistItem.getRating());
            existingItem.setTitle(watchlistItem.getTitle());
        }
    }
}
