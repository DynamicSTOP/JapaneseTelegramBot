import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;

import java.io.*;
import java.util.HashMap;

/**
 * Created by leonid on 30.04.16.
 */
public class StartUpLoader {
    public static TelegramBot createTelegramBot() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("bot.key"));
        return TelegramBotAdapter.build(reader.readLine());
    }

    public static BotStatus loadBotStatus() throws IOException,ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream("BotStatus.cache");
            ObjectInputStream oin = new ObjectInputStream(fis);
            return (BotStatus) oin.readObject();
        } catch (FileNotFoundException e){
            return new BotStatus();
        } catch (Exception e) {
            throw e;
        }
    }

    public static void saveBotStatus(BotStatus botStatus){
        try {
            FileOutputStream fos = new FileOutputStream("BotStatus.cache");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(botStatus);
            oos.flush();
            oos.close();
        } catch (Exception e){
            System.out.println("EXCEPTION: shutdown problem " + e.getMessage());
        }
    }
}
