package com.yet.another.reactive.framework;

import com.yet.another.reactive.framework.pool.CallableDataPool;
import com.yet.another.reactive.framework.pool.Initializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: Adrien
 * Date: 14/08/14
 * Time: 21:51
 * To change this template use File | Settings | File Templates.
 */
//T type return by the callable
// E event inected in the callable

public class NodeFuture <T,E>{
    TreeFuture root;
    Class<CallableData<T,E>> callableClass;
    List<NodeFuture<?,E>> next;
    List<NodeFuture<?,Future<T>>> linkNext;
    boolean waitResultBeforeCallingNext = false;
    CallableDataPool<CallableData<T,E>> pool;

    public NodeFuture( Class<CallableData<T, E>> callableClass,  boolean waitResultBeforeCallingNext, Initializer<CallableData<T,E>> _init) {

        this.callableClass = callableClass;
        this.next = new ArrayList<>();
        this.waitResultBeforeCallingNext = waitResultBeforeCallingNext;
        pool = new CallableDataPool<CallableData<T, E>>(callableClass,100,_init);
    }

    public List<NodeFuture<?, E>> getNext() {
        return next;
    }

    public void setNext(List<NodeFuture<?, E>> next) {
        this.next = next;
    }

    public List<NodeFuture<?, Future<T>>> getLinkNext() {
        return linkNext;
    }

    public void setLinkNext(List<NodeFuture<?, Future<T>>> linkNext) {
        this.linkNext = linkNext;
    }

    public TreeFuture getRoot() {
        return root;
    }

    public void setRoot(TreeFuture root) {
        this.root = root;
    }
    public void execute(E elem){
        CallableData callableData = pool.getCallable();
        callableData.acceptData(elem);
        root.getExecutor().submit(callableData);
        if(next != null && next.size()>0){
            callNextNode(elem);
        }
    }

    public void executeLink(E elem){
        CallableData callableData = pool.getCallable();
        callableData.acceptData(elem);
        Future res = root.getExecutor().submit(callableData);
        if(linkNext != null && linkNext.size()>0){
            callNextNodeLinked(res);
        }
    }

    public void callNextNode(E elem){

        next.forEach((task)-> {
           task.execute(elem);
        });
    }
    public void callNextNodeLinked(Future<T> _future){

        linkNext.forEach((task) -> {
            task.executeLink(_future);
        });
    }


}
