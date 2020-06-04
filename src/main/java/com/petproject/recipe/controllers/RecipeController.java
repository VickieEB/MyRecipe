package com.petproject.recipe.controllers;


import com.petproject.recipe.service.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeServiceImpl recipeService;

    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/list"})
    public String listRecipe(Model model){

        model.addAttribute("recipes", recipeService.getRecipes());
        log.debug("Recipes Loaded");
        return "recipe/index";
    }
}
