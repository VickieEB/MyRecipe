package com.petproject.recipe.controllers;

import com.petproject.recipe.commands.IngredientCommand;
import com.petproject.recipe.commands.RecipeCommand;
import com.petproject.recipe.commands.UnitOfMeasureCommand;
import com.petproject.recipe.service.IngredientService;
import com.petproject.recipe.service.RecipeService;
import com.petproject.recipe.service.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {

    @Mock
    RecipeService recipeService;
    @Mock
    IngredientService ingredientService;

    IngredientController ingredientController;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientController = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();

    }

    @Test
    public void listIngredientsByRecipeId() throws Exception{
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);

        //when
        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));

        //then
        verify(recipeService, times(1)).findRecipeCommandById(anyLong());
    }

    @Test
    public void testShowIngredient() throws Exception{
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();

        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

        //then
        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/ingredient/show"))
        .andExpect(model().attributeExists("ingredient"));
    }

    @Test
    public void testRecipeIngredientFormUpdate() throws Exception{
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();

        Set<UnitOfMeasureCommand> uomSet = new HashSet<>();


        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
        when(unitOfMeasureService.listAllUoms()).thenReturn(uomSet);

        //then
        mockMvc.perform(get("/recipe/1/ingredient/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));
    }

    @Test
    public void testNewIngredientForm() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        //when
        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);
        when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

        //then
        mockMvc.perform(get("/recipe/1/ingredient/new"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/ingredient/ingredientform"))
        .andExpect(model().attributeExists("ingredient"))
        .andExpect(model().attributeExists("uomList"));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1L);
        ingredientCommand.setRecipeId(1L);
        ingredientCommand.setDescription("Cameron Pepper");

        //when
        when(ingredientService.saveIngredientCommand(any())).thenReturn(ingredientCommand);

        //then
        mockMvc.perform(post("/recipe/2/ingredient")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("id", "")
            .param("description", "some ingredient")
        ).andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/recipe/1/ingredient/1/show"));

    }
}