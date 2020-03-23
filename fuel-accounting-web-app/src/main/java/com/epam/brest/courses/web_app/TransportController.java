package com.epam.brest.courses.web_app;

import com.epam.brest.courses.model.Transport;
import com.epam.brest.courses.service.FuelService;
import com.epam.brest.courses.service.TransportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Hello MVC controller.
 */
@Controller
public class TransportController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransportController.class);
    private final TransportService transportService;
    private final FuelService fuelService;

    public TransportController(TransportService transportService, FuelService fuelService) {
        this.transportService = transportService;
        this.fuelService = fuelService;
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
        transports.get(0);
        model.addAttribute("transports", transports);
        model.addAttribute("fuels", fuelService.findAll());
        return "transports";
    }

    /**
     * Show transports data by date filter.
     *
     * @param dateFrom date from.
     * @param dateTo date to.
     * @param model model.
     * @return view name.
     */
    @GetMapping(value = "/transports/from/{dateFrom}/to/{dateTo}")
    public  String findTransportsByDates(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date dateFrom, @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date dateTo, Model model){
        LOGGER.debug("findTransportsByDates({},{},{})", dateFrom, dateTo, model);
        model.addAttribute("transports",
                this.transportService.findAllFromDateToDate(dateFrom, dateTo));
        return "transports";
    }

    /**
     *  Goto edit transport page.
     *
     * @param id transport id.
     * @param model model.
     * @return view name.
     */
    @GetMapping(value = "/transport/{id}")
    public String gotoEditTransportPage(@PathVariable Integer id, Model model){
        LOGGER.debug("gotoEditTransportPage({},{})", id, model);
        Optional<Transport> transportOptional
                = this.transportService.findById(id);
        if (transportOptional.isPresent()) {
            model.addAttribute("isNew", false);
            model.addAttribute("transport", transportOptional.get());
            model.addAttribute("fuels", fuelService.findAll());
            return "transport";
        } else {
            return "redirect:/transports";
        }
    }



}
