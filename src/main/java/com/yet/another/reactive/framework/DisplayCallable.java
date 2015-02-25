package com.yet.another.reactive.framework;

import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: adao-lena
 * Date: 26/08/14
 * Time: 14:48
 * To change this template use File | Settings | File Templates.
 */
public class DisplayCallable extends CallableData<Void,Future<Integer>> {
    Future<Integer> display;
    @Override
    public void acceptData(Future<Integer> elem) {
      display = elem;
    }

    @Override
    public void reset() {
       display = null;
    }

    @Override
    public Void call() throws Exception {
        System.out.println("link using the previous node value"+display.get());
        return null;
    }
}
