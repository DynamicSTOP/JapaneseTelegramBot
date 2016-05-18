package com.japanese_bot.quizes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Leonid on 01.05.2016.
 */
public class QuizManager {

    public ArrayList<Quiz> generateHiraganaQuizes(){
        ArrayList<Quiz> quizes = new ArrayList<>();
        try {
            File inputFile = new File("KanaQuizes.xml");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Node nKanaQuizes = doc.getElementsByTagName("kanaQuizes").item(0);
            Element eKanaQuizes = (Element) nKanaQuizes;
            NodeList quizNodes = eKanaQuizes.getElementsByTagName("quiz");

            for (int i = 0; i < quizNodes.getLength(); i++) {
                Node node = quizNodes.item(i);

                Element quiz = (Element) node;
                quizes.add(new KanaQuiz(
                        "QUIZ:HIRAGANA:"+quiz.getAttribute("hiragana-id"),
                        quiz.getElementsByTagName("hiragana").item(0).getTextContent(),
                        quiz.getElementsByTagName("romaji").item(0).getTextContent(),
                        quiz.getElementsByTagName("hiraganaSimilar").item(0).getTextContent()
                ));
            }
        } catch (Exception e){
            System.out.println("Generating Hiragan Quizes from xml fail -> "+ e.getMessage());
        }

        return quizes;
    }
}
