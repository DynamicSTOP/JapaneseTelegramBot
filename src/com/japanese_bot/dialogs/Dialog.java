package com.japanese_bot.dialogs;

import com.japanese_bot.storages.Storable;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leonid on 29.04.16.
 */
public abstract class Dialog extends Storable {

    public static final String actionQuizHiragana = "Quiz Hiragana";
    public static final String actionQuizHiraganaSyllabels = "Quiz Hiragana Syllables";
    public static final String actionShowHiraganaMenu = "Hiragana";
    public static final String actionShowHiragana = "Show Hiragana";

    public static final String actionQuizKatakana = "Quiz Katakana";
    public static final String actionQuizKatakanaSyllabels = "Quiz Katakana Syllables";
    public static final String actionShowKatakanaMenu = "Katakana";
    public static final String actionShowKatakana = "Show Katakana";

    public static final String actionShowDifficultyMenu = "Change difficulty";
    public static final String actionBackToMainMenu = "Back to main menu";

    public Dialog() {
    }

    public Dialog(Map<String, String> values) {
        super(values);
    }

    private Long chatId;

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    protected boolean keyboardAvailable;

    protected enum KeyboardType {STANDARD,HIRAGANA_MENU,KATAKANA_MENU,DIFFICULTY_MENU}

    protected KeyboardType keyboardType=KeyboardType.STANDARD;

    public KeyboardType getKeyboardType() {
        return keyboardType;
    }

    public void setKeyboardAvailable(boolean keyboardAvailable) {
        this.keyboardAvailable = keyboardAvailable;
    }

    public void setKeyboardType(KeyboardType keyboardType) {
        this.keyboardType = keyboardType;
    }

    public boolean getKeyboardAvailable() {
        return keyboardAvailable;
    }

    public ParseMode getParseMode() {
        return ParseMode.HTML;
    }

    public Boolean getDisableWebPagePreview() {
        return false;
    }

    public Integer getReplyToMessageId() {
        return null;
    }

    public Keyboard getKeyboard() {
        switch (keyboardType) {
            case STANDARD:
                return new ReplyKeyboardMarkup(
                        new String[]{actionShowHiraganaMenu},
                        /*new String[]{actionShowKatakanaMenu},*/
                        new String[]{actionShowDifficultyMenu}
                )
                        .oneTimeKeyboard(true)
                        .selective(true)
                        .resizeKeyboard(true);
            default:
                return null;
        }
    }

    /**
     * @return
     */
    public abstract String getAnswer(String messageText);

    @Override
    public Map<String, String> getParamsList() {
        Map<String,String> values = new HashMap<>();
        values.put("dialogType",getClass().getCanonicalName());
        values.put("chatId",String.valueOf(getChatId()));
        values.put("keyboardAvailable",String.valueOf(getKeyboardAvailable()));
        values.put("keyboardType",String.valueOf(getKeyboardType()));
        return values;
    }

    @Override
    public void setValues(Map<String, String> values) {
        setChatId(Long.valueOf(values.get("chatId")));
        setKeyboardAvailable(Boolean.valueOf(values.get("keyboardAvailable")));
        setKeyboardType(KeyboardType.valueOf(values.get("keyboardType")));
    }
}
