package org.example;

public class Transaction {
    private String describtion;
    private int amount;
    private String category;

    public Transaction() {
    }

    public Transaction(String describtion, int amount, String category) {
        this.describtion = describtion;
        this.amount = amount;
        this.category = category;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
