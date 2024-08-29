package org.example;

import java.util.*;

public class Program {
    private boolean flag = true;
    private boolean started = false;
    private List<Transaction> transactions = new ArrayList<>();
    Map<String, Integer> freq = new HashMap<>();
    int totalExpense = 0;

    public Program() {
        System.out.println("***************************************************************");
        System.out.println("\t Welcome to Personal Finance Tracker ");
        System.out.println("***************************************************************");
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        while(flag){
            System.out.println();
            System.out.println("1. Input Transaction");
            System.out.println("2. View Transaction");
            System.out.println("3. View Summary");
            System.out.println("4. Get Insights");
            System.out.println("5. Exit");
            System.out.println("Choose the option : ");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    inputTransaction();
                    break;
                case 2:
                    viewTransacion();
                    break;
                case 3:
                    viewSummary();
                    break;
                case 4:
                    getInsights();
                    break;
                case 5:
                    flag = false;
                    System.out.println("Exiting the program.... Goodbye\n");
                    break;
                default:
                    System.out.println("Invalid choice please choose again.");
            }
        }
    }

    public void inputTransaction(){
        Transaction transaction = new Transaction();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter transaction description: ");
        String describtion = scanner.nextLine();

        System.out.println("Enter transaction amount (positive for income, negative for expense): ");
        int amount = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter category (e.g., Food, Entertainment, Bills): ");
        String category = scanner.nextLine();

        transaction.setDescribtion(describtion);
        transaction.setAmount(amount);
        transaction.setCategory(category);

        if(amount < 0){
            totalExpense += Math.abs(amount);
            freq.merge(category, Math.abs(amount), Integer::sum);
        }

        transactions.add(transaction);
        started = true;
    }

    public void viewTransacion(){
        if(started){
            Scanner scanner = new Scanner(System.in);

            viewAllTransactions(transactions);

            System.out.println("Do you want to sort transactions by amount? (y/n): ");
            String choice = scanner.next();

            if (choice.equalsIgnoreCase("y")){

                System.out.println("How do you want to sort transactions? (1. from low to high/ 2. from high to low): ");
                int ch = scanner.nextInt();

                if (ch == 1) sortLowToHigh();
                else if (ch == 2) sortHighToLow();
                else System.out.println("Invalid choice please choose again.");
            }
        }
        else System.out.println("You need to input transactions");
    }

    public void viewAllTransactions(List<Transaction> transactionList){
        System.out.printf("%-20s %-10s %-15s%n", "Description", "Amount", "Category");
        System.out.println("------------------------------------------------------");
        for (Transaction t : transactionList){
            System.out.printf("%-20s %-10d %-15s%n", t.getDescribtion(), t.getAmount(), t.getCategory());
        }
        System.out.println();
    }

    public void sortLowToHigh(){
        List<Transaction> transactions2 = transactions;
        Collections.sort(transactions2, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction t1, Transaction t2) {
                return
                Integer.compare(t1.getAmount(), t2.getAmount());
            }
        });
        viewAllTransactions(transactions2);
    }

    public void sortHighToLow(){
        List<Transaction> transactions2 = transactions;
        Collections.sort(transactions2, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction t1, Transaction t2) {
                return
                Integer.compare(t2.getAmount(), t1.getAmount());

            }
        });
        viewAllTransactions(transactions2);
    }

    public void viewSummary(){
        if(started){
            System.out.println("Summary :");
            int income = 0;
            int expense = 0;
            for (Transaction t:transactions){
                if(t.getAmount() > 0) income+= t.getAmount();
                else expense += t.getAmount();
            }
            System.out.println("Total Income: " + income);
            System.out.println("Total Expenses: " + expense);
            int balance = income + expense;
            System.out.println("Balance: " + balance);
        }
        else System.out.println("You need to input transactions");

    }

    public void getInsights(){
        if (started) {
            System.out.println("Spending Insights : ");
            System.out.println("Total Expense : " + totalExpense);
            for (Map.Entry<String, Integer> mp : freq.entrySet()) {
                String key = mp.getKey();
                int val = mp.getValue();
                double percent = ((double) val / totalExpense) * 100;
                System.out.printf("Category: %s - spent: %d (%.4f%%) of total\n", key, val, percent);
            }
        }
        else System.out.println("You need to input transactions");
    }

}
