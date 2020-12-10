/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raceconditiondriver;

/**
 *
 * @author Genius
 */
public class RaceConditionDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int trails = 0;
        int count = 5;
        
        ICounter counter = new Counter(count);
        
        RaceConditionRunnable runnable = new RaceConditionRunnable(counter);
        
        try{
            while(counter.value() == count){
                trails++;
                Thread t0 = new Thread(runnable, "0");
                Thread t1 = new Thread(runnable, "1");
                
                t0.start();
                t1.start();
                
                
                t0.join();
                t1.join();
                
                System.out.println("-----------------------------------------------");
            }
        }catch(InterruptedException e){
           
        }
        System.out.println("Trials "+ trails);
        System.out.println("Final count is " + counter.value());
    }
    
}
