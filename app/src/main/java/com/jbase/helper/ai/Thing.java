package com.jbase.helper.ai;

import android.support.annotation.NonNull;

/**
 * Created by aaa on 2017/12/12.
 */

public class Thing implements Comparable<Thing>{
    /**
     * 记忆
     */
    private String memory;
    /**
     * 影响
     */
    private double effect;
    /**
     * 潜在发生的行为
     */
    private Action action;

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public double getEffect() {
        return effect;
    }

    public void setEffect(double effect) {
        this.effect = effect;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public int compareTo(@NonNull Thing o) {
        return o.effect>this.effect?1:-1;
    }

    @Override
    public String toString() {
        return "Thing{" +
                "memory='" + memory + '\'' +
                ", effect=" + effect +
                ", action=" + action +
                '}';
    }
}
