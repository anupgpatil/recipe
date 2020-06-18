package anup.springlearn.recipe.controllers;

import anup.springlearn.recipe.model.Recipe;
import anup.springlearn.recipe.service.RecipeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class IndexControllerTest {

    IndexController indexController;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    void getIndexPage() {
        Set<Recipe> recipes = new HashSet<Recipe>();
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipes.add(recipe);
        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);
        recipes.add(recipe2);

        when(recipeService.findAllRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argCaptor = ArgumentCaptor.forClass(Set.class);

        String response = indexController.getIndexPage(model);

        Assertions.assertEquals(response,"index");

        Mockito.verify(recipeService,Mockito.times(1)).findAllRecipes();
        Mockito.verify(model, Mockito.times(1)).addAttribute(eq("recipes"), argCaptor.capture());
        Set<Recipe> recipesResp = argCaptor.getValue();
        Assertions.assertEquals(2,recipesResp.size());
    }
}
