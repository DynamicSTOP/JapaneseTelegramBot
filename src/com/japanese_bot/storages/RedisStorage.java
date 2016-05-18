package com.japanese_bot.storages;

import com.japanese_bot.BotStatus;
import com.japanese_bot.Exceptions.EmptyStringException;
import com.japanese_bot.dialogs.Dialog;
import com.japanese_bot.quizes.*;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by Leonid on 01.05.2016.
 */
public class RedisStorage implements IStorage {
    Jedis jedis;
    private Integer redisDbId=2;

    @Override
    public Dialog getDialog(Long chatId) throws EmptyStringException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Map<String,String> objectData = getObjectData("DIALOGS:" + chatId.toString());
        Class<?> dialogClass = Class.forName(objectData.get("dialogType"));
        Constructor<?> dialogClassConstructor = dialogClass.getConstructor(Map.class);
        return (Dialog) dialogClassConstructor.newInstance(objectData);
    }

    @Override
    public void setDialog(Dialog dialog) {
        setObject("DIALOGS:" + dialog.getChatId().toString(), dialog);
    }

    @Override
    public String get(String key) throws Exception {
        return jedis.get(key);
    }

    @Override
    public void set(String key, String data) {
        jedis.set(key, data);
    }

    @Override
    public BotStatus getBotStatus() throws EmptyStringException {
        return new BotStatus(getObjectData("BOTSTATUS"));
    }

    @Override
    public void setBotStatus(BotStatus status) {
        setObject("BOTSTATUS", status);
    }

    @Override
    public void start() throws Exception {
        jedis = new Jedis("localhost");
        if (jedis.isConnected())
            throw new Exception("Cannot connect to Redis server!");
        jedis.select(redisDbId);
    }

    @Override
    public void shutDown() {
        jedis.close();
    }

    private void setObject(String key, Storable o) {
        jedis.del(key);
        jedis.hmset(key,o.getParamsList());
    }

    private Map<String,String> getObjectData(String key) throws EmptyStringException {
        Map<String,String> fields = jedis.hgetAll(key);
        if(fields.size()==0)
            throw new EmptyStringException();

        return fields;
    }


    @Override
    public Quiz getQuiz(String key) throws EmptyStringException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Map<String,String> objectData = getObjectData(key);
        Class<?> quizClass = Class.forName(objectData.get("quizType"));
        Constructor<?> quizClassConstructor = quizClass.getConstructor(Map.class);
        return (Quiz) quizClassConstructor.newInstance(objectData);
    }

    @Override
    public void setQuiz(Quiz quiz) {
        setObject(quiz.getKey(), quiz);
    }
}
