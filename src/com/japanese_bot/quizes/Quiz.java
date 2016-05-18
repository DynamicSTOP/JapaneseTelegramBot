package com.japanese_bot.quizes;

import com.japanese_bot.storages.Storable;

import java.util.List;
import java.util.Map;

/**
 * Created by leonid on 29.04.16.
 */
public abstract class Quiz extends Storable {
    protected Quiz() {}
    public Quiz(Map<String,String> values){super(values);}

    /**
     * @return string that would be sended to user later
     */
    public abstract String createQuestion();

    public abstract String getCorrectAnswer();

    public abstract List<String> getAnswers();

    /**
     * should be a uniq key for db
     * @return
     */
    public abstract String getKey();

    /**
     * checks if userAnswer is equal to answer.
     *
     * @param userAnswer
     * @return true if user answered correctly
     */
    public abstract Boolean checkTask(String userAnswer);
}
