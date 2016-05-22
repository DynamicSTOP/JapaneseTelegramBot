package com.japanese_bot;

import com.japanese_bot.storages.Storable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Leonid on 30.04.2016.
 */
public class BotStatus extends Storable {
    private int lastUpdateId = -1;

    public BotStatus(){}
    public BotStatus(Map<String,String> values){
        super(values);
    }

    public int getLastUpdateId() {
        return lastUpdateId;
    }

    public void setLastUpdateId(int lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }

    @Override
    public Map<String, String> getParamsList() {
        Map<String,String> values = new HashMap<>();
        values.put("lastUpdateId",String.valueOf(getLastUpdateId()));
        return values;
    }

    @Override
    public void setValues(Map<String, String> values) {
        setLastUpdateId(Integer.valueOf(values.get("lastUpdateId")));
    }
}
