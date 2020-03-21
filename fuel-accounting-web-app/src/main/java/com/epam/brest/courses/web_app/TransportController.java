package com.epam.brest.courses.web_app;

import com.epam.brest.courses.model.Transport;
import com.epam.brest.courses.service.TransportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * Hello MVC controller.
 */
@Controller
public class TransportController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransportController.class);
    private final TransportService transportService;

    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    /**
     *  Goto transports list page.
     * @param model model.
     * @return view name.
     */
    @GetMapping(value = "/transports")
    public String transports(Model model) {
        LOGGER.debug("transports()");
        List<Transport> transports = transportService.findAll();
        model.addAttribute("transports", transports);
        return "transports";
    }

    /**
     * Show transport data by date filter.
     *
     * @param dateFrom date from.
     * @param dateTo date to.
     * @param model model.
     * @return view name.
     */
    @GetMapping(value = "/transports/from/{dateFrom}/to/{dateTo}")
    public  String findTransportsByDates(@PathVariable Date dateFrom, @PathVariable Date dateTo, Model model){
        LOGGER.debug("findTransportsByDates({},{},{})", dateFrom, dateTo, model);
        model.addAttribute("transports",
                this.transportService.findAllFromDateToDate(dateFrom, dateTo));
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
