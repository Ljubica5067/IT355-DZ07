package org.example.it355dz07.controller;

import org.example.it355dz07.model.Restaurant;
import org.example.it355dz07.repository.restaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class restaurantController {
    @Autowired
    private restaurantRepository rr;

    @GetMapping("/restorani")
    public String sviRestorani(Model model) {
        List<Restaurant> restorani = rr.findAll();
        model.addAttribute("restorani", restorani);
        return "restaurant";
    }

    @GetMapping("/top5Restorana")
    public String petRestorana(Model model) {
        List<Restaurant> restorani = rr.findTop5ByOcena();
        model.addAttribute("restorani", restorani);
        return "restaurant";
    }

    @GetMapping("/restorani/create")
    public String create(Model model){
        model.addAttribute("restoran", new Restaurant());
        return "editRestaurant";
    }

    @PostMapping("/restorani/save")
    public String save(@Validated Restaurant restoran, BindingResult result) {
        if (result.hasErrors()) {
            return "editRestaurant";
        }
        rr.save(restoran);
        return "redirect:/restorani";
    }

    @GetMapping("/restorani/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Restaurant> restoran = rr.findById(id);
        if (restoran.isPresent()) {
            model.addAttribute("restoran", restoran.get());
            return "editRestaurant";
        } else {
            return "redirect:/restorani";
        }
    }

    @GetMapping("/restorani/delete/{id}")
    public String delete(@PathVariable Long id) {
        rr.deleteById(id);
        return "redirect:/restorani";
    }
}
