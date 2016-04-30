import java.io.Serializable;

/**
 * Created by leonid on 29.04.16.
 */
public interface IDialog extends Serializable{
    Integer getState();
    Integer setState();
    Long getChatId();
}
