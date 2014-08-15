package com.yet.another.reactive.framework;

import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * User: Adrien
 * Date: 14/08/14
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */
public abstract class CallableData <T,E> implements Callable<T> {
    public abstract void acceptData(E elem);
    public abstract void reset();

}
