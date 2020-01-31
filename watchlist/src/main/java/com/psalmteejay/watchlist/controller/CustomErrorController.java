/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psalmteejay.watchlist.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Omali
 */
@Controller
public class CustomErrorController implements ErrorController {
    
    private final Logger logger = LoggerFactory.getLogger(CustomErrorController.class);
    
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @GetMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        logger.error("Error with status code {} happened. Support! Do something about it!");
        return new ModelAndView("error");
    }
}
