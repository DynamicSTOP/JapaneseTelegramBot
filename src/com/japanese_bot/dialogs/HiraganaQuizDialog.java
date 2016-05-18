package com.japanese_bot.dialogs;
import com.japanese_bot.quizes.Quiz;
import com.japanese_bot.quizes.QuizManager;

import java.util.Map;

/**
 * Created by Leonid on 18.05.2016.
 */
public class HiraganaQuizDialog extends Dialog {
    Quiz quiz;

    @Override
    public String getAnswer(String messageText) {
        return null;
    }


    @Override
    public Map<String, String> getParamsList() {
        Map<String, String> values = super.getParamsList();
        values.put("dialogType",getClass().getCanonicalName());
        values.put("quizKey",quiz.getKey());
        return values;
    }

    @Override
    public void setValues(Map<String, String> values) {
        super.setValues(values);
        quiz = QuizManager.getHiraganaQuizByKey(values.get("quizKey"));
    }
}
