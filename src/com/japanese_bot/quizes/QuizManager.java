package com.japanese_bot.quizes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;

/**
 * Created by Leonid on 01.05.2016.
 */
public class QuizManager {

    public static HashMap<String,Quiz> HiraganaQuizzes;

    public HashMap<String,Quiz> generateHiraganaQuizes(){
        try {
            File inputFile = new File("KanaQuizes.xml");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Node nKanaQuizzes = doc.getElementsByTagName("kanaQuizes").item(0);
            Element eKanaQuizzes = (Element) nKanaQuizzes;
            NodeList quizNodes = eKanaQuizzes.getElementsByTagName("quiz");

            for (int i = 0; i < quizNodes.getLength(); i++) {
                Node node = quizNodes.item(i);

                Element quiz = (Element) node;
                HiraganaQuizzes.put(
                        "QUIZ:HIRAGANA:"+quiz.getAttribute("hiragana-id"),
                        new KanaQuiz(
                        "QUIZ:HIRAGANA:"+quiz.getAttribute("hiragana-id"),
                        quiz.getElementsByTagName("hiragana").item(0).getTextContent(),
                        quiz.getElementsByTagName("romaji").item(0).getTextContent(),
                        quiz.getElementsByTagName("hiraganaSimilar").item(0).getTextContent()
                ));
            }
        } catch (Exception e){
            System.out.println("Generating Hiragan Quizes from xml fail -> "+ e.getMessage());
        }

        return HiraganaQuizzes;
    }

    public static Quiz getHiraganaQuizByKey(String key){
        return HiraganaQuizzes.get(key);
    }
}
