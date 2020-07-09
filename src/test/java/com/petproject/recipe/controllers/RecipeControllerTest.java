package com.petproject.recipe.controllers;

import com.petproject.recipe.domain.Recipe;
import com.petproject.recipe.service.RecipeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    @Mock
    RecipeServiceImpl recipeService     ;

    @Mock
    Model model;

    RecipeController recipeController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    public void testMockMVC() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(get("/recipe"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/index"));
    }

    @Test
    @DisplayName("Testing Recipes List")
    public void testListRecipe() {
        Set<Recipe> recipes = new HashSet<>();
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        recipe1.setId(1L);
        recipe2.setId(2L);
        recipes.add(recipe1);
        recipes.add(recipe2);

        when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);


        String viewName = recipeController.listRecipe(model);
        assertEquals("recipe/index", viewName);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());


    }

    @Test
    @DisplayName("Testing get Recipe by Id")
    public void testGetRecipeById() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"));
                //.andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testNewRecipeForm() throws Exception{

        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().size(1)).andExpect(model().attributeExists("recipes"));
    }

}