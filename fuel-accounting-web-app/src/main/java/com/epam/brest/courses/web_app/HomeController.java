package com.epam.brest.courses.web_app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Root controller.
 */
@Controller
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    /**
     * Goto redirect page
     * @return view redirect page.
     */
    @GetMapping(value = "/")
    public String defaultPageRedirect() {
        LOGGER.debug("defaultPageRedirect()");
        return "redirect:fuels";
    }
}
