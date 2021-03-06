package com.epam.brest.courses.web_app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Hello MVC controller.
 */
@Controller
public class HelloController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    /**
     *  Goto hello world test page.
     * @param name parameter.
     * @param model model.
     * @return view name.
     */
    @GetMapping(value = "/hello")
    public String hello2(@RequestParam(value = "name",required = false, defaultValue = "world") String name,
                        Model model) {
        LOGGER.debug("hello(name:{})", name);
        model.addAttribute("name", name);
        return "hello";
    }

}
