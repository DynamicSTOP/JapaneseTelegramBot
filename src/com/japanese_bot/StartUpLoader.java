package com.japanese_bot;

import com.japanese_bot.quizes.Quiz;
import com.japanese_bot.quizes.QuizManager;
import com.japanese_bot.storages.IStorage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;

import java.io.*;
import java.util.ArrayList;

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
        int quizNumber=0;
        try {
            quizNumber=Integer.valueOf(storage.get("QUIZ:HIRAGANA:COUNT"));
        } catch (Exception e) {}

        ArrayList<Quiz> quizes;
        if(quizNumber==0){
            quizes = manager.generateHiraganaQuizes();
            for (int i = 0; i < quizes.size() ; i++) {
                storage.setQuiz(quizes.get(i));
            }
            storage.set("QUIZ:HIRAGANA:COUNT",String.valueOf(quizes.size()));
        } else {
            quizes = new ArrayList<>();
            for (int i = 0; i < quizNumber; i++) {
                try{
                    quizes.add(storage.getQuiz("QUIZ:HIRAGANA:"+String.valueOf(i)));
                } catch (Exception e){
                    System.out.println("EXCEPTION: getting hiragana quiz from storage problem \"" + e.getMessage() + "\"");
                }
            }
        }

        System.out.println("Hiragana quiz count = " + quizes.size());
    }
}
