package com.petproject.recipe.service;

import com.petproject.recipe.commands.RecipeCommand;
import com.petproject.recipe.domain.Recipe;
import org.springframework.stereotype.Service;



@Service
public interface RecipeService {

    Iterable<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    RecipeCommand findRecipeCommandById(Long id);
}
