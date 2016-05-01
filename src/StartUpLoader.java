import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by leonid on 30.04.16.
 */
public class StartUpLoader {
    private IStorage storage;

    public StartUpLoader(IStorage storage){
        this.storage=storage;
    }

    public static TelegramBot createTelegramBot() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("bot.key"));
        return TelegramBotAdapter.build(reader.readLine());
    }

    public void checkQuizes(){
        QuizManager quizManager = new QuizManager();
        checkHiraganaQuizes(quizManager);
    }

    private void checkHiraganaQuizes(QuizManager manager) {
        try {
            System.out.println("Hiragana quiz count = "+storage.get("QUIZ:HIRAGANA:COUNT"));
        } catch (Exception e) {
            ArrayList<IQuiz> quizes = manager.generateHiraganaQuizes();
            for (int i = 0; i < quizes.size() ; i++) {

            }
            try {
                storage.set("QUIZ:HIRAGANA:COUNT",String.valueOf(quizes.size()));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
