/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

/**
 *
 * @author Genius
 */
public class GreetingThreadRunner {

    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        GreetingRunnable r1 = new GreetingRunnable("Hello");
        GreetingRunnable r2 = new GreetingRunnable("Goodbye");
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
        
        System.out.println("The program is finished successfully");
        
     

    }
}
