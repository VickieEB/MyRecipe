package com.petproject.recipe.service;

import com.petproject.recipe.domain.Recipe;
import com.petproject.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Iterable<Recipe> getRecipes(){
        log.info("Getting Recipes");
        return recipeRepository.findAll();
    }

}
