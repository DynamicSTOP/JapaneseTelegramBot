package com.japanese_bot.dialogs;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leonid on 30.04.16.
 */
public class StartDialog extends Dialog {
    private Long chatId;


    private boolean keyboardAvailable = false;
    private enum KeyboardType{STANDARD}

    private KeyboardType keyboardType;

    public KeyboardType getKeyboardType() {
        return keyboardType;
    }

    public void setKeyboardAvailable(boolean keyboardAvailable) {
        this.keyboardAvailable = keyboardAvailable;
    }

    public void setKeyboardType(KeyboardType keyboardType) {
        this.keyboardType = keyboardType;
    }

    public StartDialog(Map<String,String> values){
        super(values);
    }

    public StartDialog() {
        keyboardType = KeyboardType.STANDARD;
    }

    @Override
    public Long getChatId() {
        return chatId;
    }

    @Override
    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    @Override
    public boolean getKeyboardAvailable() {
        return keyboardAvailable;
    }

    private String getDefaultAnswer(){
        return "Hello! Hello!\n Let's begin our learning!";
    }

    @Override
    public String getAnswer(String messageText) {
        switch (messageText){
            case "Quiz Hiragana":
                return getDefaultAnswer();
            case "Quiz Katakana":
                return getDefaultAnswer();
            case "Show Hiragana":
                return getHiraganaAlphabet();
            case "Show Katakana":
                return getDefaultAnswer();
            case "Change difficulty":
                return getDefaultAnswer();
            default:
                return getDefaultAnswer();
        }
        
    }

    @Override
    public ParseMode getParseMode() {
        return ParseMode.HTML;
    }

    @Override
    public Boolean getDisableWebPagePreview() {
        return false;
    }

    @Override
    public Integer getReplyToMessageId() {
        return null;
    }

    @Override
    public Keyboard getKeyboard() {
        switch (keyboardType){
            case STANDARD:
                return new ReplyKeyboardMarkup(
                        new String[]{"Show Hiragana"/*,"Show Katakana"*/},
                        new String[]{"Quiz Hiragana"/*,"Quiz Katakana"*/},
                        new String[]{"Change difficulty"}
                )
                        .oneTimeKeyboard(true)
                        .selective(true)
                        .resizeKeyboard(true);
            default:
                return null;
        }
    }

    public String getHiraganaAlphabet() {
        return "Here is hiragana:\n" +
                "<code>\n" +
                "  \t\ta\t\ti\t\tu\t\te\t\t o\n" +
                " \t\tあ\t\tい\t\tう\t\tえ\t\tお\n" +
                "k\t\tか\t\tき\t\tく\t\tけ\t\tこ\n" +
                "s\t\tさ\t\tし\t\tす\t\tせ\t\tそ\n" +
                "t\t\tた\t\tち\t\tつ\t\tて\t\tと\n" +
                "n\t\tな\t\tに\t\tぬ\t\tね\tの\n" +
                "h\t\tは\t\tひ\t\tふ\t\tへ\tほ\n" +
                "m\t\tま\t\tみ\t\tむ\t\tめ\tも\n" +
                "y\t\tや\t\t \t\tゆ\t\t \t\tよ\n" +
                "r\t\tら\t\tり\t\tる\t\tれ\t\tろ\n" +
                "w\t\tわ\t\t \t\t \t\t  \t\tを\n" +
                "</code>";
    }


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
