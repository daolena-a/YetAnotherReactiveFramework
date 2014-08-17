package com.yet.another.reactive.framework.builder;

import com.yet.another.reactive.framework.CallableData;
import com.yet.another.reactive.framework.NodeFuture;
import com.yet.another.reactive.framework.pool.Initializer;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Adrien
 * Date: 15/08/14
 * Time: 21:22
 * To change this template use File | Settings | File Templates.
 */
public class NodeBuilder<T,E> {

    public NodeBuilder(Class<CallableData<T,E>> clazz, Initializer<? extends CallableData<T,E>> initializer){
        NodeFuture nf = new NodeFuture(clazz, new ArrayList(),false,initializer);
    }

    public NodeBuilder<?,?> addChildNode(Class<CallableData<?,?>> clazz, Initializer<? extends CallableData<T,E>> initializer){

        NodeBuilder<?,?> node =  new NodeBuilder(clazz,initializer);
        return node;

    }

    public NodeBuilder<T,E> addChildNodeLink(Class<CallableData<?,?>> clazz, Initializer<? extends CallableData<T,E>> initializer){
        return this;
    }


}
