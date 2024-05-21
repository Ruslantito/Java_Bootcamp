package ex01;

import java.util.LinkedList;

public class ProdCons {
    LinkedList<Integer> productPresent = new LinkedList<>();
    int capacity = 1;

    public void prod(String str, int step) throws InterruptedException {
        int i = 0;
        while (i++ < step) {
            synchronized(this) {
                while(productPresent.size() == capacity) wait();
                System.out.println(str);                  
                productPresent.add(i);

                notify();
            }
        }
    }

    public void cons(String str, int step) throws InterruptedException {
        int i = 0;
        while (i++ < step) {
            synchronized(this) {
                while(productPresent.size() == 0) wait();
                productPresent.removeLast();
                System.out.println(str);
                notify();
            }
        }
    }
}
