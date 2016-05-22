package com.japanese_bot.quizes;

import java.util.*;

/**
 * Created by Leonid on 01.05.2016.
 */
public class KanaQuiz extends Quiz {
    private static String hiraganaCharacters =
                    "あいうえお" +
                    "かきくけこ" +
                    "さしすせそ" +
                    "たちつてと" +
                    "なにぬねの" +
                    "はひふへほ" +
                    "まみむめも" +
                    "やゆよらり" +
                    "るれろわを";
    private static String katakanaCharacters =
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

    private static List<String> syllabls = new ArrayList<>(
            Arrays.asList(
                "a","i","u","e","o","ka","ki","ku","ke","ko","sa","shi","su","se","so","ta","chi","tsu","te",
                "to","na","ni","nu","ne","no","ha","hi","fu","he","ho","ma","mi","mu","me","mo","ya","yu","yo",
                "ra","ri","ru","re","ro","wa","wo","n"
            )
    );

    protected enum alhpabetTypes {HIRAGANA, KATAKANA}

    public alhpabetTypes getAlhpabetType() {
        return alhpabetType;
    }

    public void setAlhpabetType(alhpabetTypes alhpabetType) {
        this.alhpabetType = alhpabetType;
    }

    protected alhpabetTypes alhpabetType;

    /**
     * For example 'あ'
     */
    String kanaCharacter;
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
    String[] questionsKana ={"Character for ':reading:' is "};

    String[] questionsSyllable ={"':character:' is pronounced as "};

    public void setQuizKey(String quizKey) {
        this.quizKey = quizKey;
    }

    public void setConfusingCharacters(String confusingCharacters) {
        this.confusingCharacters = confusingCharacters;
    }

    public void setAppropriateSyllable(String appropriateSyllable) {
        this.appropriateSyllable = appropriateSyllable;
    }

    public void setKanaCharacter(String kanaCharacter) {
        this.kanaCharacter = kanaCharacter;
    }

    private String quizKey;

    public KanaQuiz(String quizKey, String kanaCharacter, String appropriateSyllable, String confusingCharacters){
        this.quizKey = quizKey;
        this.kanaCharacter = kanaCharacter;
        this.appropriateSyllable = appropriateSyllable;
        this.confusingCharacters = confusingCharacters;
    }

    public KanaQuiz(Map<String,String> values){
        super(values);
    }

    @Override
    public String createQuestion() {
        Random r = new Random();
        if(isSyllableMode()){
            String QuestionBase = questionsSyllable[r.nextInt(questionsSyllable.length)];
            return QuestionBase.replace(":character:",kanaCharacter);
        } else {
            String QuestionBase = questionsKana[r.nextInt(questionsKana.length)];
            return QuestionBase.replace(":reading:", appropriateSyllable);
        }

    }

    public String getCorrectAnswer() {
        if(syllableMode)
            return appropriateSyllable;
        else
            return kanaCharacter;
    }

    @Override
    public List<String> getAnswers() {
        List<String> Answers= new ArrayList<>();
        Answers.add(getCorrectAnswer());

        if (syllableMode)
              return getAnswersRomaji(Answers);
        else
              return getAnswersKana(Answers);
    }

    private List<String> getAnswersKana(List<String> answers){
        // 0,1,2 chars are going to be added here
        if(confusingCharacters.length()>0){
            if(confusingCharacters.length()==1){
                answers.add(String.valueOf(confusingCharacters.charAt(0)));
            } else if(confusingCharacters.length()==2){
                answers.add(String.valueOf(confusingCharacters.charAt(0)));
                answers.add(String.valueOf(confusingCharacters.charAt(1)));
            } else {
                List<String> letters = Arrays.asList(confusingCharacters.split(""));
                Collections.shuffle(letters);
                answers.add(letters.get(0));
                answers.add(letters.get(1));
            }
        }
        String possibleChars = new String(hiraganaCharacters);

        //removing chars that we already have
        for (int i = 0; i < answers.size(); i++)
            possibleChars = possibleChars.replace(answers.get(i),"");

        //shuffle remaining
        List<String> letters = Arrays.asList(possibleChars.split(""));
        Collections.shuffle(letters);

        for(int i = 0; i < letters.size() && answers.size() < 4; i++)
            answers.add(letters.get(i));
        //shuff shuff because otherwise first is correct
        Collections.shuffle(answers);
        return answers;
    }

    private List<String> getAnswersRomaji(List<String> answers){
        ArrayList<String> variants = new ArrayList<>(syllabls);
        for(int i = 0; i<variants.size();i++){
            if(variants.get(i).equals(getCorrectAnswer())){
                variants.remove(i);
                break;
            }
        }
        Collections.shuffle(variants);

        for(int i = 0; i < variants.size() && answers.size() < 4; i++)
            answers.add(variants.get(i));

        //shuff shuff because otherwise first is correct
        Collections.shuffle(answers);
        return answers;
    }

    @Override
    public String getKey() {
        return quizKey;
    }

    @Override
    public Boolean checkTask(String userAnswer) {
        if(isSyllableMode())
            return userAnswer.equals(appropriateSyllable);
        else
            return userAnswer.equals(kanaCharacter);
    }

    @Override
    public Map<String, String> getParamsList() {
        Map<String,String> values = new HashMap<>();
        values.put("quizType",getClass().getCanonicalName());
        values.put("key",getKey());
        values.put("kanaCharacter", kanaCharacter);
        values.put("appropriateSyllable",appropriateSyllable);
        values.put("confusingCharacters",confusingCharacters);
        return values;
    }

    @Override
    public void setValues(Map<String, String> values) {
        setAppropriateSyllable(values.get("appropriateSyllable"));
        setConfusingCharacters(values.get("confusingCharacters"));
        setKanaCharacter(values.get("kanaCharacter"));
        setQuizKey(values.get("key"));
    }
}
