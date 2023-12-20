package com.zhacky.app.recime.controllers;

import com.zhacky.app.recime.domain.TrendingRecipe;
import com.zhacky.app.recime.domain.exceptions.DifficultyRequiredException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RecipeController {
    @GetMapping("")
    List<TrendingRecipe> findAll();

    @GetMapping("/")
    List<TrendingRecipe> findByDifficultyAsParam(@RequestParam String difficulty) throws DifficultyRequiredException;

    @GetMapping("/{difficulty}")
    List<TrendingRecipe> findByDifficulty(@PathVariable String difficulty) throws DifficultyRequiredException;
}
