import com.japanese_bot.BotStatus;
import com.japanese_bot.Exceptions.*;
import com.japanese_bot.StartUpLoader;
import com.japanese_bot.dialogs.*;
import com.japanese_bot.storages.*;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import retrofit.RetrofitError;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by leonid on 29.04.16.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        IStorage storage = new RedisStorage();
        StartUpLoader startUpLoader = new StartUpLoader(storage);
        TelegramBot bot = null;
        BotStatus botStatus = null;

        try {
            bot = startUpLoader.createTelegramBot();
            System.out.println("Telegram Bot created.");
            storage.start();
            System.out.println("Storage initialized.");
        } catch (Exception e) {
            System.out.println("ERROR: can't create telegram bot -> " + e.getMessage());
            return;
        }

        try {
            botStatus = storage.getBotStatus();
            System.out.println("Bot Status loaded. last update id is " + botStatus.getLastUpdateId());
        } catch (EmptyStringException e) {
            botStatus = new BotStatus();
            System.out.println("Bot Status generated.");
        }


        DialogManager dialogManager = new DialogManager(storage);
        startUpLoader.checkQuizes();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            if (System.in.available() > 0)
                if (br.readLine().equals("exit"))
                    break;
            try {
                GetUpdatesResponse updatesResponse = bot.getUpdates(botStatus.getLastUpdateId() + 1, 100, 60);
                List<Update> updates = updatesResponse.updates();
                for (int i = 0; i < updates.size(); i++) {
                    botStatus.setLastUpdateId(updates.get(i).updateId());
                    Dialog dialog = dialogManager.processDialogUpdate(updates.get(i));
                    bot.sendMessage(
                            dialog.getChatId(),
                            dialogManager.getAnswer(updates.get(i).message().text()),
                            dialog.getParseMode(),
                            dialog.getDisableWebPagePreview(),
                            dialog.getReplyToMessageId(),
                            dialog.getKeyboard());
                    storage.setDialog(dialog);
                    storage.setBotStatus(botStatus);
                }
            } catch (RetrofitError e) {
                if(!e.getMessage().equals("Read timed out"))
                    System.out.println("EXCEPTION: getting updates exception \"" + e.getClass() + "\" \"" + e.getMessage() + "\"");
            }
        }

        try {
            storage.shutDown();
            storage.setBotStatus(botStatus);
        } catch (Exception e) {
            System.out.println("ERROR: shutdown problem " + e.getMessage());
        }

    }
}
