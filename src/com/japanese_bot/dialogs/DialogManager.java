package com.japanese_bot.dialogs;

import com.japanese_bot.Exceptions.EmptyStringException;
import com.japanese_bot.quizes.QuizManager;
import com.japanese_bot.storages.IStorage;
import com.pengrad.telegrambot.model.Update;

/**
 * Created by Leonid on 01.05.2016.
 */
public class DialogManager {

    private IStorage storage;

    public DialogManager(IStorage storage){
        this.storage=storage;
    }

    public Dialog processDialogUpdate(Update update){
        System.out.println(update.message().text());
        Dialog dialog = null;
        try {
            dialog = storage.getDialog(update.message().chat().id());

            if(dialog.getClass().equals(StartDialog.class)){
                if(update.message().text().equals(Dialog.actionQuizHiragana)){
                    dialog = createHiraganaQuizDialog();
                }
            } else if(dialog.getClass().equals(HiraganaQuizDialog.class)){
                HiraganaQuizDialog hiraganaQuizDialog = (HiraganaQuizDialog) dialog;
                if(update.message().text().equals(dialog.actionBackToMainMenu)) {
                    dialog = makeHelloDialog(update);
                } else {
                    hiraganaQuizDialog.processUserQuizAnswer(update.message().text());
                    if(hiraganaQuizDialog.isAnsweredCorrectly())
                        dialog = createHiraganaQuizDialog();
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

    private Dialog makeHelloDialog(Update update){
        if (!update.message().text().equals("/start")) {
            System.out.println("ERROR: expected \"/start\", got \"" + update.message().text() + "\" from user " + update.message());
        }
        Dialog helloDialog = new StartDialog();
        helloDialog.setChatId(update.message().chat().id());
        return helloDialog;
    }

    public Dialog createHiraganaQuizDialog() {
        HiraganaQuizDialog dialog = new HiraganaQuizDialog();
        dialog.setQuiz(QuizManager.getRandomHiraganaQuiz());
        return dialog;
    }
}
