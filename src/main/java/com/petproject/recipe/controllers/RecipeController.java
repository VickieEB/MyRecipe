package com.petproject.recipe.controllers;

import com.petproject.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/list"})
    public String list(Model model){
        model.addAttribute("recipes", recipeService.getRecipes());
        return "recipe/index";
    }
}
