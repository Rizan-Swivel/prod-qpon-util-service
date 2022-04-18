package com.swivel.util.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Base Controller
 */
@RestController
public class BaseController {

    /**
     * index action
     */
    @GetMapping(path = "/", produces = "text/html")
    public String index() {
        return "<h1>Swivel Util Service</h1>";
    }
}
