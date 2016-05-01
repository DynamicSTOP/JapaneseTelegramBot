
/**
 * Created by leonid on 29.04.16.
 */
public interface IQuiz {
    /**
     * @return string that would be sended to user later
     */
    String createQuestion();

    String getCorrectAnswer();

    String[] getWrongAnswers();

    /**
     * should be a uniq id
     * @return
     */
    String getKey();

    /**
     * checks if userAnswer is equal to answer.
     *
     * @param userAnswer
     * @return true if user answered correctly
     */
    Boolean checkTask(String userAnswer);
}
