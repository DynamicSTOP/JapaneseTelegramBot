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
}
