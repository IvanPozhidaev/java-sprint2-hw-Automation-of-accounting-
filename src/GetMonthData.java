import java.util.ArrayList;

public class GetMonthData {

    public ArrayList<MonthlyReport> monthReports  = new ArrayList<>();
    MonthlyReport monthlyReport;
    String path;

    public void getMonthData () {
        for (int i = 1; i <= 3; i++) {
            path = "m.20210" + i + ".csv";
            monthlyReport = new MonthlyReport(i,"resources/"+path);
            monthReports.add(monthlyReport);
            System.out.println("Отчёт за месяц " + i + " успешно считан.");
        }
    }
}
