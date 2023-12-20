package com.zhacky.app.recime.services;

import com.zhacky.app.recime.domain.Difficulty;
import com.zhacky.app.recime.domain.TrendingRecipe;
import org.springframework.stereotype.Service;
import com.zhacky.app.recime.repositories.TrendingRecipeRepository;
import java.util.Comparator;
import java.util.List;

@Service
public class TrendingRecipeServiceImpl implements TrendingRecipeService {

    private final TrendingRecipeRepository trendingRecipeRepository;

    public TrendingRecipeServiceImpl(TrendingRecipeRepository trendingRecipeRepository) {
        this.trendingRecipeRepository = trendingRecipeRepository;
    }

    @Override
    public List<TrendingRecipe> getListOfTrendingRecipes() {
        List<TrendingRecipe> recipes = trendingRecipeRepository.findAll();
        recipes.sort(Comparator.comparingInt(TrendingRecipe::position));
        return recipes;
    }

    @Override
    public List<TrendingRecipe> getListOfTrendingRecipesByDifficulty(Difficulty difficulty) {
        List<TrendingRecipe> recipes = trendingRecipeRepository.findByDifficulty(difficulty);
        return recipes;
    }
}
