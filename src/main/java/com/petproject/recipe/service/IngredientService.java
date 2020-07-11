package com.petproject.recipe.service;

import com.petproject.recipe.commands.IngredientCommand;
import org.springframework.stereotype.Service;

@Service
public interface IngredientService {



    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);


}
