package com.japanese_bot.dialogs;

import com.japanese_bot.quizes.Quiz;
import com.japanese_bot.quizes.QuizManager;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

import java.util.List;
import java.util.Map;

/**
 * Created by Leonid on 18.05.2016.
 */
public class HiraganaQuizDialog extends Dialog {
    Quiz quiz;

    protected enum QuizState {GUESSED, ANSWERED_CORRECT, ANSWERED_INCORRECT}

    public HiraganaQuizDialog.QuizState getQuizState() {
        return this.quizState;
    }

    public void setQuizState(HiraganaQuizDialog.QuizState quizState) {
        this.quizState = quizState;
    }

    private HiraganaQuizDialog.QuizState quizState = QuizState.GUESSED;

    public HiraganaQuizDialog(Map<String, String> values) {
        super(values);
    }

    protected boolean syllableMode;

    public boolean isSyllableMode() {
        return syllableMode;
    }

    public void setSyllableMode(boolean syllableMode) {
        this.syllableMode = syllableMode;
    }

    public HiraganaQuizDialog() {
    }

    public HiraganaQuizDialog(boolean syllableMode) {
        this.syllableMode = syllableMode;
    }

    public boolean isAnsweredCorrectly(){
        return quizState.equals(QuizState.ANSWERED_CORRECT);
    }

    public Quiz getQuiz() {
        return this.quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    @Override
    public String getAnswer(String messageText) {
        switch (quizState) {
            case ANSWERED_INCORRECT:
                return "No it's not.";
            case ANSWERED_CORRECT:
                return "You are correct!";
            case GUESSED:
            default:
                return quiz.createQuestion();
        }
    }

    public void processUserQuizAnswer(String userAnswer) {
        if (userAnswer.equals(quiz.getCorrectAnswer())) {
            quizState = QuizState.ANSWERED_CORRECT;
        } else {
            quizState = QuizState.ANSWERED_INCORRECT;
        }

    }

    @Override
    public Map<String, String> getParamsList() {
        Map<String, String> values = super.getParamsList();
        values.put("dialogType", getClass().getCanonicalName());
        values.put("quizKey", quiz.getKey());
        values.put("quizState", String.valueOf(quizState));
        return values;
    }

    @Override
    public void setValues(Map<String, String> values) {
        super.setValues(values);
        quiz = QuizManager.getHiraganaQuizByKey(values.get("quizKey"));
        quizState = QuizState.valueOf(values.get("quizState"));
    }

    @Override
    public Keyboard getKeyboard() {
        List<String> answers = quiz.getAnswers();
        return new ReplyKeyboardMarkup(
                new String[]{answers.get(0), answers.get(1)},
                new String[]{answers.get(2), answers.get(3)},
                new String[]{actionBackToMainMenu}
        ).oneTimeKeyboard(true)
                .selective(true)
                .resizeKeyboard(true);
    }
}
