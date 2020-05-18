package com.petproject.recipe.service;

import com.petproject.recipe.domain.Recipe;
import com.petproject.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;


@Service
public class RecipeServiceImpl implements RecipeService{
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Iterable<Recipe> getRecipes(){
        return recipeRepository.findAll();
    }

}
