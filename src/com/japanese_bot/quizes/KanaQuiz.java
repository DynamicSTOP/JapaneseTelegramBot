package com.japanese_bot.quizes;

import java.util.*;

/**
 * Created by Leonid on 01.05.2016.
 */
public class KanaQuiz extends Quiz {
    String hiraganaCharacters =
                    "あいうえお" +
                    "かきくけこ" +
                    "さしすせそ" +
                    "たちつてと" +
                    "なにぬねの" +
                    "はひふへほ" +
                    "まみむめも" +
                    "やゆよらり" +
                    "るれろわを";
    String katakanaCharacters =
            "アイウエオ" +
            "カキクケコ" +
            "サシスセソ" +
            "タチツテト" +
            "ナニヌネノ" +
            "ハヒフヘホ" +
            "マミムメモ" +
            "ヤユヨ" +
            "ラリルレロ" +
            "ワヰヱヲ";

    /**
     * For example 'あ'
     */
    String correctAnswer;
    /**
     * This would be 'a' for あ
     */
    String appropriateSyllable;
    /**
     * This would be おわぬめ when correct is あ
     * makes it more challenging
     */
    String confusingCharacters;

    /**
     * Example of answers. one pattern is borring!
     */
    String[] questions={"Do you remember appropriate character for :reading: ?"};

    int quizKey;

    public KanaQuiz(int quizKey,String correctAnswer,String appropriateSyllable, String confusingCharacters){
        this.quizKey = quizKey;
        this.correctAnswer = correctAnswer;
        this.appropriateSyllable = appropriateSyllable;
        this.confusingCharacters = confusingCharacters;
    }

    @Override
    public String createQuestion() {
        Random r = new Random();
        String QuestionBase = questions[r.nextInt(questions.length)];
        return QuestionBase.replace(":reading:",appropriateSyllable);
    }

    @Override
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public List<String> getAnswers() {
        List<String> Answers= new ArrayList<>();
        Answers.add(getCorrectAnswer());

        // 0,1,2 chars are going to be added here
        if(confusingCharacters.length()>0){
            if(confusingCharacters.length()==1){
                Answers.add(String.valueOf(confusingCharacters.charAt(0)));
            } else if(confusingCharacters.length()==2){
                Answers.add(String.valueOf(confusingCharacters.charAt(0)));
                Answers.add(String.valueOf(confusingCharacters.charAt(1)));
            } else {
                List<String> letters = Arrays.asList(confusingCharacters.split(""));
                Collections.shuffle(letters);
                Answers.add(letters.get(0));
                Answers.add(letters.get(1));
            }
        }
        String possibleChars = new String(hiraganaCharacters);

        //removing chars that we already have
        for (int i = 0; i < Answers.size(); i++)
               possibleChars.replace(Answers.get(i),"");

        //shuffle remaining
        List<String> letters = Arrays.asList(possibleChars.split(""));
        Collections.shuffle(letters);

        for(int i = 0; i < letters.size() && Answers.size() < 4; i++)
            Answers.add(letters.get(i));

        //shuff shuff
        Collections.shuffle(Answers);
        return Answers;
    }

    @Override
    public int getKey() {
        return quizKey;
    }

    @Override
    public Boolean checkTask(String userAnswer) {
        return userAnswer.equals(correctAnswer);
    }

    @Override
    public Map<String, String> getParamsList() {
        return null;
    }

    @Override
    public void setValues(Map<String, String> values) {

    }

}