import java.io.*;
import java.lang.Thread;

class JoinExample2 {
    public static void main(String[] args) {
        Thread th1 = new Thread(new MyClass2(), "th1");
        Thread th2 = new Thread(new MyClass2(), "th2");
        Thread th3 = new Thread(new MyClass2(), "th3");
        Thread th4 = new Thread(new MyClass2(), "th4");
        Thread th5 = new Thread(new MyClass2(), "th5");
        Thread th6 = new Thread(new MyClass2(), "th6");

        /* th1, th2 and th3 can start and ends in any order */
        th1.start();
        th2.start();
        th3.start();

        try {
        th1.join();
        th2.join();
        th3.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        /* Threads started and ended one by one in order
         * th4 starts, then wait till th4 ends then
         * th5 starts, then wait till th5 ends then
         * th6 starts, then wait till th6 ends
         */

        // Start first thread immediately
        th4.start();

        /* Start second thread(th2) once first thread(th1)
        * is dead
        */
        try {
            th4.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        th5.start();

        /* Start third thread(th3) once second thread(th2)
        * is dead
        */
        try {
            th5.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        th6.start();

        // Displaying a message once third thread is dead
        try {
            th6.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println("All six threads have finished execution");
    }
}

class MyClass2 implements Runnable{
    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("Thread started: "+t.getName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println("Thread ended: "+t.getName());
    }
}