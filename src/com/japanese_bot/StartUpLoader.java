package com.japanese_bot;

import com.japanese_bot.quizes.KanaQuiz;
import com.japanese_bot.quizes.Quiz;
import com.japanese_bot.quizes.QuizManager;
import com.japanese_bot.storages.IStorage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;

import java.io.*;
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
        checkKanaQuizes(quizManager);
    }

    private void checkKanaQuizes(QuizManager manager) {
        int quizNumber=0;
        try {
            quizNumber = Integer.valueOf(storage.get("QUIZ:HIRAGANA:COUNT"));
            if(quizNumber>0)
                quizNumber = Integer.valueOf(storage.get("QUIZ:KATAKANA:COUNT"));
        } catch (Exception e) {quizNumber=0;}

        HashMap<String,KanaQuiz> hiraganaQuizes,katakanaQuizes;
        if(quizNumber==0){
            manager.generateKanaQuizes();

            hiraganaQuizes = manager.getHiraganaQuizzes();
            for(Quiz quiz : hiraganaQuizes.values()){
                storage.setQuiz(quiz);
            }
            storage.set("QUIZ:HIRAGANA:COUNT",String.valueOf(hiraganaQuizes.size()));

            katakanaQuizes = manager.getKatakanaQuizzes();

            for(Quiz quiz : katakanaQuizes.values()){
                storage.setQuiz(quiz);
            }
            storage.set("QUIZ:KATAKANA:COUNT",String.valueOf(katakanaQuizes.size()));
        } else {
            hiraganaQuizes = new HashMap<>();
            katakanaQuizes = new HashMap<>();
            for (int i = 0; i < quizNumber; i++) {
                try{
                    KanaQuiz quiz = (KanaQuiz) storage.getQuiz("QUIZ:HIRAGANA:"+String.valueOf(i));
                    quiz.setQuizKey("QUIZ:HIRAGANA:"+String.valueOf(i));
                    quiz.setAlhpabetType(KanaQuiz.alhpabetTypes.HIRAGANA);
                    hiraganaQuizes.put("QUIZ:HIRAGANA:"+String.valueOf(i),quiz);
                } catch (Exception e){
                    System.out.println("EXCEPTION: getting hiragana quiz from storage problem \"" + e.getMessage() + "\"");
                }

                try{
                    KanaQuiz quiz = (KanaQuiz) storage.getQuiz("QUIZ:KATAKANA:"+String.valueOf(i));
                    quiz.setQuizKey("QUIZ:KATAKANA:"+String.valueOf(i));
                    quiz.setAlhpabetType(KanaQuiz.alhpabetTypes.KATAKANA);

                    katakanaQuizes.put("QUIZ:KATAKANA:"+String.valueOf(i),quiz);
                } catch (Exception e){
                    System.out.println("EXCEPTION: getting katakana quiz from storage problem \"" + e.getMessage() + "\"");
                }
            }
        }

        QuizManager.HiraganaQuizzes = hiraganaQuizes;
        QuizManager.KatakanaQuizzes = katakanaQuizes;

        System.out.println("Hiragana quiz count = " + hiraganaQuizes.size());
        System.out.println("Katakana quiz count = " + hiraganaQuizes.size());
    }
}
