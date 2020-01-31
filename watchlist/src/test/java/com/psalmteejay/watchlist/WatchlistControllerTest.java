/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psalmteejay.watchlist;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import com.psalmteejay.watchlist.service.WatchlistService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 *
 * @author Omali
 */
@WebMvcTest
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class WatchlistControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WatchlistService watchlistService;

    @Test
    public void testShowWatchlistItemForm() throws Exception {

        mockMvc.perform(get("/watchlistItemForm"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("watchlistItemForm"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("watchlistItem"));
    }

    @Test
    public void testSubmitWatchlistItemForm() throws Exception {
        mockMvc.perform(post("/watchlistItemForm")
                .param("title", "Top Gun")
                .param("rating", "5.5")
                .param("priority", "L"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/watchlist"));
    }
}
