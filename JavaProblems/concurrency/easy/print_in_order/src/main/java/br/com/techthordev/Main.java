package br.com.techthordev;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    static void main(String[] args) throws Exception {

        // The implementation class that controls the order
        Foo foo = new Foo();

        // Define the three printing Runnables
        Runnable printFirst = () -> System.out.print("first");
        Runnable printSecond = () -> System.out.print("second");
        Runnable printThird = () -> System.out.println("third"); // Using println for final output

        // List of the three tasks
        List<Runnable> tasks = Arrays.asList(
                () -> { try { foo.first(printFirst); } catch (InterruptedException e) {} },
                () -> { try { foo.second(printSecond); } catch (InterruptedException e) {} },
                () -> { try { foo.third(printThird); } catch (InterruptedException e) {} }
        );

        // Shuffle the tasks to simulate arbitrary thread start order
        Collections.shuffle(tasks);

        // Create and start threads for each task
        Thread t1 = new Thread(tasks.get(0));
        Thread t2 = new Thread(tasks.get(1));
        Thread t3 = new Thread(tasks.get(2));

        t1.start();
        t2.start();
        t3.start();

        // Wait for all threads to finish before the program exits
        t1.join();
        t2.join();
        t3.join();

        System.out.println("\nExecution complete. Output should be 'firstsecondthird'.");
    }
}