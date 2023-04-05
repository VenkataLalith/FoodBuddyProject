package foodBuddy.foodBuddy.expense;

import lombok.*;

@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseResponse {
    private String status;
    private String message;
    private Double totalUserExpense;

}
