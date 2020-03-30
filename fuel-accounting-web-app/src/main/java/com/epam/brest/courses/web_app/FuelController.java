package com.epam.brest.courses.web_app;

import com.epam.brest.courses.model.Fuel;
import com.epam.brest.courses.service.FuelDtoService;
import com.epam.brest.courses.service.FuelService;
import com.epam.brest.courses.web_app.validators.FuelValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Fuel MVC controller.
 */
@Controller
public class FuelController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FuelController.class);
    private final FuelDtoService fuelDtoService;
    private final FuelService fuelService;

    @Autowired
    private FuelValidator fuelValidator;
    
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
            model.addAttribute("isNew", false);
            model.addAttribute("fuel", optionalFuel.get());
            return "fuel";
        } else {
            return "redirect:/fuels";
        }
    }

    /**
     * Update fuel.
     * @param fuel fuel with filled data.
     * @param result binding result.
     * @return view name.
     */
    @PostMapping(value="fuel/{id}")
    public String updateFuel(@Valid Fuel fuel,
                             BindingResult result){
        LOGGER.debug("updateFuel({}, {})", fuel, result);
        fuelValidator.validate(fuel, result);
        if (result.hasErrors()){
            return "fuel";
        } else {
            this.fuelService.update(fuel);
            return "redirect:/fuels";
        }
    }

    /**
     *  Goto add fuel page.
     * @param model model.
     * @return view name.
     */
    @GetMapping(value = "/fuel")
    public String gotoAddFuelPage(Model model){
        LOGGER.debug("gotoAddFuelPage({})", model);
        model.addAttribute("isNew", true);
        model.addAttribute("fuel", new Fuel());
        return "fuel";
    }

    /**
     * Persist new fuel into persistence storage.
     * @param fuel new fuel with filled data.
     * @param result binding result.
     * @return view name.
     */
    @PostMapping(value = "/fuel")
    public String addNewFuel(@Valid  Fuel fuel,
                             BindingResult result){
        LOGGER.debug("addNewFuel({}, {})", fuel, result);
        fuelValidator.validate(fuel, result);
        if (result.hasErrors()) {
            return "fuel";
        } else {
            this.fuelService.create(fuel);
            return "redirect:/fuels";
        }
    }

    /**
     * Delete fuel by id.
     * @param id Integer id.
     * @param model model.
     * @return view name.
     */
    @GetMapping(value = "/fuel/{id}/delete")
    public String deleteFuel(@PathVariable Integer id, Model model) {
        LOGGER.debug("deleteFuel({},{})", id, model);
        this.fuelService.delete(id);
        return "redirect:/fuels";
    }

}
