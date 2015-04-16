package com.yet.another.reactive.framework.pool;

/**
 *
 * User: Adrien
 * Date: 15/08/14
 * Time: 16:42
 *
 */
public interface Initializer<T> {
    void init(T elem);
}
