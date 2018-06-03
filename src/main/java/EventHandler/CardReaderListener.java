package EventHandler;

import javax.swing.event.EventListenerList;

public class CardReaderListener
{

    protected EventListenerList listenerList = new EventListenerList();

    public void addCardReaderListener(CardReaderInterface listener)
    {
        listenerList.add(CardReaderInterface.class, listener);
    }

    public void removeCardReaderListener(CardReaderInterface listener)
    {
        listenerList.remove(CardReaderInterface.class, listener);
    }

    public void changeCardReaderStatusUserJoin(String status, Object user)
    {
        CardReaderEvent evt = new CardReaderEvent(status, user);
        fireCardReaderEvent(evt,false);
    }
    public void changeCardReaderStatus(String status)
    {
        CardReaderEvent evt = new CardReaderEvent(status);
        fireCardReaderEvent(evt,false);
    }
    public void cardReaderError(String status)
    {
        CardReaderEvent evt = new CardReaderEvent(status);
        fireCardReaderEvent(evt,true);
    }
    private void fireCardReaderEvent(CardReaderEvent evt, boolean error)
    {
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i = i + 2)
        {
            if (listeners[i] == CardReaderInterface.class)
            {
                if(error)
                {
                    ((CardReaderInterface) listeners[i + 1]).readerGotError(evt);
                }
                else
                {
                    ((CardReaderInterface) listeners[i + 1]).changeReaderStatus(evt);
                }
            }
        }
    }
}
