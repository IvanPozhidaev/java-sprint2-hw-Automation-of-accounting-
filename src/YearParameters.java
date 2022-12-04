class YearParameters { // задаю свой класс параметров
    boolean is_expense;
    int month;
    int amount;
    int yearNum;

    public YearParameters(int month, int amount, boolean is_expense, int yearNum) {
        this.month = month;
        this.amount = amount;
        this.is_expense = is_expense;
        this.yearNum = yearNum;
    }
}
