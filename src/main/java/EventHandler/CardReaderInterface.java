package EventHandler;

import java.util.EventListener;

public interface CardReaderInterface extends EventListener
{
    public void changeReaderStatus(CardReaderEvent evt);
    public void readerGotError(CardReaderEvent evt);
}

