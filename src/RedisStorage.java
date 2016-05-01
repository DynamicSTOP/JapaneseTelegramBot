import redis.clients.jedis.Jedis;

import java.io.*;

/**
 * Created by Leonid on 01.05.2016.
 */
public class RedisStorage implements IStorage {
    Jedis jedis;

    @Override
    public IDialog getDialog(Long chatId) throws Exception {
        return (IDialog) getObject("DIALOGS:" + jedis.get(chatId.toString()));
    }

    @Override
    public void setDialog(IDialog dialog) {
        setObject("DIALOGS:" + dialog.getChatId().toString(), dialog);
    }

    @Override
    public String get(String key) throws Exception {
        return jedis.get(key);
    }

    @Override
    public void set(String key, String data) throws Exception {
        jedis.set(key, data);
    }

    @Override
    public BotStatus getBotStatus() throws EmptyStringException {
        BotStatus botStatus = (BotStatus) getObject("BOTSTATUS");
        if(botStatus == null)
            botStatus = new BotStatus();
        return botStatus;
    }

    @Override
    public void setBotStatus(BotStatus status) throws Exception {
        setObject("BOTSTATUS", status);
    }

    @Override
    public void start() throws Exception {
        jedis = new Jedis("localhost");
        if (jedis.isConnected())
            throw new Exception("Cannot connect to Redis server!");
    }

    @Override
    public void shutDown() {
        jedis.close();
    }

    private void setObject(String key, Object o) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(o);
            so.flush();
            jedis.set(key, bo.toString());
        } catch (IOException e) {
            System.out.println("EXCEPTION: setting \"" + key + "\" into redis problem -> " + e.getMessage());
        }
    }

    private Object getObject(String key) throws EmptyStringException {
        try {
            byte b[] = get(key).getBytes();
            ByteArrayInputStream bi = new ByteArrayInputStream(b);
            ObjectInputStream si = new ObjectInputStream(bi);
            return si.readObject();
        } catch (Exception e) {
            System.out.println("EXCEPTION: getting \"" + key + "\" from redis problem -> " + e.getClass() + ":" + e.getMessage());
            throw new EmptyStringException();
        }
    }


}
