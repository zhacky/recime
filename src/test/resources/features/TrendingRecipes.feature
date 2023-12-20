Feature: Getting The List Of Trending Recipes

  Scenario Outline: Client makes a call to GET /api/recipes
    When the client calls /api/recipes
    Then the client receives status code of <statusCode>
    Examples:
      | statusCode |
      | 200        |