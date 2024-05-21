package ex03;

public class Program {
    public static void main(String[] args) {
        User Morfinov = new User("Morfinov", 900);
        User Delilahl = new User("Delilahl", 750);
        User Changsky = new User("Changsky", 850);
        
        Transaction transaction_1 = new Transaction(Delilahl, Morfinov, Transaction.Type.OUTCOME, -600);
        Transaction transaction_2 = new Transaction(Morfinov, Delilahl, Transaction.Type.INCOME, 600);
        Transaction transaction_3 = new Transaction(Changsky, Delilahl, Transaction.Type.OUTCOME, -200);
        Transaction transaction_4 = new Transaction(Delilahl, Changsky, Transaction.Type.INCOME, 200);

        TransactionsLinkedList transactionsList = new TransactionsLinkedList();
        transactionsList.addTransaction(transaction_1);
        transactionsList.addTransaction(transaction_2);
        transactionsList.addTransaction(transaction_3);
        transactionsList.addTransaction(transaction_4);

        Delilahl.addTransaction(transaction_1);
        Morfinov.addTransaction(transaction_2);
        Changsky.addTransaction(transaction_3);
        Delilahl.addTransaction(transaction_4);


        System.out.println();
        System.out.println("----- by users transactions -----");
        Delilahl.getTransactions().print();
        Morfinov.getTransactions().print();
        Changsky.getTransactions().print();
        
        System.out.println();
        System.out.println("----- all transactions list -----");
        transactionsList.print();

        System.out.println();
        System.out.println("----- transactions list minus second record -----");
        Transaction removedTrans = transactionsList.removeTransaction(transaction_2.getId());
        transactionsList.print();

        System.out.println();
        System.out.println("----- by users transactions list minus second record -----");
        Transaction removedTrans2 = removedTrans.getSender().removeTransaction(removedTrans.getId());
        System.out.println("removed:    " + removedTrans2.getId());

        System.out.println();
        Delilahl.getTransactions().print();
        Morfinov.getTransactions().print();
        Changsky.getTransactions().print();


        System.out.println();
        System.out.println("----- transactions transformed to array -----");
        Transaction[] transformedToArray = transactionsList.toArray();
        
        for(int i = 0; i < transformedToArray.length; ++i) {
            transformedToArray[i].print();
        }
    }
}
