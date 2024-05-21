package ex04;

public class Program {
    public static void main(String[] args) {

        TransactionsService transactionsService = new TransactionsService();
        transactionsService.addUser("Morfinov", 900);
        transactionsService.addUser("Delilahl", 750);
        transactionsService.addUser("Changsky", 850);

        System.out.println("----- all users list before -----");
        transactionsService.printAllUsers();

        transactionsService.addTransaction(2, 1, 222);
        transactionsService.addTransaction(2, 3, 111);
        transactionsService.addTransaction(3, 1, 333);
        transactionsService.addTransaction(1, 2, 33);


        System.out.println();
        System.out.println("----- all transactions list -----");
        transactionsService.printAllTransactions();

        System.out.println();
        System.out.println("----- transactions list by user id -----");
        Transaction[] transactionsByUser = transactionsService.getTransactionsByUserId(2);
        transactionsService.printTransactions(transactionsByUser);

        System.out.println();
        System.out.println("----- delete transaction by user id -----");
        Transaction transactionDel = transactionsService.removingTransactionsById(2, transactionsByUser[0].getId());
        System.out.println("Transfer To " + transactionDel.getRecipient().getName() + "(id = " + transactionDel.getRecipient().getId() + ") " + transactionDel.getAmount() + " removed");

        System.out.println();
        System.out.println("----- transactions list by user id after delete -----");
        transactionsByUser = transactionsService.getTransactionsByUserId(2);
        transactionsService.printTransactions(transactionsByUser);

        System.out.println();
        System.out.println("----- all transactions list after delete -----");
        transactionsService.printAllTransactions();

        System.out.println();
        System.out.println("----- all users list after -----");
        transactionsService.printAllUsers();

        System.out.println();
        System.out.println("----- transfer validity -----");
        Transaction[] transferValidity = transactionsService.transferValidity();
        transactionsService.printTransactionsValidity(transferValidity);
        System.out.println();
    }
}
