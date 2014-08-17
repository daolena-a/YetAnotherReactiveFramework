package com.yet.another.reactive.framework.builder;

import com.yet.another.reactive.framework.CallableData;
import com.yet.another.reactive.framework.NodeFuture;
import com.yet.another.reactive.framework.TreeFuture;
import com.yet.another.reactive.framework.pool.Initializer;

/**
 * Created with IntelliJ IDEA.
 * User: Adrien
 * Date: 15/08/14
 * Time: 21:18
 * To change this template use File | Settings | File Templates.
 */
public class Builder<T,E> {
    TreeFuture<E> tree;
    public Builder createTree(Class<CallableData<T,E>> rootNodeCallable,Initializer<? extends CallableData<T,E>> initializer){
        NodeFuture<T,E> rootNode =  new NodeFuture<T, E>(rootNodeCallable,null, false,new Initializer<CallableData<T, E>>() {
            @Override
            public void init(CallableData<T, E> elem) {

            }
        });
        TreeFuture<E> res = new TreeFuture<E>(rootNode);
        tree = res;
        return this;
    }


    public NodeBuilder<T,E> addNode(){
        return new NodeBuilder<>();
    }
}
