package com.epam.lowcost.controller;

import com.epam.lowcost.model.Plane;
import com.epam.lowcost.services.interfaces.PlaneService;
import com.epam.lowcost.util.PlaneValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.epam.lowcost.util.Endpoints.*;

@Controller
@RequestMapping(PLANE)
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class PlaneController {
    private final PlaneService planeService;
    private  final PlaneValidator planeValidator;

    @Autowired
    public PlaneController(PlaneService planeService, PlaneValidator planeValidator) {
        this.planeService = planeService;
        this.planeValidator = planeValidator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String allPlanesPage(Model model, Pageable pageable) {
        model.addAttribute("planes", planeService.getAllPlanes(pageable));
        return PLANES_PAGE;
    }

    @RequestMapping(value = ADD, method = RequestMethod.GET)
    public String addPlanePage(Model model) {
        model.addAttribute("planeForm", new Plane());
        return ADD_PLANE_PAGE;
    }

    @RequestMapping(value = ADD, method = RequestMethod.POST)
    public String addPlane(@ModelAttribute("planeForm") Plane planeForm, BindingResult bindingResult) {
        planeValidator.validate(planeForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return ADD_PLANE_PAGE;
        }
        planeService.addPlane(planeForm);

        return "redirect:" + PLANE;
    }

    @RequestMapping(value = "{plane}", method = RequestMethod.GET)
    public String updatePlanePage(@PathVariable Plane plane, Model model) {
        model.addAttribute("plane", plane);

        return PLANES_SETTINGS;
    }

    @RequestMapping(value = "{planeId}", method = RequestMethod.POST)
    public String updatePlane(@RequestParam Long planeId,
                              @RequestParam Map<String, String> params) {
        Plane plane = planeService.getById(planeId);
        plane.setModel(params.get("model"));
        plane.setBusinessPlacesNumber(Integer.parseInt(params.get("businessPlacesNumber")));
        plane.setEconomPlacesNumber(Integer.parseInt(params.get("economPlacesNumber")));
        planeService.updatePlane(plane);

        return "redirect:" + PLANE;
    }

    @RequestMapping(value = DELETE, method = RequestMethod.POST)
    public String deletePlane(@RequestParam Long planeId) {
        planeService.deletePlane(planeId);

        return "redirect:" + PLANE;
    }
}
