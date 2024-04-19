package org.example.it355dz07.controller;

import org.example.it355dz07.model.Meal;
import org.example.it355dz07.model.Restaurant;
import org.example.it355dz07.repository.mealRepository;
import org.example.it355dz07.repository.restaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class mealController {

    @Autowired
    private mealRepository mr;

    @Autowired
    private restaurantRepository rr;

    @GetMapping("/jela")
    public String svaJela(Model model) {
        List<Meal> jela = mr.findAll();
        model.addAttribute("jela", jela);
        return "meal";
    }


    @GetMapping("/jela/create")
    public String create(Model model){
        List<Restaurant> restorani = rr.findAll();
        model.addAttribute("restorani", restorani);
        model.addAttribute("jelo", new Meal());
        return "editMeal";
    }

    @PostMapping("/jela/save")
    public String save(@Validated Meal jelo, BindingResult result) {
        if (result.hasErrors()) {
            return "editMeal";
        }
        mr.save(jelo);
        return "redirect:/jela";
    }

    @GetMapping("/jela/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Meal> jelo = mr.findById(id);

        List<Restaurant> restorani = rr.findAll();
        model.addAttribute("restorani", restorani);

        if (jelo.isPresent()) {
            model.addAttribute("jelo", jelo.get());
            return "editMeal";
        } else {
            return "redirect:/jela";
        }
    }

    @GetMapping("/jela/delete/{id}")
    public String delete(@PathVariable Long id) {
        mr.deleteById(id);
        return "redirect:/jela";
    }

    @GetMapping("/jela/search")
    public String serchJelo(@RequestParam String naziv, Model model){
        Optional<List<Meal>> jela = mr.findAllByNaziv(naziv);

        if(jela.isPresent()){
            model.addAttribute("jela", jela.get());
            return "meal";
        } else {
            return "redirect:/jela";
        }
    }


}
