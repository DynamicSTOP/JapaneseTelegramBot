package com.japanese_bot.quizes;

import java.util.Map;

/**
 * Created by Leonid on 01.05.2016.
 */
public class HiraganaQuiz extends Quiz {
    String hiraganaCharacters =
                    "あいうえお" +
                    "かきくけこ" +
                    "さしすせそ" +
                    "たちつてと" +
                    "なにぬねの" +
                    "はひふへほ" +
                    "まみむめも" +
                    "やゆよらり" +
                    "るれろわを";

    @Override
    public String createQuestion() {
        return null;
    }

    @Override
    public String getCorrectAnswer() {
        return null;
    }

    @Override
    public String[] getWrongAnswers() {
        return new String[0];
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public Boolean checkTask(String userAnswer) {
        return null;
    }

    @Override
    public Map<String, String> getParamsList() {
        return null;
    }

    @Override
    public void setValues(Map<String, String> values) {

    }

}
