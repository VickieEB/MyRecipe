package com.petproject.recipe.bootstrap;

import com.petproject.recipe.domain.*;
import com.petproject.recipe.domain.enums.Difficulty;
import com.petproject.recipe.repositories.CategoryRepository;
import com.petproject.recipe.repositories.RecipeRepository;
import com.petproject.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeRepository recipeRepository;

    public RecipeDataLoader(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("Getting Bootstrap Data");
        recipeRepository.saveAll(getRecipes());
        log.debug("Bootstrap Data - Loaded");

    }



    private List<Recipe> getRecipes() {
        List<Recipe> recipelist = new ArrayList<>();


        //Get Category
        Optional<Category> americanCatOptional = categoryRepository.findByDescription("American");
        if(!americanCatOptional.isPresent()){
            throw new RuntimeException("American Not Found");
        }

        Optional<Category> italianCatOptional = categoryRepository.findByDescription("Italian");
        if(!italianCatOptional.isPresent()){
            throw new RuntimeException("Italian Not Found");
        }

        Optional<Category> mexicanCatOptional = categoryRepository.findByDescription("Mexican");
        if(!mexicanCatOptional.isPresent()){
            throw new RuntimeException("Mexican Not Found");
        }

        Optional<Category> fastFoodCatOptional  = categoryRepository.findByDescription("Fast Food");
        if(!fastFoodCatOptional.isPresent()){
            throw new RuntimeException("Fast Food Not Found");
        }

        Category americanCat = americanCatOptional.get();
        Category italianCat = italianCatOptional.get();
        Category mexicanCat = mexicanCatOptional.get();
        Category fastfoodCat = fastFoodCatOptional.get();


        //get Unit of Measures
        Optional<UnitOfMeasure> teaspoonUOMOptional =  unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!teaspoonUOMOptional.isPresent()){
            throw new RuntimeException("teaspoonUOM Not Found");
        }

        Optional<UnitOfMeasure> tablespoonUOMOptional =  unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!tablespoonUOMOptional.isPresent()){
            throw new RuntimeException("tablespoonUOM Not Found");
        }

        Optional<UnitOfMeasure> cupUOMOptional =  unitOfMeasureRepository.findByDescription("Cup");
        if(!cupUOMOptional.isPresent()){
            throw new RuntimeException("cupUOM Not Found");
        }

        Optional<UnitOfMeasure> pinchUOMOptional =  unitOfMeasureRepository.findByDescription("Pinch");
        if(!pinchUOMOptional.isPresent()){
            throw new RuntimeException("pinchUOM Not Found");
        }

        Optional<UnitOfMeasure> ounceUOMOptional =  unitOfMeasureRepository.findByDescription("Ounce");
        if(!ounceUOMOptional.isPresent()){
            throw new RuntimeException("ounceUOM Not Found");
        }

        Optional<UnitOfMeasure> eachUOMOptional =  unitOfMeasureRepository.findByDescription("Each");
        if(!eachUOMOptional.isPresent()){
            throw new RuntimeException("eachUOM Not Found");
        }

        Optional<UnitOfMeasure> dashUOMOptional =  unitOfMeasureRepository.findByDescription("Dash");
        if(!dashUOMOptional.isPresent()){
            throw new RuntimeException("dashUOM Not Found");
        }

        Optional<UnitOfMeasure> pintUOMOptional =  unitOfMeasureRepository.findByDescription("Pint");
        if(!pintUOMOptional.isPresent()){
            throw new RuntimeException("pintUOM Not Found");
        }

        Optional<UnitOfMeasure> someUOMOptional =  unitOfMeasureRepository.findByDescription("Some");
        if(!someUOMOptional.isPresent()){
            throw new RuntimeException("Some UOM Not Found");
        }

        //Get Optionals
        UnitOfMeasure teaspoonUOM = teaspoonUOMOptional.get();
        UnitOfMeasure tablespoonUOM =  tablespoonUOMOptional.get();
        UnitOfMeasure cupUOM = cupUOMOptional.get();
        UnitOfMeasure pinchUOM = pinchUOMOptional.get();
        UnitOfMeasure ounceUOM = ounceUOMOptional.get();
        UnitOfMeasure eachUOM = eachUOMOptional.get();
        UnitOfMeasure dashUOM = dashUOMOptional.get();
        UnitOfMeasure pintUOM = pintUOMOptional.get();
        UnitOfMeasure someUOM = someUOMOptional.get();


        //Create Recipe
        Recipe perfectGuacamole = new Recipe();
        perfectGuacamole.setDescription("Perfect Guacamole");
        perfectGuacamole.setDifficulty(Difficulty.HARD);
        perfectGuacamole.getCategories().add(mexicanCat);
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setServings(4);
        perfectGuacamole.setCookTime(15);
        perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        perfectGuacamole.addIngredient(new Ingredient("Ripe Avocado", new BigDecimal(2), eachUOM));
        perfectGuacamole.addIngredient(new Ingredient("Salt", new BigDecimal(.5), tablespoonUOM));
        perfectGuacamole.addIngredient(new Ingredient("Fresh Lime Juice or Lemon Juice", new BigDecimal(1), tablespoonUOM));
        perfectGuacamole.addIngredient(new Ingredient("Minced Onion or thinly sliced green Onion", new BigDecimal(2), tablespoonUOM));
        perfectGuacamole.addIngredient(new Ingredient("Serrano Chiles, stems and seeds removed, minced", new BigDecimal(2), eachUOM));
        perfectGuacamole.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tablespoonUOM));
        perfectGuacamole.addIngredient(new Ingredient("Freshly grated Black Pepper", new BigDecimal(2), dashUOM));
        perfectGuacamole.addIngredient(new Ingredient("Ripe tomato, seeds and pulp removed, chopped", new BigDecimal(.5), eachUOM));
        perfectGuacamole.addIngredient(new Ingredient("Red radishes or jicama, to garnish", new BigDecimal(1), someUOM));
        perfectGuacamole.addIngredient(new Ingredient("Tortilla chips, to serve", new BigDecimal(1), someUOM));

        perfectGuacamole.setDirections("1. Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. Place in a bowl.\n" +
                "2. Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3. Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving\n" +
                "4. Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");

        Notes perfectGuacNotes = new Notes();
        perfectGuacNotes.setRecipeNotes("The simplest version of guacamole is just mashed avocados with salt. Don’t let the lack of availability of other ingredients stop you from making guacamole." +
                "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados." +
                "Don’t have enough avocados? To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.");
        //perfectGuacNotes.setRecipe(perfectGuacamole);
        perfectGuacamole.setNotes(perfectGuacNotes);

        recipelist.add(perfectGuacamole);



        //Taco Recipe
        Recipe spicyGrilledChickenTacos = new Recipe();
        spicyGrilledChickenTacos.setDescription("Spicy Grilled Chicken Tacos Recipe");
        spicyGrilledChickenTacos.setDifficulty(Difficulty.MODERATE);
        spicyGrilledChickenTacos.getCategories().add(americanCat);
        spicyGrilledChickenTacos.getCategories().add(mexicanCat);
        spicyGrilledChickenTacos.setPrepTime(20);
        spicyGrilledChickenTacos.setServings(6);
        spicyGrilledChickenTacos.setCookTime(15);
        spicyGrilledChickenTacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");


        spicyGrilledChickenTacos.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat." +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings." +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes." +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving." +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");
        //tacoNotes.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.setNotes(tacoNotes);


        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tablespoonUOM, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("Dried Oregano", new BigDecimal(1), teaspoonUOM, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("Dried Cumin", new BigDecimal(1), teaspoonUOM, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("Sugar", new BigDecimal(1), teaspoonUOM, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("Salt", new BigDecimal(".5"), teaspoonUOM, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), eachUOM, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("finely grated orange zestr", new BigDecimal(1), tablespoonUOM, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tablespoonUOM, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("Olive Oil", new BigDecimal(2), tablespoonUOM, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("boneless chicken thighs", new BigDecimal(4), tablespoonUOM, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("small corn tortillasr", new BigDecimal(8), eachUOM, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("packed baby arugula", new BigDecimal(3), cupUOM, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), eachUOM, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUOM, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pintUOM, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), eachUOM, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUOM, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupUOM, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("lime, cut into wedges", new BigDecimal(4), eachUOM, spicyGrilledChickenTacos));

        recipelist.add(spicyGrilledChickenTacos);
        return recipelist;
    }


}
