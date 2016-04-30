import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import java.util.List;

/**
 * Created by leonid on 29.04.16.
 */
public class Main {
    public static void main(String[] args) {


        IStorage storage = new MemoryStorage();
        TelegramBot bot = null;
        BotStatus botStatus = null;
        try {
            bot = StartUpLoader.createTelegramBot();
            botStatus = StartUpLoader.loadBotStatus();
            System.out.println("last update id was -> " + botStatus.getLastUpdateId());
        } catch (Exception e) {
            System.out.println("ERROR: can't create telegram bot -> " + e.getMessage());
            return;
        }

        String hiraganaCharacters = "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわを";

        GetUpdatesResponse updatesResponse = bot.getUpdates(botStatus.getLastUpdateId()+1, 0, 60);
        List<Update> updates = updatesResponse.updates();
        for (int i = 0; i < updates.size(); i++) {
            botStatus.setLastUpdateId(updates.get(i).updateId());
            System.out.println(updates.get(i));
            try {
                storage.get(updates.get(i).message().chat().id());
            } catch (EmptyStringException e) {
                if (updates.get(i).message().text().equals("/start")) {
                    System.out.println("CHAT:"
                            + updates.get(i).message().chat().id()
                            + ":" + updates.get(i).message().from().username()
                            + ": sending hello message");
                    bot.sendMessage(updates.get(i).message().chat().id(),"Hallo Hallo!\nBot is still in development. hehe.");
                } else {
                    System.out.println("ERROR: expected \"/start\", got \"" + updates.get(i).message().text() + "\" from user " + updates.get(i).message());
                }
            } catch (Exception e) {
                System.out.println("EXCEPTION: getting message from storage problem \"" + e.getMessage() + "\"");
            }

        }

        storage.shutDown();
        //TOneverDO remove it from here
        StartUpLoader.saveBotStatus(botStatus);
    }
}
