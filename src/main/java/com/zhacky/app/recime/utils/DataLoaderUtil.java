package com.zhacky.app.recime.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhacky.app.recime.domain.RecipeList;
import com.zhacky.app.recime.repositories.TrendingRecipeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
//@Log4j2
public class DataLoaderUtil implements CommandLineRunner {

    private final ObjectMapper objectMapper;
    private final TrendingRecipeRepository trendingRecipeRepository;

    public DataLoaderUtil(ObjectMapper objectMapper, TrendingRecipeRepository trendingRecipeRepository) {
        this.objectMapper = objectMapper;
        this.trendingRecipeRepository = trendingRecipeRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if (trendingRecipeRepository.count() == 0) {
            String TRENDING_RECIPES = "/data/recipes.json"; // sample data in json format
            System.out.println("TRENDING RECIPES: " + TRENDING_RECIPES);
//            log.info("Loading recipes into database from JSON: ", TRENDING_RECIPES);
            try (InputStream inputStream = TypeReference.class.getResourceAsStream(TRENDING_RECIPES)) {
                var response = objectMapper.readValue(inputStream, RecipeList.class);
                trendingRecipeRepository.saveAll(response.recipeList());
            } catch (Exception e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        }

    }
}
