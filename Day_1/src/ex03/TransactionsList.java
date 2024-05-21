package ex03;

public interface TransactionsList {
    public void addTransaction(Transaction Transaction);
    public Transaction removeTransaction(String id); 
    public Transaction[] toArray();
}
