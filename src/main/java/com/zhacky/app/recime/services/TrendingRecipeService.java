package com.zhacky.app.recime.services;

import com.zhacky.app.recime.domain.Difficulty;
import com.zhacky.app.recime.domain.TrendingRecipe;

import java.util.List;


/**
 * Service for getting a list of TrendingRecipe objects from the repository.
 *
 *
 */
public interface TrendingRecipeService {
    List<TrendingRecipe> getListOfTrendingRecipes();

    List<TrendingRecipe> getListOfTrendingRecipesByDifficulty(Difficulty difficulty);
}
