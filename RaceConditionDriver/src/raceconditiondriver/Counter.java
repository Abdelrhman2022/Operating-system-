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
public class Counter implements ICounter{
    
    private int counter = 0;
    
    public Counter(int counter) {
        this.counter = counter;
    }
    
    @Override
    public void increment() {
         counter++; 
    }

    @Override
    public void decrement() {
        counter--; 
    }

    @Override
    public int value() {
        return counter;
    }
    public String toString(){
        return Integer.toString(counter);
    }
    
}
