import java.io.Serializable;

/**
 * Created by Leonid on 30.04.2016.
 */
public class BotStatus implements Serializable {
    private int lastUpdateId = -1;

    public int getLastUpdateId() {
        return lastUpdateId;
    }

    public void setLastUpdateId(int lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }
}
