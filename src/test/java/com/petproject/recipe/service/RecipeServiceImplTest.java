package com.petproject.recipe.service;

import com.petproject.recipe.domain.Recipe;
import com.petproject.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet<>();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);
        Set<Recipe> recipeSet = recipeService.getRecipes();
        assertEquals(recipeSet.size(), 1);

        //To verify that Mockito called the service once
        verify(recipeRepository, times(1)).findAll();

    }
}