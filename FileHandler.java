import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class FileHandler {

    private static final String FILE_NAME = "expenses.csv";

    public void saveExpensesToFile(List<Expense> expenses) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Expense expense : expenses) {
                writer.write(expense.getAmount() + "," + expense.getCategory() + "," + expense.getDate() + ","
                        + expense.getDescription());
                writer.newLine();
            }
            System.out.println("Expenses saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving expenses to file.");
            e.printStackTrace();
        }
    }

    public List<Expense> loadExpensesFromFile() {
        List<Expense> expenses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                double amount = Double.parseDouble(data[0]);
                String category = data[1];
                LocalDate date = LocalDate.parse(data[2]);
                String description = data[3];
                expenses.add(new Expense(amount, category, date, description));
            }
            System.out.println("Expenses loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading expenses from file.");
            e.printStackTrace();
        }
        return expenses;
    }
}
