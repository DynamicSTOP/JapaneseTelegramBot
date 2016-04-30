import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by leonid on 30.04.16.
 */
public class StartUpLoader {
    public static TelegramBot makeTelegramBot() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("bot.key"));
        return TelegramBotAdapter.build(reader.readLine());
    }
}
