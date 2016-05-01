import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

/**
 * Created by leonid on 30.04.16.
 */
public class StartDialog implements IDialog {
    private Long chatId;
    boolean hasKeyboard = false;

    public StartDialog(Long chatId) {
        setChatId(chatId);
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
    public boolean hasKeyboard() {
        return hasKeyboard;
    }

    private String getDefaltAnswer(){
        return "Hello! Hello!\n Let's begin our learning!";
    }

    @Override
    public String getAnswer() {
        return getDefaltAnswer();
    }

    @Override
    public ParseMode getParseMode() {
        return ParseMode.Markdown;
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
        return  new ReplyKeyboardMarkup(
                    new String[]{"Show Hiragana","Show Katakana"},
                    new String[]{"Quiz Hiragana","Quiz Katakana"},
                    new String[]{"Change difficulty"}
                )
                .oneTimeKeyboard(true)
                .selective(true)
                .resizeKeyboard(true);
    }
}
