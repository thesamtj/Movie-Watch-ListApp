/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psalmteejay.watchlist.controller;

import com.psalmteejay.watchlist.domain.WatchlistItem;
import com.psalmteejay.watchlist.exception.DuplicateTitleException;
import com.psalmteejay.watchlist.service.WatchlistService;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Omali
 */
// Controller
@Controller
public class WatchlistController {

    private WatchlistService watchlistService;
    private final Logger logger = LoggerFactory.getLogger(WatchlistController.class);
    
    @Autowired
    public WatchlistController(WatchlistService watchlistService) {
        this.watchlistService = watchlistService;
    }
    

    @GetMapping("/watchlistItemForm")
    public ModelAndView showWatchlistItemForm(@RequestParam(required = false) Integer id) {
        
        logger.info("HTTP GET request received at /watchlistItemForm URL");
        
        String viewName = "watchlistItemForm";
        Map<String, Object> model = new HashMap<String, Object>();

        WatchlistItem watchlistItem = watchlistService.findWatchlistItemById(id);

        if (watchlistItem == null) {
            model.put("watchlistItem", new WatchlistItem());
        } else {
            model.put("watchlistItem", watchlistItem);
        }

        return new ModelAndView(viewName, model);
    }

    @PostMapping("/watchlistItemForm")
    public ModelAndView submitWatchlistItemForm(@Valid WatchlistItem watchlistItem, BindingResult bindingResult) {
        
        logger.info("HTTP POST request received at /watchlistItemForm URL");
        
        if (bindingResult.hasErrors()) {
            return new ModelAndView("watchlistItemForm");
        }

        try {
            watchlistService.addOrUpdateWatchlistItem(watchlistItem);
        } catch (DuplicateTitleException e) {
            bindingResult.rejectValue("title", "", "This title already exists on your watchlist");
            return new ModelAndView("watchlistItemForm");
        }

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/watchlist");

        return new ModelAndView(redirectView);
    }

    @GetMapping("/watchlist")
    public ModelAndView getWatchlist() {
        
        logger.info("HTTP GET request received at /watchlist URL");
        
        String viewName = "watchlist";
        Map<String, Object> model = new HashMap<>();

        model.put("watchlistItems", watchlistService.getWatchlistItems());
        model.put("numberOfMovies", watchlistService.getWatchlistItemsSize());

        return new ModelAndView(viewName, model);
    }

    
}
