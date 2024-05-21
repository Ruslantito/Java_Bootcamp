package ex02;

public class Program {
    public static void main(String[] args) {
        User Morfinov = new User("Morfinov", 900);
        User Delilahl = new User("Delilahl", 750);
        User Changsky = new User("Changsky", 850);
        User Huberflu = new User("Huberflu", 950);
        User Ameeleon = new User("Ameeleon", 550);
        
        UsersArrayList userList = new UsersArrayList();

        userList.addUser(Morfinov);
        userList.addUser(Delilahl);
        userList.addUser(Changsky);
        userList.addUser(Huberflu);
        userList.addUser(Ameeleon);

        System.out.println("----- all users list -----");
        userList.printAll();

        System.out.println();
        System.out.println("----- get users by id -----");
        User userById = userList.getUserById(3);
        userById.print();

        System.out.println();
        System.out.println("----- get users by iindex -----");
        User userByIndex = userList.getUserByIndex(1);
        userByIndex.print();

        System.out.println();
        System.out.println("----- count users -----");
        int countUsers = userList.getCountUsers();
        System.out.println("total users = " + countUsers);
    }
}
