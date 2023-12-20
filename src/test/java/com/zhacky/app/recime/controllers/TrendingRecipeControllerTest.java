package com.zhacky.app.recime.controllers;

import com.zhacky.app.recime.domain.Difficulty;
import com.zhacky.app.recime.domain.TrendingRecipe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;


@Testcontainers(disabledWithoutDocker = true)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TrendingRecipeControllerTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0");

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldFindAllTrendingRecipeSortedByPosition() {
        TrendingRecipe[] recipes = restTemplate.getForObject("/api/recipes", TrendingRecipe[].class);
        assertThat(recipes.length).isGreaterThan(0);
        assertThat(recipes).isSortedAccordingTo(Comparator.comparingInt(TrendingRecipe::position));
    }

    @Test
    void findByDifficultyAsParam() {
        TrendingRecipe[] recipes = restTemplate.getForObject("/api/recipes/easy", TrendingRecipe[].class);
        assertThat(Arrays.stream(recipes).filter(c -> c.difficulty().equals(Difficulty.Easy)).count()).isGreaterThan(0);
    }

    @Test
    void findByDifficulty() {
        TrendingRecipe[] recipes = restTemplate.getForObject("/api/recipes/?difficulty=hard", TrendingRecipe[].class);
        assertThat(Arrays.stream(recipes).filter(c -> c.difficulty().equals(Difficulty.Hard)).count()).isGreaterThan(0);
    }
}