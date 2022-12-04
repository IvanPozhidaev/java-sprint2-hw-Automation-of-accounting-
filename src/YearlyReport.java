import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    // Годовой отчёт, содержит ровно по 2 записи на каждый из 12 месяцев - расход и доход за месяц
    FileReader fileReader = new FileReader(); // импорт функции чтения файла
    public int yearNum;
    public int month;
    public ArrayList<YearParameters> yearData = new ArrayList<>();

    public YearlyReport(String path) { //считывание годового отчёта
        String content = fileReader.readFileContentsOrNull(path);
        String[] lines = content.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] splitLine = line.split(",");
            month = Integer.parseInt(splitLine[0]);
            int amount = Integer.parseInt(splitLine[1]);
            boolean is_expense = Boolean.parseBoolean(splitLine[2]);
            yearNum = Integer.parseInt(path.substring(12, 16));

            YearParameters yearParameters = new YearParameters(month, amount, is_expense, yearNum);
            yearData.add(yearParameters);
        }
    }

    public void yearInfo() { // метод для вывода статистики по годовому отчёту
        int sumIncome = 0;
        int sumExpense = 0;
        HashMap<Integer, Integer> income = new HashMap<>();
        HashMap<Integer, Integer> expense = new HashMap<>();
        for (YearParameters value : yearData) {
            if (!value.is_expense) {
                income.put(value.month, value.amount);
                sumIncome += value.amount;
            } else {
                expense.put(value.month, value.amount);
                sumExpense += value.amount;
            }
        }
        int meanIncome = sumIncome / income.size();
        int meanExpense = sumExpense / expense.size();

        for (Integer num : income.keySet()) {
            System.out.println("Год - " + yearNum + ". В месяце " + num + " прибыль составила " + income.get(num));
        }
        System.out.println("Средний доход за все считанные месяцы в " + yearNum + " году составил " + meanIncome +
                ".\n" + "Средний расход за все считанные месяцы в " + yearNum + " году составил " + meanExpense +".");
    }
}
