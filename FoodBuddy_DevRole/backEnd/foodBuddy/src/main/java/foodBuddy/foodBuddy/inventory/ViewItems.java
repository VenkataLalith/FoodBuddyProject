package foodBuddy.foodBuddy.inventory;

import lombok.*;

@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewItems {
    private  String itemName;
    private String expDate;
    private int quantity;
}
