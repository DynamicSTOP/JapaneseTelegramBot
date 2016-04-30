/**
 * Created by leonid on 30.04.16.
 */
public class StartDialog implements IDialog {
    private Long chatId;

    @Override
    public Long getChatId() {
        return chatId;
    }

    @Override
    public void setChatId(Long chatId) {
        this.chatId=chatId;
    }

    @Override
    public IQuiz getCurrentQuiz() {
        return null;
    }
}
