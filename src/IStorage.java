/**
 * Created by leonid on 30.04.16.
 */
public interface IStorage {
    /**
     *
     * @param chatId
     * @return
     * @throws Exception
     */
    IDialog get(Long chatId) throws Exception;
    void set(IDialog dialog);

    /**
     * loads data from file to memory cache or establish connection to database
     * @return
     */
    void start();

    /**
     * saves data from cache to file or closes connection to database
     * @return
     */
    void shutDown();


}
