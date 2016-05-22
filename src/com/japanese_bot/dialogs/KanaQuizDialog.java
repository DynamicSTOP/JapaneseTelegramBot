package com.japanese_bot.dialogs;

import com.japanese_bot.quizes.KanaQuiz;
import com.japanese_bot.quizes.Quiz;
import com.japanese_bot.quizes.QuizManager;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

import java.util.List;
import java.util.Map;

/**
 * Created by Leonid on 18.05.2016.
 */
public class KanaQuizDialog extends Dialog {
    KanaQuiz quiz;

    protected enum QuizState {GUESSED, ANSWERED_CORRECT, ANSWERED_INCORRECT}

    public KanaQuizDialog.QuizState getQuizState() {
        return this.quizState;
    }

    public void setQuizState(KanaQuizDialog.QuizState quizState) {
        this.quizState = quizState;
    }

    private KanaQuizDialog.QuizState quizState = QuizState.GUESSED;

    public KanaQuizDialog(Map<String, String> values) {
        super(values);
    }

    protected boolean syllableMode;

    public boolean isSyllableMode() {
        return syllableMode;
    }

    public void setSyllableMode(boolean syllableMode) {
        this.syllableMode = syllableMode;
    }

    public KanaQuizDialog() {
    }

    public KanaQuizDialog(boolean syllableMode) {
        this.syllableMode = syllableMode;
    }

    public boolean isAnsweredCorrectly(){
        return quizState.equals(QuizState.ANSWERED_CORRECT);
    }

    public Quiz getQuiz() {
        return this.quiz;
    }

    public void setQuiz(KanaQuiz quiz) {
        this.quiz = quiz;
    }

    @Override
    public String getAnswer(String messageText) {
        switch (quizState) {
            case ANSWERED_INCORRECT:
                return "Nope, try again";
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
        values.put("quizABType", String.valueOf(quiz.getAlhpabetType()));
        values.put("quizState", String.valueOf(quizState));
        values.put("syllableMode", String.valueOf(syllableMode));
        return values;
    }

    @Override
    public void setValues(Map<String, String> values) {
        super.setValues(values);
        if( KanaQuiz.isHiraganaQuiz(values.get("quizABType"))){
            quiz = (KanaQuiz) QuizManager.getHiraganaQuizByKey(values.get("quizKey"));
        } else {
            quiz = (KanaQuiz) QuizManager.getKatakanaQuizByKey(values.get("quizKey"));
        }
        quizState = QuizState.valueOf(values.get("quizState"));
        syllableMode = Boolean.valueOf(values.get("syllableMode"));
        quiz.setSyllableMode(syllableMode);
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

    public boolean isHiraganaDialog(){
        return quiz.isHiraganaQuiz();
    }
}
