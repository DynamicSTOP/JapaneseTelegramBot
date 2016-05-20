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

    public StartDialog(Map<String,String> values){
        super(values);
    }

    public StartDialog() {
        setKeyboardType(KeyboardType.STANDARD);
    }

    private String getDefaultAnswer(){
        return "Hello! Hello!\n Let's begin our learning!";
    }

    @Override
    public String getAnswer(String messageText) {
        switch (messageText){
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
        Map<String, String> values = super.getParamsList();
        values.put("dialogType",getClass().getCanonicalName());
        return values;
    }

    @Override
    public void setValues(Map<String, String> values) {
        super.setValues(values);
    }
}
