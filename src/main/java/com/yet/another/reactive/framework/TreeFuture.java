package com.yet.another.reactive.framework;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: Adrien
 * Date: 14/08/14
 * Time: 21:51
 * To change this template use File | Settings | File Templates.
 */
public class TreeFuture<E> {
    private ExecutorService executor;
    private NodeFuture<?,E> root;
    public TreeFuture(NodeFuture<?,E> rootNode){
        executor = Executors.newFixedThreadPool(100);
        root = rootNode;
    }
    public TreeFuture(int poolSize,NodeFuture<?,E> rootNode){
        this(rootNode);
        executor = Executors.newFixedThreadPool(poolSize);

    }

    public void submit(E newData){
        root.execute(newData);
    }
    public ExecutorService getExecutor(){
        return executor;
    }




}
