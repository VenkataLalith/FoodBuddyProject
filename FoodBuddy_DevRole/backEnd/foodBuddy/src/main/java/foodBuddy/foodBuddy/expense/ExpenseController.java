package foodBuddy.foodBuddy.expense;

import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/expenses")
public class ExpenseController {
    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/total")
    public ExpenseResponse getTotalExpenses(@RequestBody ExpenseRequest expenseRequest){
        ExpenseResponse response =expenseService.getTotalUserExpenses(expenseRequest.getEmailId());
        return response;
    }
    @PostMapping("/groupExpenses")
    public GroupExpenseResponse getGroupExpenses(@RequestBody GroupExpenseRequest request){
        GroupExpenseResponse response= expenseService.getGroupExpenses(request);
        return response;

    }



}
