package foodBuddy.foodBuddy.recipeManagement;

import lombok.*;

import java.util.List;

@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeResponse {

    private List<Recipe> recipeList;
    private String status;
    private String message;
}



