package com.jbase.helper.ai;

import java.util.TreeSet;

/**
 * Created by aaa on 2017/12/12.
 */

public class Study {

    private TreeSet<Thing> things = new TreeSet<>();

    private Study() {
    }

    private static class Studyer{
        public static Study study = new Study();
    }

    public static Study getInstance(){
        return Studyer.study;
    }

    public void addKnowledge(Thing thing){
        things.add(thing);
    }

    public TreeSet<Thing> getThings(){
        return things;
    }

}
