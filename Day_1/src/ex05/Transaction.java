package ex05;

import java.util.UUID;

public class Transaction {
    private String identifier;
    private User sender;
    private User recipient;   
    private Type category;
    private int amount;

    public enum Type {
        INCOME,
        OUTCOME,
    }

    public Transaction() {}
    public Transaction(User sender, User recipient, Type category, int amount) {
        this.identifier = UUID.randomUUID().toString();
        this.sender = sender;
        this.recipient = recipient;
        this.category = category;
        this.amount = amount;
    }
    public Transaction(String identifier, User sender, User recipient, Type category, int amount) {
        this.identifier = identifier;
        this.sender = sender;
        this.recipient = recipient;
        this.category = category;
        this.amount = amount;
    }
   
    public String getId() {
        return identifier;
    }
    
    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Type getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    public void print() {
        System.out.print("identifier: " + identifier);
        System.out.print(" \t| sender: " + sender.getName());
        System.out.print(" \t| recipient: " + recipient.getName());
        System.out.print(" \t| category: " + category);
        System.out.println(" \t| amount: " + amount);
    }

    public void printTransaction() {
        if(category == Transaction.Type.OUTCOME) {
            System.out.print("To " + recipient.getName());
        } else {
            System.out.print("From " + recipient.getName());
        }
        System.out.print("(id = " + recipient.getId() + ") ");
        System.out.print(amount);
        System.out.println(" with id = " + identifier);
    }

    public void printTransactionValidity() {
        System.out.print(recipient.getName());
        System.out.print("(id = " + recipient.getId() + ")");
        System.out.print(" has an unacknowledged transfer id = " + identifier);
        System.out.print(" from " + sender.getName());
        System.out.print("(id = " + sender.getId() + ")");
        System.out.println(" for " + amount);
    }
}
