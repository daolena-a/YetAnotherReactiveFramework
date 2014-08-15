package com.yet.another.reactive.framework.pool;

import com.yet.another.reactive.framework.CallableData;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created with IntelliJ IDEA.
 * User: Adrien
 * Date: 14/08/14
 * Time: 23:19
 * To change this template use File | Settings | File Templates.
 */
public class CallableDataPool<T extends CallableData<?,?>> {
    private Initializer<T> initializer;
    Map<Integer, T> references = new ConcurrentHashMap<>();
    Integer count = 0;
    public CallableDataPool(Class<T> clazz, int poolSize, Initializer<T> _init) {
        this.clazz = clazz;
        this.poolSize = poolSize;
        initializer = _init;
        init();
    }

    private void init(){
        for(int i =0 ; i < poolSize; i++){
            try {
                T val = clazz.newInstance();
                initializer.init(val);
                references.put(i,val);
                dataQueue.add(val);
                count ++;
            } catch (InstantiationException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IllegalAccessException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    Class<T> clazz;
    int poolSize;
    ConcurrentLinkedQueue<T> dataQueue;

    public CallableData<?,?> getCallable(){
          CallableData<?,?> val =  dataQueue.poll();
        if(val == null){
            return addValue();

        }
        return val;

    }

    public T addValue(){
        T val = null;
        try {
            val = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        initializer.init(val);
        references.put(count,val);
        count++;
        return val;
    }


    public void release(T t){
        t.reset();
        dataQueue.add(t);

    }
}
