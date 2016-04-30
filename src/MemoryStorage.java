import java.io.*;
import java.util.*;

/**
 * Created by leonid on 30.04.16.
 */
public class MemoryStorage implements IStorage{

    HashMap<Integer,String> storage = new HashMap<Integer,String>();

    public MemoryStorage(){
        start();
    }

    public IDialog get(Long chatId) throws Exception {
        return DialogFactory.makeDialog(storage.get(chatId));
    }

    @Override
    public void set(IDialog dialog) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(dialog);
            so.flush();
            storage.put(dialog.getChatId(),bo.toString());
        } catch (IOException e) {
            System.out.println("EXCEPTION: setting message into memory cache problem");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void start() {
        try {
            FileInputStream fis = new FileInputStream("dialogs.cache");
            ObjectInputStream oin = new ObjectInputStream(fis);
            storage = (HashMap<Integer,String>) oin.readObject();
            System.out.println("loaded " + storage.size() + " dialogs");
        } catch (Exception e){
            System.out.println("EXCEPTION: startup problem "+e.getMessage());
        }
    }

    @Override
    public void shutDown() {
        try{
            FileOutputStream fos = new FileOutputStream("dialogs.cache");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(storage);
            oos.flush();
            oos.close();
        } catch (Exception e){
            System.out.println("EXCEPTION: shutdown problem"+e.getMessage());

        }
    }



}
