package com.japanese_bot.quizes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Leonid on 01.05.2016.
 */
public class QuizManager {

    public static HashMap<String,KanaQuiz> HiraganaQuizzes, KatakanaQuizzes;

    public static HashMap<String, KanaQuiz> getHiraganaQuizzes() {
        return HiraganaQuizzes;
    }

    public static HashMap<String, KanaQuiz> getKatakanaQuizzes() {
        return KatakanaQuizzes;
    }

    enum QuizType {HIRAGANA, KATAKANA}


    public HashMap<String,KanaQuiz> generateKanaQuizes(){
        HiraganaQuizzes = new HashMap<>();
        KatakanaQuizzes = new HashMap<>();
        try {
            File inputFile = new File("KanaQuizzes.xml");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Node nKanaQuizzes = doc.getElementsByTagName("kanaQuizzes").item(0);
            Element eKanaQuizzes = (Element) nKanaQuizzes;
            NodeList quizNodes = eKanaQuizzes.getElementsByTagName("quiz");

            for (int i = 0; i < quizNodes.getLength(); i++) {
                Node node = quizNodes.item(i);
                addQuiz(QuizType.HIRAGANA,(Element) node);
                addQuiz(QuizType.KATAKANA,(Element) node);
            }
        } catch (Exception e){
            System.out.println("Generating Kana Quizzes from xml fail -> "+ e.getMessage());
        }

        return HiraganaQuizzes;
    }

    public void addQuiz(QuizType quizType,Element quiz){
        switch (quizType){
            case HIRAGANA:
                HiraganaQuizzes.put(
                        "QUIZ:HIRAGANA:"+quiz.getAttribute("id"),
                        new KanaQuiz(
                                "QUIZ:HIRAGANA:"+quiz.getAttribute("id"),
                                quiz.getElementsByTagName("hiragana").item(0).getTextContent(),
                                quiz.getElementsByTagName("romaji").item(0).getTextContent(),
                                quiz.getElementsByTagName("hiraganaSimilar").item(0).getTextContent(),
                                KanaQuiz.alhpabetTypes.HIRAGANA
                        ));
                break;
            case KATAKANA:
                KatakanaQuizzes.put(
                        "QUIZ:KATAKANA:"+quiz.getAttribute("id"),
                        new KanaQuiz(
                                "QUIZ:KATAKANA:"+quiz.getAttribute("id"),
                                quiz.getElementsByTagName("katakana").item(0).getTextContent(),
                                quiz.getElementsByTagName("romaji").item(0).getTextContent(),
                                quiz.getElementsByTagName("katakanaSimilar").item(0).getTextContent(),
                                KanaQuiz.alhpabetTypes.KATAKANA
                        ));
                break;
        }
    }

    public static Quiz getHiraganaQuizByKey(String key){
        return HiraganaQuizzes.get(key);
    }

    public static Quiz getKatakanaQuizByKey(String key){
        return KatakanaQuizzes.get(key);
    }

    public static KanaQuiz getRandomHiraganaQuiz(){
        return (KanaQuiz) HiraganaQuizzes.values().toArray()[ new Random().nextInt(HiraganaQuizzes.size())];
    }

    public static KanaQuiz getRandomKatakanaQuiz(){
        return (KanaQuiz) KatakanaQuizzes.values().toArray()[ new Random().nextInt(KatakanaQuizzes.size())];
    }
}
