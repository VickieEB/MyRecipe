package com.petproject.recipe.controllers;


import com.petproject.recipe.commands.RecipeCommand;
import com.petproject.recipe.service.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeServiceImpl recipeService;

    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping({"", "/list"})
    public String listRecipe(Model model){

        model.addAttribute("recipes", recipeService.getRecipes());
        log.debug("Recipes Loaded");
        return "recipe/index";
    }


    @GetMapping("/{id}/show")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }

    @GetMapping("/new")
    public String newRecipe(Model model){
        model.addAttribute("recipes", new RecipeCommand());
        return "recipe/recipeform";
    }

    @PostMapping("/create")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/" +  savedCommand.getId() + "/show";
    }

    @GetMapping("/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        RecipeCommand recipeCommand = recipeService.findRecipeCommandById(Long.valueOf(id));
        model.addAttribute("recipes", recipeCommand);
        return "recipe/recipeform";

    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable String id){
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/recipe/";
    }
}
