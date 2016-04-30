import java.io.Serializable;

/**
 * Created by leonid on 29.04.16.
 */
public interface IDialog extends Serializable {
    Long getChatId();
    void setChatId(Long chatId);
    IQuiz getCurrentQuiz();
}
