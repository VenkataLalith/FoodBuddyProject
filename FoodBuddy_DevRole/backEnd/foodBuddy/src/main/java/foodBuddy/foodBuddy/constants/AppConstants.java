package foodBuddy.foodBuddy.constants;

public enum AppConstants {
    FIND_BY_INGREDIENTS_URL("https://api.spoonacular.com/recipes/findByIngredients?"),
    RECIPE_URL("https://api.spoonacular.com/recipes/"),
    INGREDIENT_LITERAL("ingredients="),
    API_KEY_LITERAL("apiKey="),
    API_KEY("9da1dbe7a8454d1aa4809d05d9c6ba06"),
    AMPERSAND("&"),
    RECIPE_COUNT(2),
    MAIL_PORT(587),
    TOKEN_EXPIRATION_MINUTES(15),
    RANDOM_NUMBER_MIN(100000),
    RANDOM_NUMBER_MAX(900000),
    ITEM_AMOUNT(10.0),
    PAST_USER_EXPENSES(30.4),
    PAST_USER_EXPENSES1(33.34),
    NUMBER_LITERAL("number="),
    NUMBER(20),
    NUM_ZERO(0);


    private final Object value;

    private AppConstants(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
