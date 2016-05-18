package com.japanese_bot.quizes;

import com.japanese_bot.storages.Storagable;

import java.util.List;

/**
 * Created by leonid on 29.04.16.
 */
public abstract class Quiz extends Storagable {
    /**
     * @return string that would be sended to user later
     */
    abstract String createQuestion();

    abstract String getCorrectAnswer();

    abstract List<String> getAnswers();

    /**
     * should be a uniq id
     * @return
     */
    abstract int getKey();

    /**
     * checks if userAnswer is equal to answer.
     *
     * @param userAnswer
     * @return true if user answered correctly
     */
    abstract Boolean checkTask(String userAnswer);
}
