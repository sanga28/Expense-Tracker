import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExpenseManager expenseManager = new ExpenseManager();
        FileHandler fileHandler = new FileHandler();
        Scanner scanner = new Scanner(System.in);

        // Load expenses from file if they exist
        List<Expense> expenses = fileHandler.loadExpensesFromFile();
        for (Expense expense : expenses) {
            expenseManager.addExpense(expense);
        }

        while (true) {
            // Display menu
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. View Expenses by Category");
            System.out.println("4. View Total Expenses");
            System.out.println("5. Save Expenses to File");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add Expense
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();

                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();

                    LocalDate date = LocalDate.now(); // Use current date for the expense
                    Expense expense = new Expense(amount, category, date, description);
                    expenseManager.addExpense(expense);
                    System.out.println("Expense added successfully.");
                    break;

                case 2:
                    // View All Expenses
                    expenseManager.printAllExpenses();
                    break;

                case 3:
                    // View Expenses by Category
                    System.out.print("Enter category: ");
                    String filterCategory = scanner.nextLine();
                    List<Expense> filteredExpenses = expenseManager.getExpensesByCategory(filterCategory);
                    if (filteredExpenses.isEmpty()) {
                        System.out.println("No expenses found for this category.");
                    } else {
                        for (Expense exp : filteredExpenses) {
                            System.out.println(exp);
                        }
                    }
                    break;

                case 4:
                    // View Total Expenses
                    double totalExpenses = expenseManager.getTotalExpenses();
                    System.out.println("Total Expenses: " + totalExpenses);
                    break;

                case 5:
                    // Save Expenses to File
                    fileHandler.saveExpensesToFile(expenseManager.getAllExpenses());
                    break;

                case 6:
                    // Exit
                    System.out.println("Exiting Expense Tracker.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
