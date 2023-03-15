package foodBuddy.foodBuddy.inventory;

import foodBuddy.foodBuddy.groupManagement.ViewGroupUsers;
import lombok.*;

import java.util.List;
@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewItemsResponse {
    private List<ViewItems> itemList;
    private String status;
    private String message;
}
