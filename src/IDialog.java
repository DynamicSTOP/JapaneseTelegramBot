import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ParseMode;

import java.io.Serializable;

/**
 * Created by leonid on 29.04.16.
 */
public interface IDialog extends Serializable {
    Long getChatId();
    void setChatId(Long chatId);

    boolean hasKeyboard();

    /**
     *
     * @return
     */
    String getAnswer(String messageText);

    /**
     * ParseMode.Markdown prefered
     * @return
     */
    ParseMode getParseMode();

    /**
     * should be false if there is no links inside
     * @return
     */
    Boolean getDisableWebPagePreview();

    /**
     * can leave it to null
     * @return
     */
    Integer getReplyToMessageId();

    /**
     * can be null
     * @return
     */
    Keyboard getKeyboard();
}
