/**
 * Created by leonid on 30.04.16.
 */
public interface IStorage {
    /**
     * @param chatId
     * @return
     * @throws Exception
     */
    IDialog getDialog(Long chatId) throws Exception;

    void setDialog(IDialog dialog) throws Exception;

    String get(String key) throws Exception;

    void set(String key,String data) throws Exception;


    BotStatus getBotStatus() throws Exception;

    void setBotStatus(BotStatus status) throws Exception;

    /**
     * loads data from file to memory cache or establish connection to database
     *
     * @return
     */
    void start() throws Exception;

    /**
     * saves data from cache to file or closes connection to database
     *
     * @return
     */
    void shutDown() throws Exception;


}
