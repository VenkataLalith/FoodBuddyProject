package foodBuddy.foodBuddy.inventory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
public class ViewItemsRequest {
    private String groupCode;
}
