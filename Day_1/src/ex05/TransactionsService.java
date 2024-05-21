package ex05;

public class TransactionsService {
    private UsersArrayList userList = new UsersArrayList();
    private TransactionsLinkedList transactionsList = new TransactionsLinkedList();

    public void addUser(User user) {
       userList.addUser(user);
    }
    public void addUser(String userName, int amount) {
        User user = new User(userName, amount);
        userList.addUser(user);
    }

    public int getBalance(User user) {
        return user.getBalance();
    }

    public User getUserById(int userId) {
        return userList.getUserById(userId);
    }

    public boolean addTransaction(int senderId, int recipientId, int amount) {
        User sender = userList.getUserById(senderId);
        if(sender == null) return false;
        User recipient = userList.getUserById(recipientId);
        if(recipient == null) return false;
        if(amount >= 0) {
            transactionProccess(sender, recipient, amount);
        } else {
            transactionProccess(recipient, sender, (amount * -1));
        }
        return true;
    }

    private void transactionProccess(User sender, User recipient, int amount) {
        int senderBalance = sender.getBalance();
        int recipientBalance = recipient.getBalance();
        if(senderBalance >= amount) {
            sender.setBalance(senderBalance - amount);
            recipient.setBalance(recipientBalance + amount);
            Transaction transaction1 = new Transaction(sender, recipient, Transaction.Type.OUTCOME, (amount * -1));
            Transaction transaction2 = new Transaction(transaction1.getId(), recipient, sender, Transaction.Type.INCOME, amount);
            transactionsList.addTransaction(transaction1);
            transactionsList.addTransaction(transaction2);
        }
    }

    public Transaction[] getTransactionsByUserId(int userId) {
        Transaction[] transactions = transactionsList.getTransByUserId(userId);
        return transactions;
    }

    public Transaction removingTransactionsById(int userId, String transactionId) {
        return transactionsList.removeTransactionById(userId, transactionId);
    }

    public Transaction[] transferValidity() {
        return transactionsList.checkTransferValidity();
    }

    public void printAllUsers() {
        userList.printAll();
    }
    public void printUser(User user) {
        userList.print(user);
    }

    public void printUserBalance(User user) {
        System.out.println(user.getName() + " - " + user.getBalance());
    }
 
    public void printAllTransactions() {
        transactionsList.print();
    }

    public void printTransactions(Transaction[] transactions) {
        int i = 0;
        if(transactions.length > 0) {
            while(transactions[i] != null) {
                transactions[i].printTransaction();
                ++i;
            }
        }
    }

    public void printTransactionsValidity(Transaction[] transactions) {
        int i = 0;
        if(transactions.length > 0) {
            while(transactions[i] != null) {
                transactions[i].printTransactionValidity();
                ++i;
            }
        }
    }

    public void printDeletedTransaction(Transaction transactions) {
        System.out.print("Transfer To " + transactions.getRecipient().getName());
        System.out.print("(id = " + transactions.getRecipient().getId() + ") "); 
        System.out.println(transactions.getAmount() + " removed");
    }
}
