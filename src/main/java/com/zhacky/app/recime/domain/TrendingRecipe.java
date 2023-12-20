package com.zhacky.app.recime.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

/**
 * This TrendingRecipe class represents a recipe with details.
 * All values must be provided and must not be null nor contain any null values.
 * @param id Integer
 * @param name String
 * @param imageUrl String
 * @param difficulty Difficulty [Easy, Medium, Hard]
 * @param position Integer
 * @param version Integer
 */
@Table(name = "trending_recipes")
public record TrendingRecipe(

        @Id
        Integer id,
        String name,
        String imageUrl,
        Difficulty difficulty,
        Integer position,
        @Version
        Integer version
) {
}
