/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psalmteejay.watchlist.repository;

import com.psalmteejay.watchlist.domain.WatchlistItem;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Omali
 */
@Service
public class WatchlistRepository {

    private final List<WatchlistItem> watchlistItems = new ArrayList<>();
    private static int index = 1;

    public List<WatchlistItem> getList() {
        return watchlistItems;
    }

    public void addItem(WatchlistItem watchlistItem) {
        watchlistItem.setId(index++);
        watchlistItems.add(watchlistItem);
    }

    public WatchlistItem findById(Integer id) {
        for (WatchlistItem watchlistItem : watchlistItems) {
            if (watchlistItem.getId().equals(id)) {
                return watchlistItem;
            }
        }
        return null;
    }

    public WatchlistItem findByTitle(String title) {
        for (WatchlistItem watchlistItem : watchlistItems) {
            if (watchlistItem.getTitle().equals(title)) {
                return watchlistItem;
            }
        }
        return null;
    }
}
