package anup.springlearn.recipe.service;

import anup.springlearn.recipe.model.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> findAllRecipes();
}
