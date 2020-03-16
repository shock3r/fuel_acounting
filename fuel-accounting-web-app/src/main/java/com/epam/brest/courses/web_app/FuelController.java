package com.epam.brest.courses.web_app;

import com.epam.brest.courses.model.dto.FuelDto;
import com.epam.brest.courses.service.FuelDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Hello MVC controller.
 */
@Controller
public class FuelController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FuelController.class);
    private final FuelDtoService fuelDtoService;

    public FuelController(FuelDtoService fuelDtoService) {
        this.fuelDtoService = fuelDtoService;
    }

    /**
     *  Goto fuels list page.
     * @param model model.
     * @return view name.
     */
    @GetMapping(value = "/fuels")
    public String fuels(Model model) {
        LOGGER.debug("fuels()");
        List<FuelDto> fuelDtoList = fuelDtoService.findAllWithFuelSum();
        model.addAttribute("fuels", fuelDtoService.findAllWithFuelSum());
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
