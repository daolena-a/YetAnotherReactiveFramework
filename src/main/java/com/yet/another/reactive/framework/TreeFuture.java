package com.yet.another.reactive.framework;

import java.util.concurrent.*;

/**
 *
 * User: adao-lena
 * Date: 14/08/14
 * Time: 21:51
 *
 */
public class TreeFuture<E> {
    private ExecutorService executor;
    private NodeFuture<?,E> root;
    public TreeFuture(NodeFuture<?,E> rootNode){
        executor = Executors.newFixedThreadPool(100);
        root = rootNode;
        root.setRoot(this);
    }
    public TreeFuture(NodeFuture<?,E> rootNode,int poolSize){
        this(rootNode);
        executor = Executors.newFixedThreadPool(poolSize);

    }

    public void submit(E newData){
        root.execute(newData);
    }
    public ExecutorService getExecutor(){
        return executor;
    }

    public NodeFuture<?, E> getRoot() {
        return root;
    }

    public void setRoot(NodeFuture<?, E> root) {
        this.root = root;
    }


}
