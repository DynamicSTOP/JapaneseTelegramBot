import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by leonid on 30.04.16.
 */
public class DialogFactory {
    public static IDialog makeDialog(String serializedDialog) throws IOException, ClassNotFoundException,EmptyStringException {
        if(serializedDialog==null || serializedDialog.length()==0)
            throw new EmptyStringException();
        byte b[] = serializedDialog.getBytes();
        ByteArrayInputStream bi = new ByteArrayInputStream(b);
        ObjectInputStream si = new ObjectInputStream(bi);
        return (IDialog) si.readObject();
    }
}

