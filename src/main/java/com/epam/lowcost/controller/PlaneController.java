package com.epam.lowcost.controller;

import com.epam.lowcost.model.Plane;
import com.epam.lowcost.services.interfaces.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.epam.lowcost.util.Endpoints.*;

@RestController
@RequestMapping(value = PLANE)
public class PlaneController {
    private final PlaneService planeService;

    @Autowired
    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    //@GetMapping(value = ALL)
    @RequestMapping(
            value = ALL,
            method = RequestMethod.GET)
    public String getAllPlanes(Model model) {
        model.addAttribute("planes", planeService.());
        return "planes";
    }

    @GetMapping
    public String getById(@RequestParam long id, Model model) {
        model.addAttribute("plane", planeService.getById(id));
        model.addAttribute("message", "Here is your Plane!");
        return "planes";
    }

    @PostMapping
    public String addPlane(@RequestParam Map<String, String> params, Model model) {
        model.addAttribute("plane", planeService.addPlane(
                Plane.builder()
                        .model(params.get("model"))
                        .businessPlacesNumber(Integer.valueOf(params.get("businessPlacesNumber")))
                        .economPlacesNumber(Integer.valueOf(params.get("economPlacesNumber")))
                        .isDeleted(false)
                        .build()));

        model.addAttribute("message", "Plane successfully added");
        return "planes";
    }

    @PostMapping(value = UPDATE)
    public String updatePlane(@RequestParam Map<String, String> params, Model model) {
        Plane plane = planeService.updatePlane(
                Plane.builder()
                        .id(Long.valueOf(params.get("id")))
                        .model(params.get("model"))
                        .businessPlacesNumber(Integer.valueOf(params.get("businessPlacesNumber")))
                        .economPlacesNumber(Integer.valueOf(params.get("economPlacesNumber")))
                        .build());
        if (plane == null) {
            model.addAttribute("message", "No such plane or it has been deleted!");
        } else {
            model.addAttribute("plane", plane);
            model.addAttribute("message", "Plane seccessfully updated");
        }
        return "planes";
    }

    @PostMapping(value = DELETE)
    public String deletePlane(@RequestParam long id, Model model) {
        model.addAttribute("message", planeService.deletePlane(id));
        return "planes";
    }
}
