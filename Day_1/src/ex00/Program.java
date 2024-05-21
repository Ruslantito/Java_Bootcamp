package ex00;

public class Program {
    public static void main(String[] args) {
        User Morfinov = new User(1, "Morfinov", 900);
        User Delilahl = new User(2, "Delilahl", 750);
        
        Transaction transaction_1 = new Transaction(Delilahl, Morfinov, Transaction.Type.OUTCOME, -600);
        Transaction transaction_2 = new Transaction(Morfinov, Delilahl, Transaction.Type.INCOME, 600);

        System.out.println();
        transaction_1.print();
        System.out.println("----------");
        transaction_2.print();

        System.out.println();
        Morfinov.print();
        System.out.println("----------");
        Delilahl.print();
        System.out.println();
    }
}
