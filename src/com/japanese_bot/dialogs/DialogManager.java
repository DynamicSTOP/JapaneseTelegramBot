package com.japanese_bot.dialogs;

import com.japanese_bot.Exceptions.EmptyStringException;
import com.japanese_bot.quizes.KanaQuiz;
import com.japanese_bot.quizes.Quiz;
import com.japanese_bot.quizes.QuizManager;
import com.japanese_bot.storages.IStorage;
import com.pengrad.telegrambot.model.Update;

/**
 * Created by Leonid on 01.05.2016.
 */
public class DialogManager {

    private IStorage storage;
    private Dialog dialog;

    private String answer;

    public DialogManager(IStorage storage) {
        this.storage = storage;
    }

    public String getAnswer(String messageText) {
        if (dialog.getClass().equals(StartDialog.class))
            return dialog.getAnswer(messageText);
        else if(dialog.getClass().equals(KanaQuizDialog.class)){
            return answer +" "+ dialog.getAnswer(messageText);
        }

        return null;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public Dialog processDialogUpdate(Update update) {
        System.out.println(update.message().from().username() + ":" + update.message().text());
        setAnswer("");
        try {
            dialog = storage.getDialog(update.message().chat().id());

            if (dialog.getClass().equals(StartDialog.class)) {
                if (update.message().text().equals(Dialog.actionQuizHiragana)) {
                    dialog = createHiraganaQuizDialog(false);
                } else if(update.message().text().equals(Dialog.actionQuizHiraganaSyllabels)){
                    dialog = createHiraganaQuizDialog(true);
                } else if(update.message().text().equals(Dialog.actionQuizKatakana)){
                    dialog = createKatakanaQuizDialog(false);
                } else if(update.message().text().equals(Dialog.actionQuizKatakanaSyllabels)){
                    dialog = createKatakanaQuizDialog(true);
                }
            } else if (dialog.getClass().equals(KanaQuizDialog.class)) {
                KanaQuizDialog kanaQuizDialog = (KanaQuizDialog) dialog;
                if (update.message().text().equals(dialog.actionBackToMainMenu)) {
                    dialog = makeHelloDialog(update);
                } else {
                    kanaQuizDialog.processUserQuizAnswer(update.message().text());
                    if (kanaQuizDialog.isAnsweredCorrectly()){
                        setAnswer(dialog.getAnswer(update.message().text()));
                        if(kanaQuizDialog.isHiraganaDialog())
                            dialog = createHiraganaQuizDialog(kanaQuizDialog.isSyllableMode());
                        else
                            dialog = createKatakanaQuizDialog(kanaQuizDialog.isSyllableMode());
                    }
                }
            }

        } catch (EmptyStringException e) {
            dialog = makeHelloDialog(update);
        } catch (Exception e) {
            System.out.println("EXCEPTION: getting dialog from storage problem \"" + e.getMessage() + "\"");
        }
        dialog.setChatId(update.message().chat().id());
        return dialog;
    }

    private Dialog makeHelloDialog(Update update) {
        Dialog helloDialog = new StartDialog();
        helloDialog.setChatId(update.message().chat().id());
        return helloDialog;
    }

    public Dialog createHiraganaQuizDialog(boolean syllableMode) {
        KanaQuizDialog dialog = new KanaQuizDialog(syllableMode);
        KanaQuiz quiz = QuizManager.getRandomHiraganaQuiz();
        quiz.setSyllableMode(syllableMode);
        dialog.setQuiz(quiz);
        return dialog;
    }

    public Dialog createKatakanaQuizDialog(boolean syllableMode) {
        KanaQuizDialog dialog = new KanaQuizDialog(syllableMode);
        KanaQuiz quiz = QuizManager.getRandomKatakanaQuiz();
        quiz.setSyllableMode(syllableMode);
        dialog.setQuiz(quiz);
        return dialog;
    }
}
