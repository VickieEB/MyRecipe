package com.petproject.recipe.service;

import com.petproject.recipe.domain.Recipe;
import org.springframework.stereotype.Service;



@Service
public interface RecipeService {

    Iterable<Recipe> getRecipes();

}
