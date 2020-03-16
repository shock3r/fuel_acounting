package com.epam.brest.courses.web_app;

import com.epam.brest.courses.model.Fuel;
import com.epam.brest.courses.model.dto.FuelDto;
import com.epam.brest.courses.service.FuelDtoService;
import com.epam.brest.courses.service.FuelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

/**
 * Hello MVC controller.
 */
@Controller
public class FuelController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FuelController.class);
    private final FuelDtoService fuelDtoService;
    private final FuelService fuelService;

    public FuelController(FuelDtoService fuelDtoService, FuelService fuelService) {
        this.fuelDtoService = fuelDtoService;
        this.fuelService = fuelService;
    }

    /**
     *  Goto fuels list page.
     * @param model model.
     * @return view name.
     */
    @GetMapping(value = "/fuels")
    public String fuels(Model model) {
        LOGGER.debug("fuels()");
        model.addAttribute("fuels", fuelDtoService.findAllWithFuelSum());
        return "fuels";
    }

    /**
     * Goto fuel edit page.
     *
     * @param id fuel id.
     * @param model model.
     * @return view name.
     */
    @GetMapping(value="/fuel/{id}")
    public String gotoEditFuelPage(@PathVariable Integer id, Model model){
        LOGGER.debug("gotoEditFuelPage({},{})", id, model);
        Optional<Fuel> optionalFuel = fuelService.findById(id);
        if (optionalFuel.isPresent()){
            model.addAttribute("fuel", optionalFuel.get());
            return "fuel";
        } else {
            return "redirect:/fuels";
        }
    }

    /**
     * Update fuel.
     * @param fuel fuel.
     * @return view name.
     */
    @PostMapping(value="fuel/{id}")
    public String updateFuel(Fuel fuel){
        LOGGER.debug("updateFuel({})", fuel);
        this.fuelService.update(fuel);
        return "redirect:/fuels";
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
