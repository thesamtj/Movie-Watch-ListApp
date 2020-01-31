/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psalmteejay.watchlist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author Omali
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer{
    
    @Override 
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home.html");
        registry.addViewController("/home").setViewName("home.html");
    }
}
