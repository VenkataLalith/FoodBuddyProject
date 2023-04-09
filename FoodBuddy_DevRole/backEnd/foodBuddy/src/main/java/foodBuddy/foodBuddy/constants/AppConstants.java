package foodBuddy.foodBuddy.constants;

import lombok.*;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class AppConstants {
    private static final String findByIngredientsUrl = "https://api.spoonacular.com/recipes/findByIngredients?";
    private static final String recipeUrl = "https://api.spoonacular.com/recipes/";
    private static final String ingredientLiteral = "ingredients=";
    private static final String apiKeyLiteral = "apiKey=";
    private static final String apiKey = "a6cf26b4e4d04e7abd42b0f9eee9af2c";
    private static final String ampersand = "&";
    private static final int recipeCount = 2;

    private static final String numberLiteral = "number=";
    private static final int number = 20;

    public static String getFindByIngredientsUrl() {
        return findByIngredientsUrl;
    }

    public static String getRecipeUrl() {
        return recipeUrl;
    }

    public static String getIngredientLiteral() {
        return ingredientLiteral;
    }

    public static String getApiKeyLiteral() {
        return apiKeyLiteral;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getAmpersand() {
        return ampersand;
    }
    public static int getRecipeCount() {
        return recipeCount;
    }

    public static String getNumberLiteral() {
        return numberLiteral;
    }

    public static int getNumber() {
        return number;
    }
}
