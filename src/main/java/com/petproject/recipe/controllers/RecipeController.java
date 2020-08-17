package com.petproject.recipe.controllers;


import com.petproject.recipe.commands.RecipeCommand;
import com.petproject.recipe.domain.Recipe;
import com.petproject.recipe.exceptions.NotFoundException;
import com.petproject.recipe.service.RecipeServiceImpl;
import com.petproject.recipe.utility.RecipePDFExporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

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

    @GetMapping("/export")
    public void exportRecipe(HttpServletResponse response)throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());
        response.setContentType("application/pdf");
        String headerKey =  "Content-Disposition";
        String headerValue = "attachment;  filename=recipelist_"+ currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        Set<Recipe> recipes = recipeService.getRecipes();
        RecipePDFExporter exporter = new RecipePDFExporter(recipes);
        exporter.export(response);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(){
        log.error("Handling not found exception");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404Error");
        return modelAndView;
    }
}
