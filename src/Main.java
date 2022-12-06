import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        GetMonthData getMonthData = new GetMonthData();
        MonthlyReport monthlyReport;
        YearlyReport yearlyReport = null;
        VerifyReports verifyReports;
        String path = null;

        Scanner scanner = new Scanner(System.in);
        printMenu();
        int inputUser = scanner.nextInt();
        while (true){
            switch(inputUser) {
                case 1:
                    getMonthData.getMonthData();
                    break;
                case 2:
                    yearlyReport = new YearlyReport("resources/y.2021.csv");
                    System.out.println("Годовой отчёт успешно считан.");
                    break;
                case 3:
                    if (getMonthData.monthReports.size() != 0) {
                        for (int i = 0; i < getMonthData.monthReports.size(); i++) {
                            MonthlyReport singleMonth = getMonthData.monthReports.get(i);
                            System.out.println("Месяц - " + (i + 1));
                            singleMonth.monthInfo();
                        }
                        break;
                    } else {
                        System.out.println("Невозможно вывести помесячную статистику - месячные отчёты не считаны.");
                        break;
                    }
                case 4:
                    if(yearlyReport != null) {
                        yearlyReport.yearInfo();
                        break;
                    } else {
                        System.out.println("Невозможно вывести годовую статистику - годовой отчёт не считан.");
                        break;
                    }
                case 5:
                    if(yearlyReport != null && getMonthData.monthReports.size() != 0) {
                        for (int i = 0; i < getMonthData.monthReports.size(); i++) {
                            MonthlyReport newMonth = getMonthData.monthReports.get(i);
                            verifyReports = new VerifyReports(newMonth, yearlyReport);
                            boolean checkResults = verifyReports.verifyReports(i+1);
                            if (checkResults) {
                                System.out.println("Сверка отчётов для месяца " + (i+1) + " завершена успешно.");
                            }
                        }
                    } else {
                        System.out.println("Месячные и годовые отчёты еще не считаны.");
                        break;
                    }
                    break;
                default:
                    //проверка ввода
                    System.out.println("Ошибка ввода команды. Введите действительную цифру команды.");
                    break;
            }
            printMenu();
            inputUser = scanner.nextInt();
            if (inputUser == 0) {
                System.out.println("Выход.");
                break;
            }
        }

    }
    private static void printMenu () {
        System.out.println("\n\tКакое действие выполняем?");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Вывести информацию о всех месячных отчётах");
        System.out.println("4 - Вывести информацию о годовом отчёте");
        System.out.println("5 - Сверить отчёты");
        System.out.println("0 - Выход");
    }
}

