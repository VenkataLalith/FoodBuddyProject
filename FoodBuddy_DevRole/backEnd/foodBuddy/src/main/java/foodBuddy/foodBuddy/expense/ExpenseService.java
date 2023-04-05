package foodBuddy.foodBuddy.expense;

import foodBuddy.foodBuddy.appuser.UserRepository;
import foodBuddy.foodBuddy.groupManagement.ViewGroupUsers;
import foodBuddy.foodBuddy.inventory.InventoryEntity;
import foodBuddy.foodBuddy.inventory.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserRepository userRepository;

    public ExpenseResponse getTotalUserExpenses(String emailId){
        ExpenseResponse response = new ExpenseResponse();
        response.setTotalUserExpense(expenseRepository.getPastUserExpenses(emailId));
        response.setStatus("success");
        response.setMessage("success");
        return response ;
    }

    public GroupExpenseResponse getGroupExpenses(GroupExpenseRequest request) {
        GroupExpenseResponse response = new GroupExpenseResponse();
        System.out.println(request);
        List<ViewGroupUsers> listOfUsers = userRepository.findUsersByGroupCode(request.getUserGroupNumber());
        List<userExpenses> userExpensesList = new ArrayList<>();
        for (ViewGroupUsers item: listOfUsers) {
            String emailId = item.getUsername();
            Double eachExpense = expenseRepository.getPastUserExpenses(emailId);
            userExpenses userExpenses =new userExpenses();
            userExpenses.setAmount(eachExpense);
            userExpenses.setEmailId(emailId);
            userExpensesList.add(userExpenses);
        }
        response.setGroupExpenses(userExpensesList);
        response.setMessage("success");
        response.setStatus("success");
        return response;
    }
}
