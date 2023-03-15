package foodBuddy.foodBuddy.inventory;

import lombok.*;

@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateItemResponse {
    private String status;
    private String message;
}
