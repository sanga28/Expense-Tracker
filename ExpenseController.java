import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ExpenseController {

    private final ExpenseManager expenseManager = new ExpenseManager();

    @GetMapping("/")
    public String viewExpenses(Model model) {
        model.addAttribute("expenses", expenseManager.getAllExpenses());
        return "index"; // Thymeleaf template will be rendered.
    }

    @PostMapping("/addExpense")
    public String addExpense(@ModelAttribute Expense expense) {
        expenseManager.addExpense(expense);
        return "redirect:/"; // Redirect to the homepage after adding an expense.
    }
}
