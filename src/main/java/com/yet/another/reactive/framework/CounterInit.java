package com.yet.another.reactive.framework;

import com.yet.another.reactive.framework.pool.Initializer;

/**
 *
 * User: adao-lena
 * Date: 26/08/14
 * Time: 14:25
 *
 */
public class CounterInit implements Initializer<Counter> {
    Character charToCount;

    public CounterInit(Character charToCount) {
        this.charToCount = charToCount;
    }

    @Override
    public void init(Counter elem) {
         elem.setCharToCount(charToCount);
    }
}
