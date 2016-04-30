import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import java.io.IOException;
import java.util.List;

/**
 * Created by leonid on 29.04.16.
 */
public class Main {
    public static void main(String[] args) {


        IStorage storage = new MemoryStorage();
        TelegramBot bot = null;
        try {
            bot = StartUpLoader.makeTelegramBot();
        } catch (IOException e) {
            System.out.println("ERROR: can't create telegram bot -> " + e.getMessage());
            return;
        }


        String hiraganaCharacters = "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわを";

        GetUpdatesResponse updatesResponse = bot.getUpdates(0, 0, 5000);
        List<Update> updates = updatesResponse.updates();
        for (int i = 0; i < updates.size(); i++) {
            System.out.println(updates.get(i).message().chat() + "  \"" + updates.get(i).message().text() + "\"");
            try {
                storage.get(updates.get(i).message().chat().id());
            } catch (EmptyStringException e) {
                if (updates.get(i).message().text() == "/start") {

                } else {
                    System.out.println("ERROR: expected \"/start\", got\"" + e.getMessage() + "\" from user " + updates.get(i).message());
                }
            } catch (Exception e) {
                System.out.println("EXCEPTION: getting message from storage problem \"" + e.getMessage() + "\"");
            }

        }

        storage.shutDown();
    }
}
