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
        return "What do you want to do?";
    }

    @Override
    public String getAnswer(String messageText) {
        switch (messageText){
            case Dialog.actionShowHiragana:
                setKeyboardType(KeyboardType.HIRAGANA_MENU);
                return getHiraganaAlphabet();
            case Dialog.actionShowHiraganaMenu:
                setKeyboardType(KeyboardType.HIRAGANA_MENU);
                return "What shall we do?";
            case Dialog.actionShowKatakana:
                setKeyboardType(KeyboardType.KATAKANA_MENU);
                return getKatakanaAlphabet();
            case Dialog.actionShowKatakanaMenu:
                setKeyboardType(KeyboardType.KATAKANA_MENU);
                return "What shall we do?";
            case Dialog.actionShowDifficultyMenu:
                setKeyboardType(KeyboardType.DIFFICULTY_MENU);
                return "still implementing. Taidana purogurama ^^";
            default:
                setKeyboardType(KeyboardType.STANDARD);
                return getDefaultAnswer();
        }
        
    }

    public Keyboard getKeyboard() {
        switch (keyboardType) {
            case STANDARD:
                return new ReplyKeyboardMarkup(
                        new String[]{actionShowHiraganaMenu},
                        new String[]{actionShowKatakanaMenu}/*,
                        new String[]{actionShowDifficultyMenu}*/
                )
                        .oneTimeKeyboard(true)
                        .selective(true)
                        .resizeKeyboard(true);
            case HIRAGANA_MENU:
                return new ReplyKeyboardMarkup(
                        new String[]{actionShowHiragana},
                        new String[]{actionQuizHiragana},
                        new String[]{actionQuizHiraganaSyllabels},
                        new String[]{actionBackToMainMenu}
                )
                        .oneTimeKeyboard(true)
                        .selective(true)
                        .resizeKeyboard(true);

            case KATAKANA_MENU:
                return new ReplyKeyboardMarkup(
                        new String[]{actionShowKatakana},
                        new String[]{actionQuizKatakana},
                        new String[]{actionQuizKatakanaSyllabels},
                        new String[]{actionBackToMainMenu}
                )
                        .oneTimeKeyboard(true)
                        .selective(true)
                        .resizeKeyboard(true);
            default:
                return null;
        }
    }


    public String getHiraganaAlphabet() {
        return "Here is hiragana that you will see in quizzes:\n" +
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
                "</code>\n" +
                "This is just an example of characters that you might see in chat.\n" +
                "If you are serious about learning you'd better check this one\n" +
                "https://upload.wikimedia.org/wikipedia/commons/thumb/2/28/Table_hiragana.svg/1280px-Table_hiragana.svg.png\n"+
                "Please note that 'wi' and 'we' are outdated and you even might never see them in modern Japanese.";
    }

    public String getKatakanaAlphabet() {
        return "Here is katakana that you will see in quizzes:\n" +
                "<code>\n" +
                "  \t\ta\t\ti\t\tu\t\te\t\t o\n" +
                " \t\tア\t\tイ\t\tウ\t\tエ\t\tオ\n" +
                "k\t\tカ\t\tキ\t\tク\t\tケ\t\tコ\n" +
                "s\t\tサ\t\tシ\t\tス\t\tセ\t\tソ\n" +
                "t\t\tタ\t\tチ\t\tツ\t\tテ\t\tト\n" +
                "n\t\tナ\t\tニ\t\tヌ\t\tネ\tノ\n" +
                "h\t\tハ\t\tヒ\t\tフ\t\tヘ\tホ\n" +
                "m\t\tマ\t\tミ\t\tム\t\tメ\tモ\n" +
                "y\t\tヤ\t\t \t\tユ\t\t \t\tヨ\n" +
                "r\t\tラ\t\tリ\t\tル\t\tレ\t\tロ\n" +
                "w\t\tワ\t\t \t\t \t\t  \t\tヲ\n" +
                "</code>\n" +
                "This is just an example of characters that you might see in chat.\n" +
                "If you are serious about learning you'd better check this one\n" +
                "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0d/Table_katakana.svg/1280px-Table_katakana.svg.png\n"+
                "Please note that 'wi' and 'we' are outdated and you even might never see them in modern Japanese.";
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
