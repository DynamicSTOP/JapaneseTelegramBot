import java.io.Serializable;

/**
 * Created by Leonid on 30.04.2016.
 */
public class BotStatus implements Serializable {
    private int lastUpdateId = -1;
    private int hiraganaQuizCount = 0;

    public int getLastUpdateId() {
        return lastUpdateId;
    }

    public void setLastUpdateId(int lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }


    public int getHiraganaQuizCount() {
        return hiraganaQuizCount;
    }

    public void setHiraganaQuizCount(int hiraganaQuizCount) {
        this.hiraganaQuizCount = hiraganaQuizCount;
    }
}
