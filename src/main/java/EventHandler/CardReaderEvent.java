package EventHandler;

import java.util.EventObject;
import model.Entrance;

public class CardReaderEvent extends EventObject
{

    private Object entrance;
    public CardReaderEvent(Object source)
    {
        super(source);
    }
    public CardReaderEvent(Object source, Object entrance)
    {
        super(source);
        this.entrance = entrance;
    }

    public Object getEntrance()
    {
        return entrance;
    }

}