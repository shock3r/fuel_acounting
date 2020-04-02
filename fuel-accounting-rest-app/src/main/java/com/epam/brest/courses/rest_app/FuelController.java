package com.epam.brest.courses.rest_app;

import com.epam.brest.courses.model.Fuel;
import com.epam.brest.courses.model.dto.FuelDto;
import com.epam.brest.courses.rest_app.exception.FuelNotFoundException;
import com.epam.brest.courses.service.FuelDtoService;
import com.epam.brest.courses.service.FuelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;
/**
 * Fuel rest controller.
 */
@RestController(value = "/api")
@EnableSwagger2
public class FuelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FuelController.class);
    private final FuelDtoService fuelDtoService;
    private final FuelService fuelService;

//    @Autowired
//    private FuelValidator fuelValidator;

    public FuelController(FuelDtoService fuelDtoService, FuelService fuelService) {
        this.fuelDtoService = fuelDtoService;
        this.fuelService = fuelService;
    }

    /**
     * Get all fuels.
     *
     * @return fuels list json.
     */
    @GetMapping(value = "/fuels")
    public Collection<FuelDto> fuels() {
        LOGGER.debug("fuels()");
        return fuelDtoService.findAllWithFuelSum();
    }

    /**
     * Get fuel by id.
     *
     * @param id fuel id.
     * @return json fuel.
     */
    @GetMapping(value="/fuel/{id}")
    public Fuel findFuelById(@PathVariable Integer id){
        LOGGER.debug("findFuelById({})", id);
        return fuelService.findById(id).orElseThrow(() -> new FuelNotFoundException(id));
    }

    /**
     * Add new Fuel into DB.
     * @param fuelName String fuel name.
     * @return Integer number of items that was inserted.
     */
    @PostMapping(path = "/fuels", consumes = "application/json", produces = "application/json")
    public Integer addFuel(@RequestBody String fuelName){
        LOGGER.debug("addFuel({})", fuelName);
        return fuelService.create(
                new Fuel().
                        setFuelName(fuelName));
    }

//    /**
//     * Update fuel.
//     * @param fuel fuel with filled data.
//     * @param result binding result.
//     * @return view name.
//     */
//    @PostMapping(value="fuel/{id}")
//    public String updateFuel(@Valid Fuel fuel,
//                             BindingResult result){
//        LOGGER.debug("updateFuel({}, {})", fuel, result);
//        fuelValidator.validate(fuel, result);
//        if (result.hasErrors()){
//            return "fuel";
//        } else {
//            this.fuelService.update(fuel);
//            return "redirect:/fuels";
//        }
//    }
//
//    /**
//     *  Goto add fuel page.
//     * @param model model.
//     * @return view name.
//     */
//    @GetMapping(value = "/fuel")
//    public String gotoAddFuelPage(Model model){
//        LOGGER.debug("gotoAddFuelPage({})", model);
//        model.addAttribute("isNew", true);
//        model.addAttribute("fuel", new Fuel());
//        return "fuel";
//    }
//
//    /**
//     * Persist new fuel into persistence storage.
//     * @param fuel new fuel with filled data.
//     * @param result binding result.
//     * @return view name.
//     */
//    @PostMapping(value = "/fuel")
//    public String addNewFuel(@Valid  Fuel fuel,
//                             BindingResult result){
//        LOGGER.debug("addNewFuel({}, {})", fuel, result);
//        fuelValidator.validate(fuel, result);
//        if (result.hasErrors()) {
//            return "fuel";
//        } else {
//            this.fuelService.create(fuel);
//            return "redirect:/fuels";
//        }
//    }
//
//    /**
//     * Delete fuel by id.
//     * @param id Integer id.
//     * @param model model.
//     * @return view name.
//     */
//    @GetMapping(value = "/fuel/{id}/delete")
//    public String deleteFuel(@PathVariable Integer id, Model model) {
//        LOGGER.debug("deleteFuel({},{})", id, model);
//        this.fuelService.delete(id);
//        return "redirect:/fuels";
//    }

}
