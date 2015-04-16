package com.yet.another.reactive.framework;

import java.util.concurrent.Future;

/**
 *
 * User: adao-lena
 * Date: 26/08/14
 * Time: 14:48
 *
 */
public class DisplayCallable extends CallableData<Void,Future<Integer>> {
    Future<Integer> display;
    @Override
    public void getData(Future<Integer> elem) {
      display = elem;
    }

    @Override
    public void resetData() {
       display = null;
    }

    @Override
    public Void call() throws Exception {
        System.out.println("link using the previous node value"+display.get());
        return null;
    }
}
