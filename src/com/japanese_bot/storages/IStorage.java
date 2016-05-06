package com.japanese_bot.storages;

import com.japanese_bot.BotStatus;
import com.japanese_bot.Exceptions.EmptyStringException;
import com.japanese_bot.dialogs.Dialog;
import com.japanese_bot.quizes.*;

/**
 * Created by leonid on 30.04.16.
 */
public interface IStorage {
    /**
     * @param chatId
     * @return
     * @throws Exception
     */
    Dialog getDialog(Long chatId) throws Exception;

    void setDialog(Dialog dialog);

    String get(String key) throws Exception;

    void set(String key,String data) throws Exception;

    BotStatus getBotStatus() throws EmptyStringException;

    void setBotStatus(BotStatus status);

    /**
     * loads data from file to memory cache or establish connection to database
     *
     * @return
     */
    void start() throws Exception;

    /**
     * saves data from cache to file or closes connection to database
     *
     * @return
     */
    void shutDown() throws Exception;


    public Quiz getQuiz(String type, Integer id) throws EmptyStringException;

    public void setQuiz(String type, Quiz quiz);
}
