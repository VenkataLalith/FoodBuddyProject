package foodBuddy.foodBuddy.recipeManagement;

import foodBuddy.foodBuddy.constants.AppConstants;
import foodBuddy.foodBuddy.inventory.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecipeService {
    @Autowired
    private final InventoryRepository inventoryRepository;
    RecipeResponse viewRecipe(String groupCode) {
        RecipeResponse response = new RecipeResponse();
        try {
            response.setRecipeList(callExternalRecipeApi(groupCode));
            response.setMessage("Found Recipes");
            response.setStatus("success");
            return response;
        } catch (Exception e){
            response.setRecipeList(null);
            response.setStatus("failure");
            response.setMessage(e.getMessage());
            return response;
        }
    }

    private String fetchIngredientsAndPrepareUrl(String groupCode) throws UnsupportedEncodingException {
        String url = null;
        List<String> itemList = inventoryRepository.findItemNameList(groupCode);
        String items= String.join(",+", itemList);
        String encodedItems = URLEncoder.encode(items, "UTF-8");
        url = AppConstants.getUrl()+AppConstants.getIngredientLiteral()+encodedItems+AppConstants.getAmpersand()+
                AppConstants.getApiKeyLiteral()+AppConstants.getApiKey()
                +AppConstants.getAmpersand()+AppConstants.getNumberLiteral()+AppConstants.getNumber();
        System.out.println(url);
        return url;
    }

    private List<Recipe> callExternalRecipeApi(String groupCode) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = fetchIngredientsAndPrepareUrl(groupCode);
        ResponseEntity<Recipe[]> response = restTemplate.getForEntity(apiUrl, Recipe[].class);
        if (response.getStatusCode().is2xxSuccessful()) {
            List<Recipe> recipes = Arrays.asList(response.getBody());
            List<Recipe> sortedRecipes = recipes.stream()
                    .sorted(Comparator.comparingInt(Recipe::getMissedIngredientCount))
                    .limit(AppConstants.getRecipeCount())
                    .collect(Collectors.toList());
            return sortedRecipes;
        } else {
            throw new RuntimeException("Failed to call external API. Status code: " + response.getStatusCode());
        }
    }



}
