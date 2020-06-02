package com.petproject.recipe.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() throws Exception {
        category = new Category();
    }

    @Test
    public void getId() {
        Long catId = 3l;
        category.setId(catId);
        assertEquals(catId, category.getId());
    }

    @Test
    public void getDescription() {
        String desc = "Some Recipe Description";
        category.setDescription(desc);
        assertEquals(desc,category.getDescription());
    }

    @Test
    public void getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        category.setRecipes(recipes);
        assertEquals(recipes, category.getRecipes());

    }
}