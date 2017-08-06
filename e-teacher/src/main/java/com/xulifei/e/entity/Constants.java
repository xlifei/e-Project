package com.xulifei.e.entity;

/**
 * Created by john on 2017/8/2.
 */
public enum Constants {
    STOP_USING("STOP_USEING"),START_USING("START_USING");
    private final String  state;
    private Constants(String state){
        this.state = state;
    }
    public String getState(){
        return state;
    }
}
