package com.japanese_bot.dialogs;

import com.japanese_bot.Exceptions.EmptyStringException;
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
        Dialog dialog = null;
        try {
            dialog = storage.getDialog(update.message().chat().id());

        } catch (EmptyStringException e) {
            dialog = makeHelloDialog(update);
        } catch (Exception e) {
            System.out.println("EXCEPTION: getting message from storage problem \"" + e.getMessage() + "\"");
        }

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
}
