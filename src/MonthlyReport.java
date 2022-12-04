import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    FileReader fileReader = new FileReader(); // импорт метода чтения файла
    public ArrayList<MonthParameters> monthData = new ArrayList<>(); // Месячный отчёт в списке построчно
    int monthNum;

    public MonthlyReport(int monthNum, String path) {
        String matter = fileReader.readFileContentsOrNull(path); // читаем все строки файла
        String[] lines = matter.split("\r?\n");
        for (int i = 1; i < lines.length; i++) { // в цикле разбиваем каждую строку на отдельные значения
            String line = lines[i];
            String[] splitLine = line.split(",");
            String item_name = splitLine[0];
            boolean is_expense = Boolean.parseBoolean(splitLine[1]);
            int quantity = Integer.parseInt(splitLine[2]);
            int sum_of_one = Integer.parseInt(splitLine[3]);
            this.monthNum = Integer.parseInt(path.substring(17, 18));

            MonthParameters monthParameters = new MonthParameters(item_name, is_expense, quantity, sum_of_one);
            monthData.add(monthParameters); // добавляем свои значения в список
        }
    }

    public void monthInfo() { // метод для вывода месячной статистики
        String topIncomeProduct = "";
        String topExpenseProduct = "";
        int highestIncome = 0;
        int highestExpense = 0;
        HashMap<String, Integer> income = new HashMap<>();// ключами будут товары, а значениями - вырученная сумма
        HashMap<String, Integer> expense = new HashMap<>();// ключи - товары, значения - сумма трат
        for (MonthParameters val : monthData) {
            if (!val.is_expense) {
                income.put(val.item_name, val.sum_of_one * val.quantity);
            } else {
                expense.put(val.item_name, val.sum_of_one * val.quantity);
            }
        }
        for (String name : income.keySet()) {
            Integer value = income.get(name);
            if (value > highestIncome) {
                highestIncome = value;
                topIncomeProduct = name;
            }
        }
        for (String name : expense.keySet()) {
            Integer value = expense.get(name);
            if (value > highestExpense) {
                highestExpense = value;
                topExpenseProduct = name;
            }
        }

        System.out.println("В месяце лучше всего продавался " + topIncomeProduct +
                ".\nПродано на сумму в " + highestIncome + ". \n" + "Самая большая трата была на товар "
                + topExpenseProduct + " и составила " + highestExpense + ".");
    }
}

