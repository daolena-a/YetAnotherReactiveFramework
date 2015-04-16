package com.yet.another.reactive.framework;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * User: Adrien
 * Date: 14/08/14
 * Time: 22:36
 *
 */
public abstract class CallableData <T,E> implements Callable<T> {
    Lock lock = new ReentrantLock();
    public  void acceptData(E elem){
        lock();
        getData(elem);

    }
    public abstract void getData(E elem);
    public  void reset(){
        resetData();
        unlock();
    }
    public abstract void resetData();

    public void lock(){
        lock.lock();
    }
    public void unlock(){
        lock.unlock();
    }

}
