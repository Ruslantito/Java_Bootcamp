package ex01;

public class Program {
    public static void main(String[] args) {
        User Morfinov = new User("Morfinov", 900);
        User Delilahl = new User("Delilahl", 750);
        User Changsky = new User("Changsky", 850);
        
        Morfinov.print();
        System.out.println("--------");
        Delilahl.print();
        System.out.println("--------");
        Changsky.print();
    }
}
