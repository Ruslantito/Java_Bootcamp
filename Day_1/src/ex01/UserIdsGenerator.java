package ex01;

public class UserIdsGenerator {
    private int id = 0;
    private static volatile UserIdsGenerator instance;

    public int generateId(){
        return ++id;
    }

    public static UserIdsGenerator getInstance() {
        UserIdsGenerator localInstance = instance;
        if (localInstance == null) {
            synchronized (UserIdsGenerator.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new UserIdsGenerator();
                }
            }
        }
        return localInstance;
    }
}
