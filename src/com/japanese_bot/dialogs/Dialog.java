package com.japanese_bot.dialogs;

import com.japanese_bot.storages.Storable;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ParseMode;

import java.util.Map;

/**
 * Created by leonid on 29.04.16.
 */
public abstract class Dialog extends Storable {
    public Dialog(){}
    public Dialog(Map<String,String> values){super(values);}

    public abstract Long getChatId();
    public abstract void setChatId(Long chatId);

    public abstract boolean getKeyboardAvailable();

    /**
     *
     * @return
     */
    public abstract String getAnswer(String messageText);

    /**
     * ParseMode.Markdown prefered
     * @return
     */
    public abstract ParseMode getParseMode();

    /**
     * should be false if there is no links inside
     * @return
     */
    public abstract Boolean getDisableWebPagePreview();

    /**
     * can leave it to null
     * @return
     */
    public abstract Integer getReplyToMessageId();

    /**
     * can be null
     * @return
     */
    public abstract Keyboard getKeyboard();
}
