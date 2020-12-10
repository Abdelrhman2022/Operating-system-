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
public class RaceConditionRunnable implements Runnable {

    private ICounter count;

    public RaceConditionRunnable(ICounter count) {
        this.count = count;
    }

    public ICounter getCount() {
        return this.count;
    }

    @Override
    public void run() {
        int threadId = Integer.parseInt(Thread.currentThread().getName());
        if(threadId == 0){
            count.decrement();
        }
        else
            count.increment();
        
        System.out.println("t"+ threadId + " Count: "+ count);
    }
    
}
