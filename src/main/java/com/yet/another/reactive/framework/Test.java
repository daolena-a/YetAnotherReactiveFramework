package com.yet.another.reactive.framework;

import com.yet.another.reactive.framework.builder.Builder;
import com.yet.another.reactive.framework.pool.Initializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: adao-lena
 * Date: 26/08/14
 * Time: 14:04
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args){
            Builder builder = new Builder();
        builder.createTree(Counter.class, new CounterInit('a'));
        builder.addNode(Counter.class,new CounterInit('b')).addChildNode(Counter.class,new CounterInit('c')).addChildNodeLink(DisplayCallable.class, null);
        TreeFuture tree = builder.build();
        try {
            Scanner sc = new Scanner(new File(args[0]));
            while (sc.hasNextLine()){
                String data = sc.nextLine();
                 tree.submit(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}

