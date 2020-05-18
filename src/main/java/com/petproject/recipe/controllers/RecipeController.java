package com.petproject.recipe.controllers;


import com.petproject.recipe.service.RecipeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeServiceImpl recipeService;

    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/list"})
    public String list(Model model){
        model.addAttribute("recipes", recipeService.getRecipes());
        return "recipe/index";
    }
}
