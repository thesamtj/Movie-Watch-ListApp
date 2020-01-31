/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psalmteejay.watchlist.domain;

import com.psalmteejay.watchlist.validation.GoodMovie;
import com.psalmteejay.watchlist.validation.Priority;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Omali
 */
@GoodMovie
public class WatchlistItem {
    private Integer id;
	
    @NotBlank(message="Please enter the title")
    private String title;

    private String rating; 
    
    @Priority
    private String priority;

    @Size(max=50,  message="Comment should be maximum 50 characters")
    private String comment;

    public static int index = 0;

    public WatchlistItem(Integer id, String title, String rating, String priority, String comment) {
        super();
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.priority = priority;
        this.comment = comment;
    }

    public WatchlistItem() {  }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        WatchlistItem.index = index;
    }
    
    
}
