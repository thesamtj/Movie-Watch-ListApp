/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psalmteejay.watchlist;

import com.psalmteejay.watchlist.domain.WatchlistItem;
import com.psalmteejay.watchlist.repository.WatchlistRepository;
import com.psalmteejay.watchlist.service.MovieRatingServiceLiveImpl;
import com.psalmteejay.watchlist.service.WatchlistService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Omali
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class WatchlistServiceTest {

    @Mock
    private WatchlistRepository watchlistRepositoryMock;

    @Mock
    private MovieRatingServiceLiveImpl movieRatingServiceMock;

    @InjectMocks
    private WatchlistService watchlistService;

    @Test
    public void testGetWatchlistItemsReturnsAllItemsFromRepository() {

        //Arrange
        WatchlistItem item1 = new WatchlistItem(1, "Star Wars", "7.7", "M", "");
        WatchlistItem item2 = new WatchlistItem(2, "Star Treck", "8.8", "M", "");
        List<WatchlistItem> mockItems = Arrays.asList(item1, item2);

        when(watchlistRepositoryMock.getList()).thenReturn(mockItems);

        //Act
        List<WatchlistItem> result = watchlistService.getWatchlistItems();

        //Assert
        assertTrue(result.size() == 2);
        assertTrue(result.get(0).getTitle().equals("Star Wars"));
        assertTrue(result.get(1).getTitle().equals("Star Treck"));
    }

    @Test
    public void testGetwatchlistItemsRatingFormOmdbServiceOverrideTheValueInItems() {

        //Arrange
        WatchlistItem item1 = new WatchlistItem(1, "Star Wars", "7.7", "M", "");
        List<WatchlistItem> mockItems = Arrays.asList(item1);

        when(watchlistRepositoryMock.getList()).thenReturn(mockItems);
        when(movieRatingServiceMock.getMovieRating(any(String.class))).thenReturn("10");

        //Act
        List<WatchlistItem> result = watchlistService.getWatchlistItems();

        //Assert
        assertTrue(result.get(0).getRating().equals("10"));
    }
}
