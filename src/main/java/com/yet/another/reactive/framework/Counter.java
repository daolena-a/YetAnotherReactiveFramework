package com.yet.another.reactive.framework;

/**
 *
 * User: adao-lena
 * Date: 26/08/14
 * Time: 14:25
 *
 */
public class Counter extends CallableData<Integer,String> {
     Character charToCount;
     String data;

    public Counter() {

    }

    @Override
    public void getData(String elem) {

        data = elem;
    }

    @Override
    public void resetData() {
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
        System.out.println(Thread.currentThread().getName()+" char "+charToCount+ " "+i);
        return i;
    }
}
