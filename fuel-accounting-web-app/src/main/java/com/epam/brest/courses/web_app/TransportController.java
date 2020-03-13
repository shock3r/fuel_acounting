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
public class TransportController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransportController.class);

    /**
     *  Goto transports list page.
     * @param model model.
     * @return view name.
     */
    @GetMapping(value = "/transports")
    public String transports(Model model) {
        LOGGER.debug("transports()");
        return "transports";
    }

    /**
     *  Goto transport page.
     * @param model model.
     * @return view name.
     */
    @GetMapping(value = "/transport")
    public String transport(Model model){
        LOGGER.debug("transport()");
        return "transport";
    }

}
