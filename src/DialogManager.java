import com.pengrad.telegrambot.model.Update;

/**
 * Created by Leonid on 01.05.2016.
 */
public class DialogManager {

    private IStorage storage;

    public DialogManager(IStorage storage){
        this.storage=storage;
    }

    public IDialog processDialogUpdate(Update update){
        IDialog dialog = null;
        try {
            dialog = storage.getDialog(update.message().chat().id());
        } catch (EmptyStringException e) {
            dialog = makeHelloDialog(update);
        } catch (Exception e) {
            System.out.println("EXCEPTION: getting message from storage problem \"" + e.getMessage() + "\"");
        }

        return dialog;
    }

    private IDialog makeHelloDialog(Update update){
        if (!update.message().text().equals("/start")) {
            System.out.println("ERROR: expected \"/start\", got \"" + update.message().text() + "\" from user " + update.message());
        }
        return new StartDialog(update.message().chat().id());
    }
}
