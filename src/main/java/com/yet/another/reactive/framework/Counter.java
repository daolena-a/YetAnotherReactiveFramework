package com.yet.another.reactive.framework;

/**
 * Created with IntelliJ IDEA.
 * User: adao-lena
 * Date: 26/08/14
 * Time: 14:25
 * To change this template use File | Settings | File Templates.
 */
public class Counter extends CallableData<Integer,String> {
    Character charToCount;
     String data;

    public Counter() {

    }

    @Override
    public void acceptData(String elem) {
        data = elem;
    }

    @Override
    public void reset() {
        data = null;
    }

    Character getCharToCount() {
        return charToCount;
    }

    void setCharToCount(Character charToCount) {
        this.charToCount = charToCount;
    }

    @Override
    public Integer call() throws Exception {
        int i =0;
        for(Character c : data.toCharArray()){
               if (charToCount.equals(c)){
                   i++;
               }
        }
        System.out.println("char "+charToCount+ " "+i);
        return i;
    }
}
