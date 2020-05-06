package anup.springlearn.recipe.bootstrap;

import anup.springlearn.recipe.model.*;
import anup.springlearn.recipe.repositories.CategoryRepository;
import anup.springlearn.recipe.repositories.RecipeRepository;
import anup.springlearn.recipe.repositories.UnitOfMesaurementRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StartupLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMesaurementRepository unitOfMesaurementRepository;
    private final RecipeRepository recipeRepository;

    public StartupLoader(CategoryRepository categoryRepository, UnitOfMesaurementRepository unitOfMesaurementRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMesaurementRepository = unitOfMesaurementRepository;
        this.recipeRepository = recipeRepository;
    }

    /*
    * INSERT INTO category (description) VALUES ('American');
INSERT INTO CATEGORY (description) VALUES ('Indian');
INSERT INTO CATEGORY (description) VALUES ('Chinese');
INSERT INTO CATEGORY (description) VALUES ('Italian');
INSERT INTO CATEGORY (description) VALUES ('Mexican');
INSERT INTO UNIT_OF_MEASURE (description) VALUES ('TeaSpoon');
INSERT INTO UNIT_OF_MEASURE (description) VALUES ('TableSpoon');
INSERT INTO UNIT_OF_MEASURE (description) VALUES ('Grams');
INSERT INTO UNIT_OF_MEASURE (description) VALUES ('Ounces');*/

    public List<Recipe> getRecipes() {

        List<Recipe> listRecipes =new ArrayList<>();

        Optional<UnitOfMeasure> teaSpoonOptUOM = unitOfMesaurementRepository.findByDescription("TeaSpoon");
        Optional<UnitOfMeasure> tbSpoonOptUOM = unitOfMesaurementRepository.findByDescription("TableSpoon");
        Optional<UnitOfMeasure> gmOptUOM = unitOfMesaurementRepository.findByDescription("Grams");
        Optional<UnitOfMeasure> ozOptUOM = unitOfMesaurementRepository.findByDescription("Ounces");
        Optional<UnitOfMeasure> ctOptUOM = unitOfMesaurementRepository.findByDescription("Count");

        UnitOfMeasure teaSpoonUOM = teaSpoonOptUOM.get();
        UnitOfMeasure tbSpoonUOM = tbSpoonOptUOM.get();
        UnitOfMeasure gmUOM = gmOptUOM.get();
        UnitOfMeasure ozUOM = ozOptUOM.get();
        UnitOfMeasure ctUOM = ctOptUOM.get();

        Optional<Category> mexicanCategoryOpt = categoryRepository.findByDescription("Mexican");
        Optional<Category> indianCategoryOpt = categoryRepository.findByDescription("Indian");
        Optional<Category> americanCategoryOpt = categoryRepository.findByDescription("American");

        Category mexCategory = mexicanCategoryOpt.get();
        Category indianCategory = indianCategoryOpt.get();
        Category americanCategory = americanCategoryOpt.get();

        Recipe guacamole = new Recipe();
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setCooktime(0);
        guacamole.setPreptime(20);
        guacamole.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. "+
                "\n 2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)"+
                "\n 3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving."+
                "\n 4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");

        guacamole.getCategories().add(americanCategory);
        guacamole.getCategories().add(mexCategory);

        guacamole.getIngredients().add(new Ingredient("Ripe Avocados",new BigDecimal(2), ctUOM, guacamole));
        guacamole.getIngredients().add(new Ingredient("Minced Onion",new BigDecimal(.5),ctUOM, guacamole));
        guacamole.getIngredients().add(new Ingredient("Salt",new BigDecimal(.5),teaSpoonUOM, guacamole));
        guacamole.getIngredients().add(new Ingredient("Crushed Pepper",new BigDecimal(1),tbSpoonUOM, guacamole));
        guacamole.getIngredients().add(new Ingredient("Chopped Tomato",new BigDecimal(1),ctUOM, guacamole));
        guacamole.getIngredients().add(new Ingredient("Chopped Coriander",new BigDecimal(2),teaSpoonUOM, guacamole));

        guacamole.setServings(2);
        Notes guacNotes = new Notes();
        guacNotes.setRecipe(guacamole);
        guacNotes.setRecipeNotes("Be careful handling chiles if using. Wash your hands thoroughly after handling and do not touch your eyes or the area near your eyes with your hands for several hours");
        guacamole.setNotes(guacNotes);

        listRecipes.add(guacamole);
        return listRecipes;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }
}
