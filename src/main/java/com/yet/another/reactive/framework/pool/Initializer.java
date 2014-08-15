package com.yet.another.reactive.framework.pool;

/**
 * Created with IntelliJ IDEA.
 * User: Adrien
 * Date: 15/08/14
 * Time: 16:42
 * To change this template use File | Settings | File Templates.
 */
public interface Initializer<T> {
    void init(T elem);
}
