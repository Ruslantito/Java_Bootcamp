package ex05;

public class UsersArrayList implements UsersList {
    private int size = 0;
    private int capacity = 10;
    private User[] array = new User[capacity];
    
    @Override
    public void addUser(User user) {
        if(size >= capacity) {
            increaseArray();
        }
        for(int i = size; i < capacity; ++i) {
            if(array[i] == null) {
                array[i] = user;
                size++;
                break;
            }
        }
    }

    @Override
    public User getUserById(int id) {
        for(int i = 0; i < size; ++i) {
            if(array[i].getId() == id) {
                return array[i];
            }
        }
        throw new UserNotFoundException("User not found!");
    }
    
    @Override
    public User getUserByIndex(int index) {
        return array[index];
    }

    @Override
    public int getCountUsers() {
        return size;
    }

    public void printAll() {
        for(int i = 0; i < size; ++i) {
            array[i].print();
        }
    }

    public void print(User user) {
        if(user != null) {
            user.print();
        }
    }

    private void increaseArray() {
        int capacityTmp = (int)(capacity * 1.5f);
        User[] arrayTmp = new User[capacityTmp];
        for(int i = 0; i < capacity; ++i) {
            arrayTmp[i] = array[i];
        }
        array = arrayTmp;
        capacity = capacityTmp;
    }
}
