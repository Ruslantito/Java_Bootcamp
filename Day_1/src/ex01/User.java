package ex01;

public class User {
    private final int identifier;
    private String name;
    private int balance;

    public User(String name, int balance) {
        this.identifier = UserIdsGenerator.getInstance().generateId();        
        this.name = name;
        setBalance(balance);
    }

    public int getId() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = 0;
        if(balance > 0) {
            this.balance = balance;
        }      
    }

    public void print() {
        System.out.println("identifier: " + identifier);
        System.out.println("name:       " + name);
        System.out.println("balance:    " + balance);
    }
}
