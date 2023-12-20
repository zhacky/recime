package com.zhacky.app.recime.controllers;

import com.zhacky.app.recime.domain.Difficulty;
import com.zhacky.app.recime.domain.TrendingRecipe;
import com.zhacky.app.recime.domain.exceptions.DifficultyNotFoundException;
import com.zhacky.app.recime.domain.exceptions.DifficultyRequiredException;
import com.zhacky.app.recime.repositories.TrendingRecipeRepository;
import com.zhacky.app.recime.services.TrendingRecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/recipes")
public class TrendingRecipeController implements RecipeController {

    private final TrendingRecipeService trendingRecipeService;

    public TrendingRecipeController(@Autowired TrendingRecipeService trendingRecipeService) {
        this.trendingRecipeService = trendingRecipeService;
    }

    @Override
    @GetMapping("")
    public List<TrendingRecipe> findAll() {
        return trendingRecipeService.getListOfTrendingRecipes();
    }

    @Override
    @GetMapping("/")
    public List<TrendingRecipe> findByDifficultyAsParam(@RequestParam String difficulty) throws DifficultyRequiredException {
        Difficulty diff = null;
        try {
            diff = Difficulty.valueOf(StringUtils.capitalize(difficulty));
        } catch (Exception e) {
            throw new DifficultyRequiredException("A difficulty is required for filtering trending recipes”");
        }
        return trendingRecipeService.getListOfTrendingRecipesByDifficulty(diff);
    }

    @Override
    @GetMapping("/{difficulty}")
    public List<TrendingRecipe> findByDifficulty(@PathVariable String difficulty) throws DifficultyRequiredException {
        if (difficulty.isBlank() || difficulty.isEmpty()) {
            throw new DifficultyRequiredException("A difficulty is required for filtering trending recipes”");
        }
        Difficulty diff;
        try {
            diff = Difficulty.valueOf(StringUtils.capitalize(difficulty));
        } catch (Exception e) {
            throw new DifficultyNotFoundException("Difficulty not found. Values allowed (Easy, Medium, Hard)");
        }
        return trendingRecipeService.getListOfTrendingRecipesByDifficulty(diff);
    }
}
