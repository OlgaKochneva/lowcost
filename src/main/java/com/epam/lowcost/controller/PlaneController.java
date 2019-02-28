package com.epam.lowcost.controller;

import com.epam.lowcost.model.Plane;
import com.epam.lowcost.services.interfaces.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.epam.lowcost.util.Endpoints.*;

@Controller
@RequestMapping(PLANE)
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class PlaneController {
    private final PlaneService planeService;

    @Autowired
    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String allPlanesPage(Model model) {
        model.addAttribute("planes", planeService.getAllPlanes());
        return PLANES_PAGE;
    }

    @RequestMapping(value = ADD, method = RequestMethod.GET)
    public String addPlanePage(Model model) {
        model.addAttribute("planeForm", new Plane());
        return ADD_PLANE_PAGE;
    }

    @RequestMapping(value = ADD, method = RequestMethod.POST)
    public String addPlane(@ModelAttribute("planeForm") Plane planeForm) {
        planeService.addPlane(planeForm);

        return "redirect:" + PLANE;
    }

    @RequestMapping(value = "{plane}", method = RequestMethod.GET)
    public String updatePlanePage(@PathVariable Plane plane, Model model) {
        model.addAttribute("plane", plane);

        return PLANES_SETTINGS;
    }

    @RequestMapping(value = "{plane}", method = RequestMethod.POST)
    public String updatePlane(@RequestParam Long id,
                              @RequestParam Map<String, String> params) {
        Plane plane = planeService.getById(id);

        plane.setModel(params.get("model"));
        plane.setBusinessPlacesNumber(Integer.parseInt(params.get("businessPlacesNumber")));
        plane.setEconomPlacesNumber(Integer.parseInt(params.get("economPlacesNumber")));
        planeService.updatePlane(plane);

        return "redirect:" + PLANE;
    }
}
