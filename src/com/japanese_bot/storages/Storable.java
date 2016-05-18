package com.japanese_bot.storages;

import java.util.Map;

/**
 * Created by leonid on 06.05.16.
 */
public abstract class Storable {
    public Storable(){}
    public Storable(Map<String,String> values){
        setValues(values);
    }
    public abstract Map<String,String> getParamsList();
    public abstract void setValues(Map<String,String> values);
}
