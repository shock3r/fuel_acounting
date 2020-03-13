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
public class FuelController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FuelController.class);

    /**
     *  Goto fuels list page.
     * @param model model.
     * @return view name.
     */
    @GetMapping(value = "/fuels")
    public String fuels(Model model) {
        LOGGER.debug("fuels()");
        return "fuels";
    }

    /**
     *  Goto fuel page.
     * @param model model.
     * @return view name.
     */
    @GetMapping(value = "/fuel")
    public String fuel(Model model){
        LOGGER.debug("fuel()");
        return "fuel";
    }

}
