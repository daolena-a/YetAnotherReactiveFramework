package com.yet.another.reactive.framework;

import com.yet.another.reactive.framework.builder.Builder;
import com.yet.another.reactive.framework.pool.Initializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 *
 * User: adao-lena
 * Date: 26/08/14
 * Time: 14:04
 *
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(args[0]);
        Builder builder = new Builder();
        builder.createTree(Counter.class, new CounterInit('a'));
        builder.addNode(Counter.class, new CounterInit('b')).addChildNode(Counter.class, new CounterInit('c')).addChildNodeLink(DisplayCallable.class, null);
        TreeFuture tree = builder.build();
        String data = null;
        try {

            Stream<String> lines = Files.lines(new File(args[0]).toPath());
            lines.forEach((line) -> {
                        tree.submit(line);
                    }
            );


        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("END" + data);

    }


}

