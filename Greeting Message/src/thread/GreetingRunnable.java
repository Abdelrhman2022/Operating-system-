/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import java.util.Date;

/**
 *
 * @author Genius
 */
public class GreetingRunnable implements Runnable {
private static final int REPETITIONS = 10;
private static final int DELAY = 1000;
private String greeting;

public GreetingRunnable(String greeting)
{
    this.greeting = greeting;
}
    @Override
    public void run() {
        try{
            for(int i = 0 ; i<REPETITIONS; i++)
            {
                Date now =new Date();
                System.err.println(now + " " + greeting);
                Thread.sleep(DELAY);
                
            }
        }
        catch(Exception InterruptedException){
            
        }
    }
    
}
