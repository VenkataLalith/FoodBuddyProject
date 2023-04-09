package foodBuddy.foodBuddy.ExpenseManagementTests;

import foodBuddy.foodBuddy.expense.userExpenses;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserExpensesTest {

    @Test
    void getEmailIdTest(){
        userExpenses usp = new userExpenses("user@email.com",3534.44);
        String result = usp.getEmailId();
        assertEquals("user@email.com",result);
    }

    @Test
    void getAmountTest(){
        userExpenses usp = new userExpenses("user@email.com",3534.44);
        Double result = usp.getAmount();
        assertEquals(3534.44,result);
    }
}
