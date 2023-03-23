package foodBuddy.foodBuddy.constants;

import lombok.*;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class AppConstants {
    private static final String url = "https://api.spoonacular.com/recipes/findByIngredients?";
    private static final String ingredientLiteral = "ingredients=";
    private static final String apiKeyLiteral = "apiKey=";
    private static final String apiKey = "fab641bba03c4195be52c40c98f8ac24";
    private static final String ampersand = "&";
    private static final int recipeCount = 15;

    private static final String numberLiteral = "number=";
    private static final int number = 20;

    public static String getUrl() {
        return url;
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
