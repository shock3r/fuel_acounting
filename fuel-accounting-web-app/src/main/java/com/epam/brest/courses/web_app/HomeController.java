package com.epam.brest.courses.web_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Root controller.
 */
@Controller
public class HomeController {

    /**
     * Goto redirect page
     * @return view redirect page.
     */
    @GetMapping(value = "/")
    public String defaultPageRedirect() {
        return "redirect:hello";
    }
}
