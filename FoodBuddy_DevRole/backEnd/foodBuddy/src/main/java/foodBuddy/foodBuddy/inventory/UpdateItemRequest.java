package foodBuddy.foodBuddy.inventory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class UpdateItemRequest {
    private final String itemName;
    private final int quantity;
    private final String expDate;
    private final String groupCode;

    private final String emailId;

}
