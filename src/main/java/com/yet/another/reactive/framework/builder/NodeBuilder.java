package com.yet.another.reactive.framework.builder;

import com.yet.another.reactive.framework.CallableData;
import com.yet.another.reactive.framework.NodeFuture;
import com.yet.another.reactive.framework.TreeFuture;
import com.yet.another.reactive.framework.pool.Initializer;

import java.util.ArrayList;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: Adrien
 * Date: 15/08/14
 * Time: 21:22
 * To change this template use File | Settings | File Templates.
 */
public class NodeBuilder<T,E> {
    NodeFuture local;
    TreeFuture<E> root;
    public NodeBuilder(Class<CallableData<T,E>> clazz, Initializer<? extends CallableData<T,E>> initializer, TreeFuture<E> root){
        this.root = root;
        NodeFuture nf = new NodeFuture(clazz, false,initializer);
        nf.setRoot(root);
        local = nf;
    }

    public NodeBuilder<?,?> addChildNode(Class<CallableData<?,?>> clazz, Initializer<? extends CallableData<T,E>> initializer){

        NodeBuilder<?,?> node =  new NodeBuilder(clazz,initializer,root);
        local.getNext().add(node.getLocal());
        return node;

    }

    public NodeBuilder<?,?> addChildNodeLink(Class<CallableData<?,Future<T>>> clazz, Initializer<? extends CallableData<T,E>> initializer){
        NodeBuilder<?,?> node =  new NodeBuilder(clazz,initializer,root);
        local.getLinkNext().add(node.getLocal());
        return node;
    }

    public NodeFuture getLocal() {
        return local;
    }

    public void setLocal(NodeFuture local) {
        this.local = local;
    }
}
