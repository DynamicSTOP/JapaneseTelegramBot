import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Leonid on 01.05.2016.
 */
public class QuizManager {

    public ArrayList<IQuiz> generateHiraganaQuizes(){
        ArrayList<IQuiz> quizes = new ArrayList<>();
        try {
            File inputFile = new File("HiraganaQuizes.xml");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("hiraganaQuiz");
            for (int i = 0; i < nList.getLength(); i++) {
                
            }

        } catch (Exception e){
            System.out.println("Generating Hiragan Quizes from xml fail -> "+ e.getMessage());
        }

            return quizes;
    }
}
