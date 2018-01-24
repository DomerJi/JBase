package com.jbase.helper.ai;

/**
 * Created by aaa on 2017/12/12.
 */

public class Action implements IAction{

    private long executeTime;
    private String plan;
    private Type type = Type.NORMAL;

    public long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(long executeTime) {
        this.executeTime = executeTime;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean execute() {
        return false;
    }

    public enum Type {
        MOVE(0),SEE(1),LISTEN(2),STUDY(3),TALK(4),NORMAL(5);
        int value;
        private Type(int _value){
            this.value = _value;
        }
    }

    @Override
    public String toString() {
        return "Action{" +
                "executeTime=" + executeTime +
                ", plan='" + plan + '\'' +
                ", type=" + type +
                '}';
    }
}
