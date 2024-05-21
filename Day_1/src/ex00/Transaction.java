package ex00;

import java.util.UUID;

public class Transaction {
    private String identifier;
    private User recipient;   
    private User sender;
    private Type category;
    private int amount;

    public enum Type {
        INCOME,
        OUTCOME,
    }

    public Transaction() {}
    public Transaction(User recipient, User sender, Type category, int amount) {
        this.identifier = UUID.randomUUID().toString();
        this.recipient = recipient;
        this.sender = sender;
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
        System.out.println("identifier: " + identifier);
        System.out.println("recipient:  " + recipient.getName());
        System.out.println("sender:     " + sender.getName());
        System.out.println("category:   " + category);
        System.out.println("amount:     " + amount);
    }
}
