/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psalmteejay.watchlist.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

/**
 *
 * @author Omali
 */
@Component
@Endpoint(id = "release-notes")
public class ReleaseNotesEndpoint {

    String version10 = "** Version 1.0 ** \n\n"
            + "* Home page created\n"
            + "* Adding a new item form added\n"
            + "* View the watchlist page added\n\n\n";

    String version11 = "** Version 1.1 \n\n"
            + "* Reading from OMDb API added \n"
            + "* Actuator endpoints added \n";

    @ReadOperation
    public String releaseNotes() {
        return version11 + version10;
    }

    @ReadOperation
    public String selectReleaseNotes(@Selector String selector) {
        if ("1.0".equals(selector)) {
            return version10;
        } else if ("1.1".equals(selector)) {
            return version11;
        } else {
            return releaseNotes();
        }
    }
}
