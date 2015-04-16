package com.yet.another.reactive.framework.builder;

import com.yet.another.reactive.framework.CallableData;
import com.yet.another.reactive.framework.NodeFuture;
import com.yet.another.reactive.framework.TreeFuture;
import com.yet.another.reactive.framework.pool.Initializer;

/**
 * User: adao-lena
 * Date: 15/08/14
 * Time: 21:18
 *
 */
public class Builder<T,E> {
    TreeFuture<E> tree;
    public Builder createTree(Class<CallableData<T,E>> rootNodeCallable,Initializer<CallableData<T,E>> initializer){
        NodeFuture<T,E> rootNode =  new NodeFuture<T, E>(rootNodeCallable, false,initializer);
        TreeFuture<E> res = new TreeFuture<E>(rootNode);
        tree = res;
        return this;
    }


    public NodeBuilder<T,E> addNode(Class<CallableData<T,E>> clazz, Initializer<? extends CallableData<T,E>> initializer){
        NodeBuilder<T,E> nb =  new NodeBuilder<>( clazz, initializer,tree);
        tree.getRoot().getNext().add(nb.getLocal());
         return nb;
    }

    public TreeFuture<E> build(){
        return tree;
    }
}
