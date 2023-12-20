package com.zhacky.app.recime.repositories;

import com.zhacky.app.recime.domain.Difficulty;
import com.zhacky.app.recime.domain.TrendingRecipe;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TrendingRecipeRepository extends ListCrudRepository<TrendingRecipe, Integer> {

    List<TrendingRecipe> findByDifficulty(Difficulty difficulty);
}
