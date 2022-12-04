public class VerifyReports {
    public MonthlyReport monthlyReport;
    public YearlyReport yearlyReport;

    public VerifyReports(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;
    }

    public boolean verifyReports(int numM) { //метод сверке данных из годового и месячного отчёта
        boolean isReportsIdentical = true; //метка, которая меняется на false при несоответссвии данных

        int sumIncMonth = 0;
        int sumExpMonth = 0;


        for (MonthParameters datum : monthlyReport.monthData) { //вытаскиваем данные из месячного отчёта
            if (datum.is_expense == false) {
                sumIncMonth += datum.sum_of_one * datum.quantity;
            } else {
                sumExpMonth += datum.sum_of_one * datum.quantity;
            }
        }

        for (YearParameters datum : yearlyReport.yearData) { //производим сравнение данных
            if (datum.month == numM) {
                if (datum.is_expense == false) {
                    if (datum.amount != sumIncMonth) {
                        System.out.println("В месяце " + datum.month + " обнаружено несоответствие");
                        isReportsIdentical = false;
                    }
                } else {
                    if (datum.amount != sumExpMonth) {
                        System.out.println("В месяце " + datum.month + " обнаружено несоответствие");
                        isReportsIdentical = false;
                    }
                }
            }
        }
       return isReportsIdentical;
        }
    }